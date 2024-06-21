import { logger } from '../utils/logger'
import analysisModel from '../models/analysis.model'
import AnalysisType from '../types/analysis.type'

export const saveAnalysisToDB = async (analysis: AnalysisType) => {
  return await analysisModel.create(analysis)
}

export const getAnalysisFromDB = async (id: String) => {
  return await analysisModel
    .find({ child_id: id })
    .then((data) => {
      return data
    })
    .catch((error) => {
      logger.info('Cannot retrieve data from database')
      logger.error(error)
    })
}

export const getAnalysisById = async (child_id: String, analysis_id: String) => {
  return await analysisModel.findOne({ child_id, analysis_id })
}
