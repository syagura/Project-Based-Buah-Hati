import { Request, Response, NextFunction } from 'express'
import { verifyToken } from '../utils/jwt'

const deserializeToken = async (req: Request, res: Response, next: NextFunction) => {
  const accessToken = req.headers.authorization?.replace(/^Bearer\s/, '')
  if (!accessToken) {
    return next()
  }

  const token: any = verifyToken(accessToken)
  if (token.decoded) {
    res.locals.user = token.decoded
    return next()
  }
  return next()
}

export default deserializeToken
