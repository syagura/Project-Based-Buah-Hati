import { Request, ResponseToolkit } from '@hapi/hapi'
import { getChildById, getChildsFromDB, registerChildToDB } from '../services/child.service'
import { registerChildValidation } from '../validations/child.validation'
import { logger } from '../utils/logger'
import { v4 as uuidv4 } from 'uuid'

export const registerChild = async (request: Request, h: ResponseToolkit) => {
  const payload = request.payload as any
  payload.child_id = uuidv4()

  if (payload.gender.toLowerCase() === 'laki-laki') {
    payload.gender = 1
  } else if (payload.gender.toLowerCase() === 'perempuan') {
    payload.gender = 0
  }

  const { error, value } = registerChildValidation(payload)

  if (error) {
    logger.error(`ERR: child - register = ${error.details[0].message}`)
    return h
      .response({
        status: false,
        statusCode: 422,
        message: error.details[0].message
      })
      .code(422)
  }

  try {
    await registerChildToDB(value)

    logger.info('Success register new child')
    return h
      .response({
        status: true,
        statusCode: 201,
        message: 'Register child success'
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

export const getChild = async (request: Request, h: ResponseToolkit) => {
  const {
    params: { id }
  } = request

  if (id) {
    const child = await getChildById(id)
    if (child) {
      logger.info('Success get child data')
      return h.response({
        status: true,
        statusCode: 200,
        data: child
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
    const childs = await getChildsFromDB()
    logger.info('Success get product data')
    return h.response({
      status: true,
      statusCode: 200,
      data: childs
    })
  }
}
