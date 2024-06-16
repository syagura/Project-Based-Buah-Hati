import Hapi from '@hapi/hapi'
import { requireUser } from '../middlewares/auth'
import { getAnalysis, saveAnalysis } from '../controllers/analysis.controller'

export const AnalysisRouter: Hapi.ServerRoute[] = [
  {
    method: 'POST',
    path: '/analyze',
    options: {
      pre: [{ method: requireUser }],
      handler: saveAnalysis
    }
  },
  {
    method: 'GET',
    path: '/analyze/{id}',
    options: {
      pre: [{ method: requireUser }],
      handler: getAnalysis
    }
  },
  {
    method: 'GET',
    path: '/analyze',
    options: {
      pre: [{ method: requireUser }],
      handler: getAnalysis
    }
  }
]
