import Vue from 'vue'
import './filters'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import Element from 'element-ui'
import { systemConfig } from '@/config'
import { apiHelper } from 'vue-x-axios'
import 'vue-touch-ripple/component.css'
import VueTouchRipple from 'vue-touch-ripple'
import '@/plugins'
import '@/assets/index.scss'
// import '@/mock'

Vue.use(VueTouchRipple)
Vue.use(Element, { size: 'small' })

Vue.config.productionTip = false

apiHelper.register({
  systemConfig
}).then(() => {
// 自动登录
  const autoLogin = async () => {
    // 设置是否正在登录状态
    const setLoadinging = () => {
      store.commit('user/setLoginState', false)
    }
    try {
      await store.dispatch('user/loginByStorage')
      if (router.currentRoute.params.module) {
        router.replace({ path: '/' + router.currentRoute.params.module, query: router.currentRoute.query }, setLoadinging, setLoadinging)
      } else {
        store.getters['menu/firEndMenu'] && router.replace({ path: `/${store.getters['menu/firEndMenu'].code}` }, setLoadinging, setLoadinging)
      }
    } catch {
      router.push({ name: 'login' }, setLoadinging, setLoadinging)
    }
  }
  new Vue({
    store,
    router,
    render: h => h(App),
    created () {
      // 如果当前路由需要登录，才进行自动登录
      if (router.currentRoute.meta.needLogin !== false) {
        store.commit('user/setLoginState', true)
        autoLogin()
      }
    }
  }).$mount('#app')
})
