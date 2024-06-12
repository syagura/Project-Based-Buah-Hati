import { Request, ResponseToolkit } from '@hapi/hapi'
import {
  deleteArticleById,
  getArticlesFromDB,
  getArticleById,
  saveArticleToDB,
  updateArticleById
} from '../services/article.service'
import { saveArticleValidation, updateArticleValidation } from '../validations/article.validation'
import { logger } from '../utils/logger'
import { v4 as uuidv4 } from 'uuid'

export const saveArticle = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as any
  payload.article_id = uuidv4()

  const { error, value } = saveArticleValidation(payload)

  if (error) {
    logger.error(`ERR: article - save = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    await saveArticleToDB(value)

    logger.info(`Success save the ${payload.title} article`)
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Save article success'
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

export const getArticle = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  if (id) {
    const article = await getArticleById(id)
    if (article) {
      logger.info('Success get article data')
      return h.response({
        status: true,
        statusCode: 200,
        data: article
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
    const articles = await getArticlesFromDB()
    logger.info('Success get articles data')
    return h.response({
      status: true,
      statusCode: 200,
      data: articles
    })
  }
}

export const updateArticle = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  const payload = request.payload as any

  const { error, value } = updateArticleValidation(payload)

  if (error) {
    logger.error(`ERR: article - update = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    await updateArticleById(id, value)

    logger.info('Success update the article')
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Update article success'
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

export const deleteArticle = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  try {
    await deleteArticleById(id)

    logger.info('Success delete the article')
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Delete article success'
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
