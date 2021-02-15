module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  lintOnSave: false,
  devServer: {
    proxy: "http://localhost:80",
    port: 8081,
  },

  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  },

  transpileDependencies: ["vuetify"]
};

// "use strict";

// // https://webpack.js.org/configuration/dev-server/#devserver-proxy

// const targetHost = "localhost";
// const targetPort = 80;

// const port = 8081;
// let cookie;

// const replaceCookie = function(cookie) {
//   return cookie.replace(/\s+domain=[^\s;]+;?/, "").replace(/\s+secure;?/, "");
// };

// module.exports = {
//   outputDir: "../src/main/resources/static",
//   indexPath: "../static/index.html",

//   devServer: {
//     port: port,
//     clientLogLevel: "debug",
//     historyApiFallback: true,
//     hot: true,
//     inline: true,
//     publicPath: `http://localhost:${port}/`,
//     transportMode: "sockjs",
//     headers: {
//       "Access-Control-Allow-Origin": "*"
//     },
//     proxy: {
//       "^/ws": {
//         target: `http://${targetHost}:${targetPort}`,
//         changeOrigin: true,
//         ws: true,
//         onProxyReq: proxyReq => {
//           if (proxyReq.getHeader("origin")) {
//             proxyReq.setHeader("origin", `http://${targetHost}:${targetPort}`);
//           }

//           if (proxyReq.getHeader("set-cookie")) {
//             proxyReq.headers["set-cookie"] = proxyReq.headers["set-cookie"].map(
//               replaceCookie
//             );
//           }

//           if (cookie) proxyReq.setHeader("Cookie", cookie);
//         },
//         onProxyRes: proxyRes => {
//           if (proxyRes.headers["set-cookie"]) {
//             cookie = proxyRes.headers["set-cookie"] = proxyRes.headers[
//               "set-cookie"
//             ].map(replaceCookie);
//           }
//         }
//       },
//       "^/(sse|api|sign|resources)": {
//         target: `http://${targetHost}:${targetPort}`,
//         changeOrigin: true,
//         onProxyRes: proxyRes => {
//           if (proxyRes.headers["set-cookie"]) {
//             cookie = proxyRes.headers["set-cookie"] = proxyRes.headers[
//               "set-cookie"
//             ].map(replaceCookie);
//           }
//         }
//       }
//     }
//   },

//   pluginOptions: {
//     moment: {
//       locales: ["ko_kr"]
//     }
//   }
// };
