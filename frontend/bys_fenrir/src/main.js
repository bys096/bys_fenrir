import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Paginate from 'vuejs-paginate'
Vue.use('paginate', Paginate)

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
