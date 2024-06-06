import { Request, ResponseToolkit } from '@hapi/hapi'
import { findUserByEmail, getUserById, registerUserToDB } from '../services/auth.service'
import {
  createSessionValidation,
  refreshSessionValidation,
  registerUserValidation
} from '../validations/auth.validation'
import { logger } from '../utils/logger'
import { checkPassword, hashing } from '../utils/hashing'
import { v4 as uuidv4 } from 'uuid'
import { signJWT, verifyToken } from '../utils/jwt'

import UserType from '../types/user.type'

export const registerUser = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as UserType
  payload.user_id = uuidv4()

  const { error, value } = registerUserValidation(payload)

  if (error) {
    logger.error(`ERR: user - register = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    value.password = `${hashing(value.password)}`
    await registerUserToDB(value)

    logger.info('Success register new user')
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Register user success'
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

export const createSession = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as UserType
  const { error, value } = createSessionValidation(payload)

  if (error) {
    logger.error(`ERR: user - create session = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    const user: any = await findUserByEmail(value.email)
    const isValid = checkPassword(value.password, user.password)

    if (!isValid) {
      return h
        .response({
          status: false,
          statusCode: 401,
          message: 'Invalid email or password'
        })
        .code(401)
    }
    const accessToken = signJWT({ ...user }, { expiresIn: '10s' })
    const refreshToken = signJWT({ ...user }, { expiresIn: '1y' })

    logger.info('Success login user')
    return h
      .response({
        status: true,
        statusCode: 200,
        message: 'Login user success',
        id: user.user_id,
        data: { accessToken, refreshToken }
      })
      .code(200)
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

export const refreshSession = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as UserType
  const { error, value } = refreshSessionValidation(payload)

  if (error) {
    logger.error(`ERR: user - refresh session = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    const { decoded } = verifyToken(value.refreshToken)
    const user = await findUserByEmail(decoded._doc.email)

    if (!user) {
      return h
        .response({
          status: false,
          statusCode: 401,
          message: 'User not found'
        })
        .code(401)
    }
    const accessToken = signJWT({ ...user }, { expiresIn: '1d' })

    logger.info('Success refresh token')
    return h
      .response({
        status: true,
        statusCode: 200,
        message: 'Refresh token success',
        token: accessToken
      })
      .code(200)
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

export const getSingleUser = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  if (id) {
    const user = await getUserById(id)
    if (user) {
      logger.info('Success get user data')
      return h.response({
        status: true,
        statusCode: 200,
        data: user
      })
    }
    logger.error('Data not found')
    return h.response({
      status: false,
      statusCode: 404,
      message: 'Data not found',
      data: {}
    })
  }
}
