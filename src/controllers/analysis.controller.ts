import { Request, ResponseToolkit } from '@hapi/hapi'
import * as tf from '@tensorflow/tfjs-node'
import {getAnalysisById, getAnalysisFromDB, saveAnalysisToDB} from '../services/analysis.service'
import {inputAnalysisValidation, saveAnalysisValidation} from '../validations/analysis.validation'
import { logger } from '../utils/logger'
import { v4 as uuidv4 } from 'uuid'
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
    'berat badan/umur': processLabel(['kurang', 'normal', 'lebih'], [`${payload.weight}`, `${payload.age}`]),
    'tinggi badan/umur': processLabel(['pendek', 'normal', 'tinggi'], [`${payload.height}`, `${payload.age}`]),
    'berat badan/tinggi badan': processLabel(['gizi kurang', 'normal', 'gizi lebih'], [`${payload.weight}`, `${payload.height}`]),
    'lingkar kepala/(umur & jenis kelamin)': processLabel(['mikrosefali', 'normal', 'makrosefali'], [`${payload.headCircumference}`, `${payload.age}`, `${payload.gender}`]),
  }

  const analysis_id = uuidv4()
  const child_id = `${value.child_id}`
  const weight_age = labels['berat badan/umur']
  const height_age = labels['tinggi badan/umur']
  const weight_height = labels['berat badan/tinggi badan']
  const headCircumference_age_gender = labels['lingkar kepala/(umur & jenis kelamin)']
  const date = `${value.date}`

  saveAnalysisValidation({analysis_id, weight_age, height_age, weight_height, headCircumference_age_gender})

  try {
    await saveAnalysisToDB({analysis_id, child_id, weight_age, height_age, weight_height, headCircumference_age_gender, date})

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
          date
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
