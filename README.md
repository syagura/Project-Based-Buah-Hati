# Auth Server API

The following steps will guide you through the installation process of Auth Server API for running locally on your machine:

1. Clone the latest version of this api project from the repository
2. Run `npm install` to install the required Node.js dependencies
3. Run `npm run dev` to start the server
4. Open Postman on your PC to testing the endpoint/path usage

## How to

You can fetch this data with any kind of tools you know (fetch API, Axios, JQuery Ajax, etc.)

### Register a new user

```js
fetch('http://34.136.188.57:5000/user/register', {
  method: 'POST',
  body: JSON.stringify({
    name: 'User test 3',
    username: 'usertest3',
    email: 'user3@yahoo.id',
    password: '1234',
    birthdate: 'January 18th, 2002'
  })
})
  .then((res) => res.json())
  .then((json) => console.log(json))
```

### Login user (create session)

```js
fetch('http://34.136.188.57:5000/user/login', {
  method: 'POST',
  body: JSON.stringify({
    email: 'user3@yahoo.id',
    password: '1234'
  })
})
  .then((res) => res.json())
  .then((json) => console.log(json))
```

### Refresh session

Note!!
Use the refresh token that you got from running the login endpoint

```js
fetch("http://34.136.188.57:5000/user/refresh"), {
  method: "POST",
  body: JSON.stringify({
    refreshToken: `eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsiYWN0aXZlUGF0aHMiOnsicGF0aHMiOnsiX2lkIjoiaW5pdCIsInVzZXJfaWQiOiJpbml0IiwibmFtZSI6ImluaXQiLCJ1c2VybmFtZSI6ImluaXQiLCJlbWFpbCI6ImluaXQiLCJwYXNzd29yZCI6ImluaXQiLCJiaXJ0aGRhdGUiOiJpbml0IiwiY3JlYXRlZEF0IjoiaW5pdCIsInVwZGF0ZWRBdCI6ImluaXQiLCJfX3YiOiJpbml0In0sInN0YXRlcyI6eyJpbml0Ijp7Il9pZCI6dHJ1ZSwidXNlcl9pZCI6dHJ1ZSwibmFtZSI6dHJ1ZSwidXNlcm5hbWUiOnRydWUsImVtYWlsIjp0cnVlLCJwYXNzd29yZCI6dHJ1ZSwiYmlydGhkYXRlIjp0cnVlLCJjcmVhdGVkQXQiOnRydWUsInVwZGF0ZWRBdCI6dHJ1ZSwiX192Ijp0cnVlfX19LCJza2lwSWQiOnRydWV9LCIkaXNOZXciOmZhbHNlLCJfZG9jIjp7Il9pZCI6IjY2NTljNjMyM2EzMGM0NTM0ZjNmOGE4ZSIsInVzZXJfaWQiOiIwNzM3YTIyMi04YzhhLTQ0YmYtYWFiYy1iOGI4NGIxMGI3OGYiLCJuYW1lIjoiVXNlciB0ZXN0IDEiLCJ1c2VybmFtZSI6InVzZXJ0ZXN0MSIsImVtYWlsIjoidXNlcjFAeWFob28uaWQiLCJwYXNzd29yZCI6IiQyYiQxMCQ0LnlRZG5MaU9VV2QyRWtDdUdadVllMlJ5OWlSWUh6NURLZDNRbmFVdkFTNXcxM0NXLnRRQyIsImJpcnRoZGF0ZSI6Ik9jdG9iZXIgMTB0aCwgMjAwMSIsImNyZWF0ZWRBdCI6IjIwMjQtMDUtMzFUMTI6NDQ6MzQuMjQ5WiIsInVwZGF0ZWRBdCI6IjIwMjQtMDUtMzFUMTI6NDQ6MzQuMjQ5WiIsIl9fdiI6MH0sImlhdCI6MTcxNzE2ODAzOSwiZXhwIjoxNzQ4NzI1NjM5fQ.bfIpKuc4F1CfT-IsZTYyc_ODEtWxcMAZJmiZDK6KVayLgFvgD46oQvXPG0q5MNBCeVZonzY8AlxU3DUTff7BwND-F5bk2471KtDCwtQtOc1T9N7vwXLIQcC5X3IsRsaRNkg-EIlNrgr2UoJG0Kixw_E3G1aT-l4bX6yRlfdrYEOUKdOeSe6jAtjXCa2e8xqWDyqwxQm5cOF8bNx001FpZJr7PDcw5lA7ZaFUzgoq49nu-h6VobwfRBPz0dvXRA-Wo-HGElmAwV-TwLZH-ttrKLO4iZGTBWfQLj8WPCYp0zWnTtUHs0KAXnzmjEZWmozqmSKS0IDsChwVBrWWef8EMA`
  }),
})
  .then((res) => res.json())
  .then((json) => console.log(json));
```

=======
