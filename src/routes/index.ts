import Hapi from '@hapi/hapi'
import { ArticleRouter } from './article.route'
import { AuthRouter } from './auth.route'
import { ChildRouter } from './child.route'

const allRoutes: Hapi.ServerRoute[] = [
  ...AuthRouter,
  ...ChildRouter,
  ...ArticleRouter
  // Add more routes arrays as needed
]

export const routes = (server: Hapi.Server) => {
  server.route(allRoutes)
}
