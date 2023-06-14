import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios';


Vue.use(Vuex);

export const store =  new Vuex.Store({
  
  state: {
    token: null,
    tokenExpiration: null,
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
    },
    isTokenExpired(state) {
      if (!state.tokenExpiration) {
        return true; // 토큰 만료 시간이 없는 경우
      }
      const now = new Date();
      const expiration = new Date(state.tokenExpiration);
      return now >= expiration;
    },
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
    setTokenExpiration(state, expiration) {
      state.tokenExpiration = expiration;
    },
    clearToken(state) {
      state.token = null;
      state.tokenExpiration = null;

    },
    setAuthenticated(state) {
      state.isAuthenticated = !state.isAuthenticated;
    },
    addOneReview(state, review) {
      state.reviewList.push(review);
    }
  },
  actions: {
    login({ commit, dispatch }, payload) {
      const { token, uid, expire } = payload;
      console.log('check from actions token: ' + token);
      console.log('check from actions uid: ' + uid);
      commit('setToken', { token, uid });
      commit('setAuthenticated');
      commit('setTokenExpiration', expire);
      dispatch('startSessionTimeout');
      // alert('セッションの有効期限が切れました。');
    },
    startSessionTimeout({ commit, dispatch, state }) {
      const expirationTime = state.tokenExpiration;
      const currentTime = Date.now();
      const delay = expirationTime - currentTime;

      console.log('expire: ' + expirationTime);
      console.log('current: ' + currentTime);
      if (delay > 0) {
        setTimeout(() => {
          alert('セッションの有効期限が切れました。');
          dispatch('logout');
        }, delay);
      }
    },
    logout({ commit }) {
      commit('clearToken');
      commit('setAuthenticated');
    },
    handleTokenExpired({ commit }) {
      commit('clearToken');
      location.href = '/';
    },
    
  },
  
  plugins: [
    createPersistedState(),
    (store) => {
      store.dispatch('startSessionTimeout');
    }
  ],

})