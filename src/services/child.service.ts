import { logger } from '../utils/logger'
import childModel from '../models/child.model'
import ChildType from '../types/child.type'

export const registerChildToDB = async (payload: ChildType) => {
  return await childModel.create(payload)
}

export const getChildsFromDB = async (id: String) => {
  return await childModel
    .find({ user_id: id })
    .then((data) => {
      return data
    })
    .catch((error) => {
      logger.info('Cannot retrieve data from database')
      logger.error(error)
    })
}

export const getChildById = async (user_id: String, child_id: String) => {
  return await childModel.findOne({ user_id, child_id })
}
