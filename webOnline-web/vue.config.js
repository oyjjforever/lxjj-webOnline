const path = require('path')

module.exports = {
  // 基本路径
  publicPath: process.env.NODE_ENV === 'production' ? '' : '/',
  // 用于嵌套生成的静态资产（js，css，img，fonts）的目录
  assetsDir: 'static',
  transpileDependencies: ['vue-x-axios'],
  // webpack配置
  chainWebpack: config => {
    // 设置静态目录别名
    config
      .resolve
      .alias
      .set('public', path.join(__dirname, 'public'))
    //  设置外部资源
    config.externals({
      'vue': 'Vue',
      'vuex': 'Vuex',
      'axios': 'axios',
      'moment': 'moment',
      'vue-router': 'VueRouter',
      'element-ui': 'ELEMENT'
    })
  },
  // webpack-dev-server 相关配置
  devServer: {
    disableHostCheck: true,
    // proxy: {
    //   '/*': {
    //     ws: false,
    //     target: 'http://localhost:10000'
    //   }
    // },
    overlay: {
      warnings: true,
      errors: true
    }
  }
}
