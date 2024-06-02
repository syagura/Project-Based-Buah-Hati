import Hapi from '@hapi/hapi'
import dotenv from 'dotenv'
import { logger } from './utils/logger'
import { routes } from './routes'
import './utils/connectDB'
import deserializeToken from './middlewares/deserializedToken'

dotenv.config()

const host: any = process.env.HOST
const port: any = process.env.PORT

const init = async () => {
  const server = Hapi.server({
    host: host,
    port: port,
    routes: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  })

  // Register middleware
  deserializeToken(server)

  // Register routes
  routes(server)

  await server.start()
  logger.info(`Server running on ${server.info.uri}`)
}

process.on('unhandledRejection', (err) => {
  logger.error(err)
  process.exit(1)
})

init()
