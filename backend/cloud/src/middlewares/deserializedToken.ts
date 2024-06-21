import Hapi from '@hapi/hapi'
import { verifyToken } from '../utils/jwt'

const deserializeToken = (server: Hapi.Server) => {
  server.ext('onRequest', (request, h) => {
    // Implement your token deserialization logic here
    const accessToken = request.headers.authorization?.replace(/^Bearer\s/, '')
    if (!accessToken) {
      return h.continue
    }

    const token: any = verifyToken(accessToken)
    if (token.decoded) {
      // Store the decoded token in request.plugins.credentials
      request.plugins = { credentials: token.decoded }
    }
    return h.continue
  })
}

export default deserializeToken
