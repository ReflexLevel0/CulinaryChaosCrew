const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "https://culinary-chaos-website.onrender.com/",
      changeOrigin: true,
    })
  );
};