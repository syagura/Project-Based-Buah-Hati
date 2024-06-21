import Hapi from '@hapi/hapi'

export const requireUser = async (request: Hapi.Request, h: Hapi.ResponseToolkit) => {
  const user = request.headers.authorization

  if (!user) {
    return h.response({ message: 'Forbidden' }).code(403).takeover()
  }

  return h.continue
}
