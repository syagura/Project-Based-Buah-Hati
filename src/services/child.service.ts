import { logger } from '../utils/logger'
import childModel from '../models/child.model'
import ChildType from '../types/child.type'

export const registerChildToDB = async (payload: ChildType) => {
  return await childModel.create(payload)
}

export const getChildsFromDB = async () => {
  return await childModel
    .find()
    .then((data) => {
      return data
    })
    .catch((error) => {
      logger.info('Cannot retrieve data from database')
      logger.error(error)
    })
}

export const getChildById = async (id: String) => {
  return await childModel.findOne({ child_id: id })
}
