import Hapi from '@hapi/hapi'
import { createSession, refreshSession, registerUser } from '../controllers/auth.controller'

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
    handler: refreshSession
  }
]
