import { logger } from '../utils/logger'
import articleModel from '../models/article.model'
import ArticleType from '../types/article.type'

export const saveArticleToDB = async (payload: ArticleType) => {
  return await articleModel.create(payload)
}

export const getArticlesFromDB = async () => {
  return await articleModel
    .find()
    .then((data) => {
      return data
    })
    .catch((error) => {
      logger.info('Cannot retrieve data from database')
      logger.error(error)
    })
}

export const getArticleById = async (id: String) => {
  return await articleModel.findOne({ article_id: id })
}

export const updateArticleById = async (id: String, payload: ArticleType) => {
  return await articleModel.findOneAndUpdate({ article_id: id }, { $set: payload })
}

export const deleteArticleById = async (id: String) => {
  return await articleModel.findOneAndDelete({ article_id: id })
}
