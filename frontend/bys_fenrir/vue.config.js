const path = require('path');
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: { 
        '/v1': { 
            target: 'https://webservice.recruit.co.jp/hotpepper/gourmet/v1/', 
            changeOrigin: true, 
            pathRewrite:{ "^/v1" : '' } 
        } 
    },
  headers: { "Access-Control-Allow-Origin": "*" }
  }, 
})
