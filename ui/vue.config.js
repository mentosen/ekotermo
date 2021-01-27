const path = require("path");
// const VersionFile = require('webpack-version-file-plugin');
module.exports = {
  runtimeCompiler: true,

  configureWebpack: {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src/'),
      }
    },
    // plugins: [
    //   new VersionFile({
    //     packageFile:path.join(__dirname, '../ticket-ui/package.json'),
    //     template: path.join(__dirname, '../ticket-ui/src/version.ejs'),
    //     outputFile: path.join(__dirname, '../ticket-ui/public/v.json')
    //   })
    // ]
  },
  css: {
    sourceMap: false
  },
  devServer: { //TODO: Remove proxy config server without proxy, see systaro-test-task
    port: '8080',
    proxy: {
      '^/api': {
        target: 'http://localhost:8081',
        ws: true,
        changeOrigin: true
      },
    },
  },
  productionSourceMap: process.env.NODE_ENV !== 'production',
  lintOnSave: process.env.NODE_ENV !== 'production'
}