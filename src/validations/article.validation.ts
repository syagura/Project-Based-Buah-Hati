import Joi from 'joi'
import ArticleType from '../types/article.type'

export const saveArticleValidation = (payload: ArticleType) => {
  const schema = Joi.object({
    article_id: Joi.string().required(),
    title: Joi.string().required(),
    desc: Joi.string().required(),
    date: Joi.string().required()
  })
  return schema.validate(payload)
}

export const updateArticleValidation = (payload: ArticleType) => {
  const schema = Joi.object({
    title: Joi.string().allow('', null),
    desc: Joi.string().allow('', null),
    date: Joi.string().allow('', null)
  })
  return schema.validate(payload)
}
