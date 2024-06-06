import Hapi from '@hapi/hapi'
import { AuthRouter } from './auth.route'
import { ChildRouter } from './child.route'

const allRoutes: Hapi.ServerRoute[] = [
  ...AuthRouter,
  ...ChildRouter
  // Add more routes arrays as needed
]

export const routes = (server: Hapi.Server) => {
  server.route(allRoutes)
}
