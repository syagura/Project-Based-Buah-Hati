import mongoose from 'mongoose'

const userSchema = new mongoose.Schema(
  {
    user_id: {
      type: String,
      unique: true
    },
    name: {
      type: String
    },
    username: {
      type: String
    },
    email: {
      type: String,
      unique: true
    },
    password: {
      type: String
    },
    birthdate: {
      type: String
    }
  },
  { timestamps: true }
)

const userModel = mongoose.model('user', userSchema)

export default userModel
