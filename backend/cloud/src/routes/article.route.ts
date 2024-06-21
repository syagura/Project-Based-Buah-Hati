import Hapi from '@hapi/hapi'
import { requireUser } from '../middlewares/auth'
import { deleteArticle, getArticle, saveArticle, updateArticle } from '../controllers/article.controller'

export const ArticleRouter: Hapi.ServerRoute[] = [
  {
    method: 'POST',
    path: '/article',
    options: {
      pre: [{ method: requireUser }],
      handler: saveArticle
    }
  },
  {
    method: 'GET',
    path: '/article/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: getArticle
    }
  },
  {
    method: 'GET',
    path: '/article',
    options: {
      pre: [{ method: requireUser }],
      handler: getArticle
    }
  },
  {
    method: 'PATCH',
    path: '/article/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: updateArticle
    }
  },
  {
    method: 'DELETE',
    path: '/article/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: deleteArticle
    }
  }
]
