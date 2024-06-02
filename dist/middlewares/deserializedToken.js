"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const jwt_1 = require("../utils/jwt");
const deserializeToken = (server) => {
    server.ext('onRequest', (request, h) => {
        var _a;
        // Implement your token deserialization logic here
        const accessToken = (_a = request.headers.authorization) === null || _a === void 0 ? void 0 : _a.replace(/^Bearer\s/, '');
        if (!accessToken) {
            return h.continue;
        }
        const token = (0, jwt_1.verifyToken)(accessToken);
        if (token.decoded) {
            // Store the decoded token in request.plugins.credentials
            request.plugins = { credentials: token.decoded };
        }
        return h.continue;
    });
};
exports.default = deserializeToken;
//# sourceMappingURL=deserializedToken.js.map