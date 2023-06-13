import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'
import LoginView from '../views/LoginView.vue'
import DetailView from '../views/StoreDetailView.vue'
import StoreRegisterView from '../views/StoreRegisterView.vue'
import AdminView from '../views/AdminView.vue'
import Error404 from '../views/Error404.vue'
import Error500 from '../views/Error500.vue'

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
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/error404',
    name: 'error404',
    component: Error404
  },
  {
    path: '/error500',
    name: 'error500',
    component: Error500
  },
  { path: '/:pathMatch(.*)*', component: Error404 }

  
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router