import Hapi from '@hapi/hapi'
import { getChild, registerChild } from '../controllers/child.controller'

export const ChildRouter: Hapi.ServerRoute[] = [
  {
    method: 'POST',
    path: '/child/register',
    handler: registerChild
  },
  {
    method: 'GET',
    path: '/child/{user_id}',
    handler: getChild
  },
  {
    method: 'GET',
    path: '/child/{user_id}/{id}',
    handler: getChild
  }
]
