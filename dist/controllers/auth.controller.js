"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.refreshSession = exports.createSession = exports.registerUser = void 0;
const auth_service_1 = require("../services/auth.service");
const auth_validation_1 = require("../validations/auth.validation");
const logger_1 = require("../utils/logger");
const hashing_1 = require("../utils/hashing");
const uuid_1 = require("uuid");
const jwt_1 = require("../utils/jwt");
const registerUser = (request, h) => __awaiter(void 0, void 0, void 0, function* () {
    const payload = request.payload;
    payload.user_id = (0, uuid_1.v4)();
    const { error, value } = (0, auth_validation_1.registerUserValidation)(payload);
    if (error) {
        logger_1.logger.error(`ERR: user - register = ${error.details[0].message}`);
        return h
            .response({
            status: false,
            statusCode: 422,
            message: error.details[0].message
        })
            .code(422);
    }
    try {
        value.password = `${(0, hashing_1.hashing)(value.password)}`;
        yield (0, auth_service_1.registerUserToDB)(value);
        logger_1.logger.info('Success register new user');
        return h
            .response({
            status: true,
            statusCode: 201,
            message: 'Register user success'
        })
            .code(201);
    }
    catch (error) {
        logger_1.logger.error('SERVER ERROR');
        return h
            .response({
            status: false,
            statusCode: 500,
            message: error
        })
            .code(500);
    }
});
exports.registerUser = registerUser;
const createSession = (request, h) => __awaiter(void 0, void 0, void 0, function* () {
    const payload = request.payload;
    const { error, value } = (0, auth_validation_1.createSessionValidation)(payload);
    if (error) {
        logger_1.logger.error(`ERR: user - create session = ${error.details[0].message}`);
        return h
            .response({
            status: false,
            statusCode: 422,
            message: error.details[0].message
        })
            .code(422);
    }
    try {
        const user = yield (0, auth_service_1.findUserByEmail)(value.email);
        const isValid = (0, hashing_1.checkPassword)(value.password, user.password);
        if (!isValid) {
            return h
                .response({
                status: false,
                statusCode: 401,
                message: 'Invalid email or password'
            })
                .code(401);
        }
        const accessToken = (0, jwt_1.signJWT)(Object.assign({}, user), { expiresIn: '10s' });
        const refreshToken = (0, jwt_1.signJWT)(Object.assign({}, user), { expiresIn: '1y' });
        logger_1.logger.info('Success login user');
        return h
            .response({
            status: true,
            statusCode: 200,
            message: 'Login user success',
            data: { accessToken, refreshToken }
        })
            .code(200);
    }
    catch (error) {
        logger_1.logger.error('SERVER ERROR');
        return h
            .response({
            status: false,
            statusCode: 500,
            message: error
        })
            .code(500);
    }
});
exports.createSession = createSession;
const refreshSession = (request, h) => __awaiter(void 0, void 0, void 0, function* () {
    const payload = request.payload;
    const { error, value } = (0, auth_validation_1.refreshSessionValidation)(payload);
    if (error) {
        logger_1.logger.error(`ERR: user - refresh session = ${error.details[0].message}`);
        return h
            .response({
            status: false,
            statusCode: 422,
            message: error.details[0].message
        })
            .code(422);
    }
    try {
        const { decoded } = (0, jwt_1.verifyToken)(value.refreshToken);
        const user = yield (0, auth_service_1.findUserByEmail)(decoded._doc.email);
        if (!user) {
            return h
                .response({
                status: false,
                statusCode: 401,
                message: 'User not found'
            })
                .code(401);
        }
        const accessToken = (0, jwt_1.signJWT)(Object.assign({}, user), { expiresIn: '1d' });
        logger_1.logger.info('Success refresh token');
        return h
            .response({
            status: true,
            statusCode: 200,
            message: 'Refresh token success',
            token: accessToken
        })
            .code(200);
    }
    catch (error) {
        logger_1.logger.error('SERVER ERROR');
        return h
            .response({
            status: false,
            statusCode: 500,
            message: error
        })
            .code(500);
    }
});
exports.refreshSession = refreshSession;
//# sourceMappingURL=auth.controller.js.map