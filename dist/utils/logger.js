"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.logger = void 0;
const pino_1 = __importDefault(require("pino"));
const pino_pretty_1 = __importDefault(require("pino-pretty"));
const moment_1 = __importDefault(require("moment"));
exports.logger = (0, pino_1.default)({
    base: {
        pid: false
    },
    timestamp: () => `,"time":"${(0, moment_1.default)().format()}"`
}, (0, pino_pretty_1.default)());
//# sourceMappingURL=logger.js.map