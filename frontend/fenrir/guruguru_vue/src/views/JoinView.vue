<template>
<div class="container">
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
            会員登録
          </h2>
        </div>
        <form id="form" class="needs-validation" @submit.prevent="joinRequest">
        
          <div class="mb-3">
            <label for="email" class="form-label">email</label>
            <input type="text" class="form-control" id="email"
                name="email" v-model="email" required />
            <div class="invalid-feedback">請輸入正確的郵件格式</div>
          </div>

          <div class="mb-3">
            <label for="pw" class="form-label">パスワード</label>
            <input type="text" class="form-control" id="pw"
                name="pw" v-model="pw" required />
            <div class="invalid-feedback"></div>
          </div>

          <div class="mb-3">
            <label for="userName" class="form-label">名前</label>
            <input type="text" class="form-control" id="userName"
                name="userName" v-model="userName" required />
            <div class="valid-feedback"></div>
          </div>

          <div class="mb-3">
            <label for="nickName" class="form-label">ニックネーム</label>
            <input type="text" class="form-control" id="nickName"
                name="nickName" v-model="nickName" required />
            <div class="valid-feedback"></div>
          </div>

          <!-- <div class="mb-3">
            <label for="phone" class="form-label">連絡先</label>
            <input type="text" class="form-control" id="phone" required />
            <div class="invalid-feedback"></div>
          </div> -->

          <div class="d-none d-md-flex justify-content-center">
            <button type="submit" class="
                      btn btn-warning btn-lg btn-warning-hover
                      px-5
                      fw-bold
                      rounded-pill
                      mt-2
                    "
                  
            >
              登録
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

</template>

<script>
import axios from 'axios'
import router from 'vue-router';

export default {
  data() {
    return {
      email: null,
      pw: null,
      nickName: null,
      userName: null,
    }
  },
  methods: {
    joinRequest() {
      const user = {
        email: this.email,
        pw: this.pw,
        nickName: this.nickName,
        userName: this.userName
      }
      axios.post('http://localhost:8080/user', user)
        .then((res) => {
          if(res.status === 201)
            this.$router.push('/');
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }
}
</script>

<style>
@import url('../assets/css/header.css');
.container {
  width: 71vw;
}

.form-wrap {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 세로 가운데 정렬 */
  align-items: stretch; /* 가로 가운데 정렬 */
}

.row {
  justify-content: center; /* 가로 가운데 정렬 */
}

</style>
