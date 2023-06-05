import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';


Vue.use(Vuex);

export const store =  new Vuex.Store({
  
  state: {
    token: null,
    h1: "hello",
    arr: null,
    shopDetail: null
  },
  getters: {
    
  },
  mutations: {
      testState(state) {
        state.token = "tokenTest complete!!";
      },
      testArr(state, arr) {
        state.arr = arr;
      },
      changeShop(state, shop) {
        state.shopDetail = shop;
      }

  },
  actions: {
    
  },
  
  plugins: [createPersistedState()],

})