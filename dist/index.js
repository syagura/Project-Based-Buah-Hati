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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const hapi_1 = __importDefault(require("@hapi/hapi"));
const dotenv_1 = __importDefault(require("dotenv"));
const logger_1 = require("./utils/logger");
const routes_1 = require("./routes");
require("./utils/connectDB");
const deserializedToken_1 = __importDefault(require("./middlewares/deserializedToken"));
dotenv_1.default.config();
const host = process.env.HOST;
const port = process.env.PORT;
const init = () => __awaiter(void 0, void 0, void 0, function* () {
    const server = hapi_1.default.server({
        host: host,
        port: port,
        routes: {
            cors: {
                origin: ['*'],
                additionalHeaders: ['cache-control', 'x-requested-with']
            }
        }
    });
    // Register middleware
    (0, deserializedToken_1.default)(server);
    // Register routes
    (0, routes_1.routes)(server);
    yield server.start();
    logger_1.logger.info(`Server running on ${server.info.uri}`);
});
process.on('unhandledRejection', (err) => {
    logger_1.logger.error(err);
    process.exit(1);
});
init();
//# sourceMappingURL=index.js.map