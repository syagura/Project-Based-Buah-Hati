import { logger } from '../utils/logger'
import analysisModel from '../models/analysis.model'
import AnalysisType from '../types/analysis.type'

export const saveAnalysisToDB = async (analysis: AnalysisType) => {
  return await analysisModel.create(analysis)
}

export const getAnalysisFromDB = async () => {
  return await analysisModel
    .find()
    .then((data) => {
      return data
    })
    .catch((error) => {
      logger.info('Cannot retrieve data from database')
      logger.error(error)
    })
}

export const getAnalysisById = async (id: String) => {
  return await analysisModel.findOne({ analysis_id: id })
}
