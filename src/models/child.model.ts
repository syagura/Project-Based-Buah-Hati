import mongoose from 'mongoose'

const childSchema = new mongoose.Schema(
  {
    user_id: {
      type: String
    },
    child_id: {
      type: String,
      unique: true
    },
    name: {
      type: String
    },
    birthdate: {
      type: String
    },
    gender: {
      type: Number,
      enum: [1, 0] // 1 (boy), 0 (girl)
    },
    body_weight: {
      type: Number
    },
    body_height: {
      type: Number
    },
    head_circumference: {
      type: Number
    },
    blood_type: {
      type: String,
      enum: ['A', 'B', 'AB', 'O']
    }
  },
  { timestamps: true }
)

const childModel = mongoose.model('child', childSchema)

export default childModel
