<template>
  <div id="app">
    <HeaderVue></HeaderVue>
    <div id="content">
      <RouterView ref="routerView"></RouterView>
    </div>
    <FooterVue></FooterVue>
  </div>
</template>

<script>
import { RouterView } from 'vue-router'
import headerVue from './components/Header.vue'
import footerVue from './components/Footer.vue'
import { mapState } from 'vuex'


export default {
  components: {
    HeaderVue: headerVue,
    RouterView,
    FooterVue: footerVue
  },
  computed: {
    ...mapState(['isTokenExpired'])
  },
  created() {
    if (this.isTokenExpired) {
      alert('logout');
      this.$store.dispatch('logout');
    }
  }
  
}
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css);

html, body {
  margin: 0;
  padding: 0;
}

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  font-family: 'Spoqa Han Sans Neo', 'sans-serif' !important;
}

#content {
  flex-grow: 1;
}
</style>