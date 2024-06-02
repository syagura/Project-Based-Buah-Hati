import Joi from 'joi'
import UserType from '../types/user.type'

export const registerUserValidation = (payload: UserType) => {
  const schema = Joi.object({
    user_id: Joi.string().required(),
    name: Joi.string().required(),
    username: Joi.string().allow(''),
    email: Joi.string().required(),
    password: Joi.string().required(),
    birthdate: Joi.string().allow('')
  })
  return schema.validate(payload)
}

export const createSessionValidation = (payload: UserType) => {
  const schema = Joi.object({
    email: Joi.string().required(),
    password: Joi.string().required()
  })
  return schema.validate(payload)
}

export const refreshSessionValidation = (payload: UserType) => {
  const schema = Joi.object({
    refreshToken: Joi.string().required()
  })
  return schema.validate(payload)
}
