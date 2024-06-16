# Menggunakan image node sebagai base
FROM node:18

# Menentukan working directory
WORKDIR /usr/src/app

# Menyalin package.json dan package-lock.json
COPY package*.json ./

# Menginstal dependensi
RUN npm install

# Menyalin seluruh kode ke dalam container
COPY . .

# Membangun aplikasi TypeScript -> JavaScript
RUN npm run build

# Mengekspos port yang digunakan oleh Hapi.js
EXPOSE 3000

# Menjalankan aplikasi
CMD ["npm", "run", "start"]
