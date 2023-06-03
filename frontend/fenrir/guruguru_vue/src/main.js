import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import * as VueGoogleMaps from 'vue2-google-maps'


Vue.config.productionTip = false;
Vue.use(VueGoogleMaps, {
  load: {
    // key: 'AIzaSyAil4xXCaukSc_hw2Bayr9DFrUf_T44N7A',
    key: 'AIzaSyDNYcgyxV4LTXB5j2iwlq0YXv19a5dIOP8',
    libraries: 'places', // 필요한 라이브러리 추가
    language: 'ja'
  },
})

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
