import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios';


Vue.use(Vuex);

export const store =  new Vuex.Store({
  
  state: {
    token: null,
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
    setToken(state, token) {
      state.token = token;
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
    login({ commit }, token) {
      commit('setToken', token);
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
    async createReview({ commit, getters }, review) {
      let response = null;
      try {
        await axios.post(`/api/review`, review, {
          headers: getters.headers
        });
      } catch(err) {
        console.log(err);
        const errRes = err.response.data;
        if(errRes.code === "R002")
          alert(errRes.message);
      }
    }
    
  },
  
  plugins: [createPersistedState()],

})