import Hapi from '@hapi/hapi'
import { AuthRouter } from './auth.route'

const allRoutes: Hapi.ServerRoute[] = [
  ...AuthRouter
  // Add more routes arrays as needed
]

export const routes = (server: Hapi.Server) => {
  server.route(allRoutes)
}
