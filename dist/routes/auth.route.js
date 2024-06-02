"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AuthRouter = void 0;
const auth_controller_1 = require("../controllers/auth.controller");
exports.AuthRouter = [
    {
        method: 'POST',
        path: '/user/register',
        handler: auth_controller_1.registerUser
    },
    {
        method: 'POST',
        path: '/user/login',
        handler: auth_controller_1.createSession
    },
    {
        method: 'POST',
        path: '/user/refresh',
        handler: auth_controller_1.refreshSession
    }
];
//# sourceMappingURL=auth.route.js.map