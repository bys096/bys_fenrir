import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'
import LoginView from '../views/LoginView.vue'
import DetailView from '../views/StoreDetailView.vue'
import StoreRegisterView from '../views/StoreRegisterView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/join',
    name: 'join',
    component: JoinView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/store/detail',
    name: 'detail',
    component: DetailView
  },
  {
    path: '/store/register',
    name: 'register',
    component: StoreRegisterView
  }
  
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router