import Joi from 'joi'
import ChildType from '../types/child.type'

export const registerChildValidation = (payload: ChildType) => {
  const schema = Joi.object({
    user_id: Joi.string().required(),
    child_id: Joi.string().required(),
    name: Joi.string().required(),
    birthdate: Joi.string().required(),
    gender: Joi.number().required(),
    body_weight: Joi.number().required(),
    body_height: Joi.number().required(),
    head_circumference: Joi.number().required(),
    blood_type: Joi.string().required()
  })
  return schema.validate(payload)
}
