const path = require('path');
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave: false,
  devServer: {
    proxy: { 
        '/v1': { 
            target: 'https://webservice.recruit.co.jp/hotpepper/gourmet/v1/', 
            changeOrigin: true, 
            pathRewrite:{ "^/v1" : '' } 
        },
        '/api': { 
            target: 'http://localhost:8080/api', 
            changeOrigin: true, 
            pathRewrite:{ "^/api" : '' } 
        }
    },
  headers: { "Access-Control-Allow-Origin": "*" }
  }, 
})
