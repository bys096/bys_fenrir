<template>
  <div>
    <HeaderVue></HeaderVue>
    <RouterView></RouterView>
    <FooterVue></FooterVue>
  </div>
</template>

<script>
import { RouterView } from 'vue-router'
import headerVue from './components/Header.vue'
import footerVue from './components/Footer.vue'


export default {
  data() {
    return {
    }
  },
  components: {
    HeaderVue: headerVue,
    RouterView,
    FooterVue: footerVue
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>