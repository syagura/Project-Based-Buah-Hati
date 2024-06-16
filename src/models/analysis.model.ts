import mongoose from 'mongoose'

const analysisSchema = new mongoose.Schema(
  {
    analysis_id: {
      type: String
    },
    child_id: {
      type: String
    },
    weight_age: { /** weight/age */
      type: String
    },
    height_age: { /** height/age */
      type: String
    },
    weight_height: { /** weight/height */
      type: String
    },
    headCircumference_age_gender: { /** headCircumference/(age&gender) */
      type: String
    },
    date: {
      type: String
    }
  },
  { timestamps: true }
)

const analysisModel = mongoose.model('analyze', analysisSchema)

export default analysisModel
