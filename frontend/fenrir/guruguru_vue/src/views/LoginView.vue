<template>
<div class="container">
  <div>
  <div class="my-4">
    <div class="row g-md-5">
      <div class="col-lg-8 form-wrap">
        <div class="position-relative my-4">
          <div class="progress bg-warning" style="height: 3px">
            <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
            </div>
          </div>
          <h2 class="
                    position-absolute
                    top-0
                    start-50
                    translate-middle
                    bg-white
                    text-center
                    px-3
                    fs-lg-4 fs-mobile-2
                    fw-bold
                  ">
            ログイン
          </h2>
        </div>
        <form id="form" class="needs-validation" @submit.prevent="loginRequest">
        
          <div class="mb-3">
            <label for="email" class="form-label">email</label>
            <input type="text" class="form-control" id="email"
                name="email" v-model="email" required />
            <div class="invalid-feedback">請輸入正確的郵件格式</div>
          </div>

          <div class="mb-3">
            <label for="pw" class="form-label">パスワード</label>
            <input type="password" class="form-control" id="pw"
                name="pw" v-model="pw" required />
          </div>
          <div class="feedback-wrap">

            <div class="feedback" :style="{ visibility: isWrong ? 'visible' : 'hidden' }">IDまたはパスワードが間違っています。</div>
          </div>

          <div class="d-none d-md-flex justify-content-center login-btn">
            <button type="submit" class="
                      btn btn-warning btn-lg btn-warning-hover
                      px-5
                      fw-bold
                      rounded-pill
                      mt-2
                    "
                  
            >
              ログイン
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <Ribbon></Ribbon>
</div>
</div>
</template>

<script>
import axios from 'axios'
import ribbon from '../components/GitRibbon.vue'
// import router from 'vue-router';

export default {
  components: {
    Ribbon: ribbon
  },
  data() {
    return {
      email: null,
      pw: null,
      isWrong: null
    }
  },
  methods: {
    loginRequest() {
      const user = {
        email: this.email,
        pw: this.pw
      }
      axios.post('/api/user/login', user)
        .then((res) => {
          const accessToken = res.data.accessToken;
          const uid = res.data.uid;
          const expire = res.data.accessTokenExpiresIn;
          this.$store.dispatch('login', { token: accessToken, uid: uid, expire: expire });
          this.$router.push('/');
        })
        .catch((err) => {
          const code = err.response.data.code;
          if(code === "U001") {
            this.isWrong = true;
          }
          // alert(code);
          console.log(err);
        });
    }
  }
}
</script>

<style scoped>
@import url('../assets/css/header.css');
.container {
  display: flex !important;
  flex-direction: column !important;
  justify-content: center !important; 
  align-items: center !important; 
  position: relative;
  left: 1.5vw;
}
.container > div:first-child{
  width: 49vw !important;
}

.form-wrap {
  /* display: flex !important; */
  /* flex-direction: column !important; */
  /* justify-content: center !important;  */
  /* align-items: stretch !important;  */
}

.row {
  /* justify-content: center !important;  */
}
.login-btn {
  position: relative;
  top: 1vh;
}
.login-btn button{
  width: 49vw;
}
input[type=text], input[type=password] {
    text-indent: 5px;
    border: 1px solid #d6d6d6;
    height: 45px;
    line-height: 31px;
    color: #666;
    font-size: 1.3vw;
}
.feedback-wrap {
  text-align: center;
  color: red;
  /* position: relative; */
  /* left: 5px; */
}


</style>
