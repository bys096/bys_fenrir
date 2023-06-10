import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios';


Vue.use(Vuex);

export const store =  new Vuex.Store({
  
  state: {
    token: null,
    uid: null,
    shopDetail: null,
    isAuthenticated: false,
    reviewList: []
  },
  getters: {
    getToken(state) {
      return state.token;
    },
    headers(state) {
      return {
        Authorization: `Bearer ${state.token}`,
      };
    },
    getAuthenticated(state) {
      return state.isAuthenticated;
    }
  },
  mutations: {
    changeShop(state, shop) {
      state.shopDetail = shop;
    },
    setToken(state, { token, uid }) {
      state.token = token;
      state.uid = uid;
      console.log('check from mutations: ' + uid);
    },
    clearToken(state) {
      sessionStorage.removeItem('member_id');
      sessionStorage.removeItem('member_name');
      state.token = null;
    },
    setAuthenticated(state) {
      state.isAuthenticated = !state.isAuthenticated;
    },
    addOneReview(state, review) {
      state.reviewList.push(review);
    }
  },
  actions: {
    login({ commit }, payload) {
      const { token, uid } = payload;
      console.log('check from actions token: ' + token);
      console.log('check from actions uid: ' + uid);
      commit('setToken', { token, uid });
      commit('setAuthenticated');
    },
    logout({ commit }) {
      commit('clearToken');
      commit('setAuthenticated');
    },
    handleTokenExpired({ commit }) {
      alert('로그인 세션이 만료되었습니다. 다시 로그인 후 이용해주세요');
      commit('clearToken');
      location.href = '/';
    },
    
  },
  
  plugins: [createPersistedState()],

})