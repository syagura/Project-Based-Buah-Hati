import Joi from 'joi'
import AnalysisType from '../types/analysis.type'
import InputType from '../types/input.type'

export const inputAnalysisValidation = (payload: InputType) => {
  const schema = Joi.object({
    child_id: Joi.string().required(),
    gender: Joi.number().required(),
    age: Joi.number().required(),
    height: Joi.number().required(),
    weight: Joi.number().required(),
    headCircumference: Joi.number().required(),
    date: Joi.string().required()
  })
  return schema.validate(payload)
}

export const saveAnalysisValidation = (payload: any) => {
  const schema = Joi.object({
    analysis_id: Joi.string().required(),
    weight_age: Joi.string().required(),
    height_age: Joi.string().required(),
    weight_height: Joi.string().required(),
    headCircumference_age_gender: Joi.string().required(),
  })
  return schema.validate(payload)
}
