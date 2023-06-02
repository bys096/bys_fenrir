<template>
  <div>
    <HeaderVue></HeaderVue>
    <RouterView></RouterView>
  </div>
</template>

<script>
import headerVue from './components/Header.vue'
import { RouterView } from 'vue-router'

export default {
  data() {
    return {
    }
  },
  components: {
    HeaderVue: headerVue,
    RouterView
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
