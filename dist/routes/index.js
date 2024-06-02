"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.routes = void 0;
const auth_route_1 = require("./auth.route");
const allRoutes = [
    ...auth_route_1.AuthRouter
    // Add more routes arrays as needed
];
const routes = (server) => {
    server.route(allRoutes);
};
exports.routes = routes;
//# sourceMappingURL=index.js.map