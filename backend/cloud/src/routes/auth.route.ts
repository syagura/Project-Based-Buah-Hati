import Hapi from '@hapi/hapi'
import { requireUser } from '../middlewares/auth'
import { createSession, getSingleUser, refreshSession, registerUser } from '../controllers/auth.controller'

export const AuthRouter: Hapi.ServerRoute[] = [
  {
    method: 'POST',
    path: '/user/register',
    handler: registerUser
  },
  {
    method: 'POST',
    path: '/user/login',
    handler: createSession
  },
  {
    method: 'POST',
    path: '/user/refresh',
    options: {
      pre: [{ method: requireUser }],
      handler: refreshSession
    }
  },
  {
    method: 'GET',
    path: '/user/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: getSingleUser
    }
  }
]
