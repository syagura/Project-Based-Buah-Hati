"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.verifyToken = exports.signJWT = void 0;
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
const environment_1 = __importDefault(require("../config/environment"));
const signJWT = (payload, options) => {
    return jsonwebtoken_1.default.sign(payload, environment_1.default.jwt_private, Object.assign(Object.assign({}, (options && options)), { algorithm: 'RS256' }));
};
exports.signJWT = signJWT;
const verifyToken = (token) => {
    try {
        const decoded = jsonwebtoken_1.default.verify(token, environment_1.default.jwt_public);
        return {
            valid: true,
            expired: false,
            decoded
        };
    }
    catch (error) {
        return {
            valid: false,
            expired: error.message === 'token is expired or not eligible to use',
            decoded: null
        };
    }
};
exports.verifyToken = verifyToken;
//# sourceMappingURL=jwt.js.map