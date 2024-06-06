import Hapi from '@hapi/hapi'
import { requireUser } from '../middlewares/auth'
import { getChild, registerChild } from '../controllers/child.controller'

export const ChildRouter: Hapi.ServerRoute[] = [
  {
    method: 'POST',
    path: '/child/register',
    options: {
      pre: [{ method: requireUser }],
      handler: registerChild
    }
  },
  {
    method: 'GET',
    path: '/child/{user_id}',
    options: {
      pre: [{ method: requireUser }],
      handler: getChild
    }
  },
  {
    method: 'GET',
    path: '/child/{user_id}/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: getChild
    }
  }
]
