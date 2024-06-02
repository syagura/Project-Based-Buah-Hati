"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.checkPassword = exports.hashing = void 0;
const bcrypt_1 = __importDefault(require("bcrypt"));
// encode process
const hashing = (password) => {
    return bcrypt_1.default.hashSync(password, 10);
};
exports.hashing = hashing;
// decode process
const checkPassword = (password, userPassword) => {
    return bcrypt_1.default.compareSync(password, userPassword);
};
exports.checkPassword = checkPassword;
//# sourceMappingURL=hashing.js.map