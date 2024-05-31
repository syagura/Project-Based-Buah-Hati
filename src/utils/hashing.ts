import bcrypt from 'bcrypt'

// encode process
export const hashing = (password: string) => {
  return bcrypt.hashSync(password, 10)
}

// decode process
export const checkPassword = (password: string, userPassword: string) => {
  return bcrypt.compareSync(password, userPassword)
}
