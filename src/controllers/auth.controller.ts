import { Request, Response } from 'express'
import { findUserByEmail, registerUserToDB } from '../services/auth.service'
import {
  createSessionValidation,
  refreshSessionValidation,
  registerUserValidation
} from '../validations/auth.validation'
import { logger } from '../utils/logger'
import { checkPassword, hashing } from '../utils/hashing'
import { v4 as uuidv4 } from 'uuid'
import { signJWT, verifyToken } from '../utils/jwt'

export const registerUser = async (req: Request, res: Response) => {
  req.body.user_id = uuidv4()
  const { error, value } = registerUserValidation(req.body)

  if (error) {
    logger.error(`ERR: user - register = ${error.details[0].message}`)
    return res.status(422).send({
      status: false,
      statusCode: 422,
      message: error.details[0].message
    })
  }

  try {
    value.password = `${hashing(value.password)}`
    await registerUserToDB(value)

    logger.info('Success register new user')
    return res.status(201).send({
      status: true,
      statusCode: 201,
      message: 'Register user success'
    })
  } catch (error) {
    logger.error('SERVER ERROR')
    return res.status(500).send({
      status: false,
      statusCode: 500,
      message: error
    })
  }
}

export const createSession = async (req: Request, res: Response) => {
  const { error, value } = createSessionValidation(req.body)

  if (error) {
    logger.error(`ERR: user - create session = ${error.details[0].message}`)
    return res.status(422).send({
      status: false,
      statusCode: 422,
      message: error.details[0].message
    })
  }

  try {
    const user: any = await findUserByEmail(value.email)
    const isValid = checkPassword(value.password, user.password)

    if (!isValid) {
      return res.status(401).send({
        status: false,
        statusCode: 401,
        message: 'Invalid email or password'
      })
    }
    const accessToken = signJWT({ ...user }, { expiresIn: '10s' })
    const refreshToken = signJWT({ ...user }, { expiresIn: '1y' })

    logger.info('Succes login user')
    return res.status(200).send({
      status: true,
      statusCode: 200,
      message: 'Login user success',
      data: { accessToken, refreshToken }
    })
  } catch (error) {
    logger.error('SERVER ERROR')
    return res.status(500).send({
      status: false,
      statusCode: 500,
      message: error
    })
  }
}

export const refreshSession = async (req: Request, res: Response) => {
  const { error, value } = refreshSessionValidation(req.body)

  if (error) {
    logger.error(`ERR: user - refresh session = ${error.details[0].message}`)
    return res.status(422).send({
      status: false,
      statusCode: 422,
      message: error.details[0].message
    })
  }

  try {
    const { decoded } = verifyToken(value.refreshToken)
    const user = await findUserByEmail(decoded._doc.email)

    if (!user) {
      return false
    }
    const accessToken = signJWT({ ...user }, { expiresIn: '1d' })

    logger.info('Succes refresh token')
    return res.status(200).send({
      status: true,
      statusCode: 200,
      message: 'Refresh token success',
      token: accessToken
    })
  } catch (error) {
    logger.error('SERVER ERROR')
    return res.status(500).send({
      status: false,
      statusCode: 500,
      message: error
    })
  }
}
