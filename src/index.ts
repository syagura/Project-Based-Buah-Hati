import express, { Application, Request, Response, NextFunction } from 'express'
import bodyParser from 'body-parser'
import cors from 'cors'
import 'dotenv/config'
import { logger } from './utils/logger'
import { routes } from './routes'

// connect to database
import './utils/connectDB'

import deserializeToken from './middlewares/deserializedToken'

const app: Application = express()
const host: any = process.env.HOST
const port: any = process.env.PORT

// parse body request
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

// cors access handler
app.use(cors())
app.use((req: Request, res: Response, next: NextFunction) => {
  res.setHeader('Access-Control-Allow-Origin', '*')
  res.setHeader('Access-Control-Allow-Methods', '*')
  res.setHeader('Access-Control-Allow-Headers', '*')
  next()
})

routes(app)

app.listen(port, () => {
  logger.info(`Server is listening on http://${host}:${port}`)
})
