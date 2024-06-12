import mongoose from 'mongoose'

const articleSchema = new mongoose.Schema(
  {
    article_id: {
      type: String
    },
    title: {
      type: String
    },
    desc: {
      type: String
    },
    date: {
      type: String
    }
  },
  { timestamps: true }
)

const articleModel = mongoose.model('article', articleSchema)

export default articleModel
