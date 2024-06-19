import { Request, ResponseToolkit } from '@hapi/hapi'
import * as tf from '@tensorflow/tfjs-node'
import {getAnalysisById, getAnalysisFromDB, saveAnalysisToDB} from '../services/analysis.service'
import {inputAnalysisValidation, saveAnalysisValidation} from '../validations/analysis.validation'
import { logger } from '../utils/logger'
import { v4 as uuidv4 } from 'uuid'
import axios from 'axios'
import dotenv from 'dotenv'
dotenv.config()

export const saveAnalysis = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as any

  if (payload.gender.toLowerCase() === 'laki-laki') {
    payload.gender = 1
  } else if (payload.gender.toLowerCase() === 'perempuan') {
    payload.gender = 0
  }

  const { error, value } = inputAnalysisValidation({child_id: payload.child_id, gender: payload.gender, age: payload.age, height: payload.height, weight: payload.weight, headCircumference: payload.headCircumference, date: payload.date})

  if (error) {
    logger.error(`ERR: analysis - save = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  const loadedModel = await tf.loadLayersModel(`${process.env.MODEL_URL}`)

  // Function to process and categorize a single label
  const processLabel = (label: string[], features: string[]): string => {
    // Create input tensor based on features
    const inputTensor = tf.tensor2d([
      [value.gender, value.age, value.height, value.weight, value.headCircumference],
    ]).reshape([1, 5])

    // Make prediction and get probabilities
    const prediction = loadedModel.predict(inputTensor) as tf.Tensor | tf.Tensor[]

    // Handle both single tensor and array of tensors
    let predictionArray: number[]
    if (Array.isArray(prediction)) {
      predictionArray = Array.from((prediction[0] as tf.Tensor).dataSync())
    } else {
      predictionArray = Array.from(prediction.dataSync())
    }
    const probabilities = predictionArray

    // Determine category based on probabilities
    let category: string
    if (probabilities[1] > probabilities[0] && probabilities[1] > probabilities[2]) {
      category = label[1]
    } else if (probabilities[0] > probabilities[1] && probabilities[0] > probabilities[2]) {
      category = label[0]
    } else {
      category = label[2]
    }
    return category
  }

  // Process and categorize each label
  const labels: { [key: string]: string } = {
    'berat_badan': processLabel(['kurus', 'normal', 'obesitas'], [`${payload.weight}`, `${payload.age}`]),
    'tinggi_badan': processLabel(['pendek', 'normal', 'tinggi'], [`${payload.height}`, `${payload.age}`]),
    'gizi': processLabel(['gizi kurang', 'normal', 'gizi lebih'], [`${payload.weight}`, `${payload.height}`]),
    'lingkar_kepala': processLabel(['mikrosefali', 'normal', 'makrosefali'], [`${payload.headCircumference}`, `${payload.age}`, `${payload.gender}`]),
  }

  const analysis_id = uuidv4()
  const child_id = `${value.child_id}`
  const weight_age = labels['berat_badan']
  const height_age = labels['tinggi_badan']
  const weight_height = labels['gizi']
  const headCircumference_age_gender = labels['lingkar_kepala']
  const date = `${value.date}`

  saveAnalysisValidation({analysis_id, weight_age, height_age, weight_height, headCircumference_age_gender})

  /** Start Gemini Prompt API */
  let recomendation = '';

  try {
    const response = await axios.post('http://34.128.120.84:5000/summary', {
      gender: "Perempuan",  // ganti dengan varibael input awal aplikasi
      age: 7,  // ganti dengan varibael input awal aplikasi
      weight: 25, // ganti dengan varibael input awal aplikasi
      height: 120, // ganti dengan varibael input awal aplikasi
      headCircumference: 54, // ganti dengan varibael input awal aplikasi
      weightAgeGender: "Obesitas", // ganti dengan varibael input dari output prediksi model
      heightAgeGender: "Tinggi", // ganti dengan varibael input dari output prediksi model
      headCircumferenceAgeGender: "Makrosefali", // ganti dengan varibael input dari output prediksi model
      weightHeight: "Gizi Lebih" // ganti dengan varibael input dari output prediksi model
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    if (response.status === 200) {
      const data = response.data;
      recomendation = data.summaryText;
      console.log("Summary API Response Text:", data.summaryText);
    } else {
      console.error(`Error ${response.status}: ${response.statusText}`);
    }
  } catch (error: any) {
    if (error.response) {
      console.error(`Error ${error.response.status}: ${error.response.statusText}`);
    } else {
      console.error(`Error: ${error.message}`);
    }
  }
  /** End Gemini Prompt API */

  try {
    await saveAnalysisToDB({analysis_id, child_id, weight_age, height_age, weight_height, headCircumference_age_gender, date, recomendation})

    logger.info(`Success save the analysis prediction`)
    console.log(payload)
    
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Save analysis prediction success',
        analyze_result: {
          analysis_id,
          child_id,
          weight_age,
          height_age,
          weight_height,
          headCircumference_age_gender,
          date,
          recomendation
        }
      })
      .code(201)
  } catch (error) {
    logger.error('SERVER ERROR')
    return h
      .response({
        status: false,
        statusCode: 500,
        message: error
      })
      .code(500)
  }
}

export const getAnalysis = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  if (id) {
    const analysis = await getAnalysisById(id)
    if (analysis) {
      logger.info('Success get analysis data')
      return h.response({
        status: true,
        statusCode: 200,
        data: analysis
      })
    }

    logger.error('Data not found')
    return h.response({
      status: false,
      statusCode: 404,
      message: 'Data not found',
      data: {}
    })
  } else {
    const analysis = await getAnalysisFromDB()
    logger.info('Success get analysis data')
    return h.response({
      status: true,
      statusCode: 200,
      data: analysis
    })
  }
}
