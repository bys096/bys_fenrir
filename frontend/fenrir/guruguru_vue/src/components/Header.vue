<template>
  <header class="border-bottom border-5 border-warning">
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light py-3">
      <div class="container">
        <div class="home-img">
          <a class="navbar-brand py-0" href="/">
            <img src="https://WangShuan.github.io/bootstrap5-project/images/logo.jpeg" alt="拼拼 LOGO" width="111" height="40" class="d-inline-block align-top" />
          </a>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item active">
              <router-link to="/" class="nav-link link-dark px-md-4">検索</router-link>
              <!-- <a class="nav-link link-dark px-md-4" aria-current="page" href="/">検索</a> -->
            </li>
            <li class="nav-item">
              <a class="nav-link link-dark px-md-4" @click="toRegister()" id="rg-link">店舗登録</a>
            </li>
          </ul>
          <div class="justify-content-end d-flex btns" v-if="!isAuthenticated">
            <button 
            @click="$router.push('/join')"
            class="btn btn-warning btn-warning-hover me-3 rounded-pill">
              会員登録
            </button>
            <button 
            @click="$router.push('/login')"
                      class="
                      btn btn-outline-secondary btn-outline-secondary-hover
                      rounded-pill
                      border-2
                    ">
              ログイン
            </button>
          </div>


          <div class="justify-content-end d-flex btns" v-if="isAuthenticated">
            <!-- <button 
            @click="$router.push('/join')"
            class="btn btn-warning btn-warning-hover me-3 rounded-pill">
              会員登録
            </button> -->
            <button 
            @click="logout()"
                      class="
                      btn btn-warning btn-warning-hover me-3 rounded-pill
                      border-2
                    ">
              ログアウト
            </button>
          </div>
        </div>
      </div>
    </nav>
  </div>
</header>

</template>

<script>

export default {
  data() {
    return {
      
    }
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.getAuthenticated;
    }
  },
  components: {
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/').catch(() => {});
    },
    toRegister() {
      console.log(this.isAuthenticated);
      if(this.isAuthenticated)
        this.$router.push('/store/register').catch(() => {});
      else
        this.$router.push('login').catch(() => {});
    }
  }
}
</script>

<style scoped>
/* @import url('../assets/css/header.css'); */
@import url('https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@400;600;700&amp;family=Noto+Sans+TC:wght@400;700&amp;display=swap');
#rg-link {
  cursor: pointer;
}

.btns {
  position: relative;
  left: 40px;
}
.home-img {
  position: relative;
  right: 2.5vw;
}
.navbar-nav {
  position: relative;
  right: 2.5vw;
}
</style>