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
            <div class="avatar-upload">
              <div class="avatar-edit">
                  <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" @change="readURL" ref="fileUpload"/>
                  <label for="imageUpload"></label>
              </div>
              <div class="avatar-preview">
                  <div id="imagePreview" :style="{backgroundImage: `url(${imagePreview})`}">
                  </div>
              </div>
            </div>

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
            
            <div class="d-none d-md-flex justify-content-center login-btn">
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
      imagePreview: "",
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
      axios.post('/api/user', user)
        .then((res) => {
          if(res.status === 201)
            this.$router.push('/');
        })
        .catch((err) => {
          console.log(err);
        });
    },
    readURL(e) {
      if (e.target.files && e.target.files[0]) {
          let reader = new FileReader();
          reader.onload = (e) => {
              this.imagePreview = e.target.result;
          };
          reader.readAsDataURL(e.target.files[0]);
      }
    },
  }
}
</script>

<style scoped>
@import url('../assets/css/header.css');
.container {
  width: 49vw !important;
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



/* add image upload css */
.avatar-upload {
  position: relative;
  max-width: 205px;
  margin: 30px auto;
  /* cursor: pointer; */
}
.avatar-upload .avatar-edit {
    position: absolute;
    right: 12px;
    z-index: 1;
    top: 10px;
}
.avatar-upload .avatar-edit input {
    display: none;
}
.avatar-upload .avatar-edit label {
    display: inline-block;
    width: 34px;
    height: 34px;
    margin-bottom: 0;
    border-radius: 100%;
    background: #FFFFFF;
    border: 1px solid transparent;
    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.12);
    cursor: pointer;
    font-weight: normal;
    transition: all .2s ease-in-out;
}
.avatar-upload .avatar-edit label:hover {
    background: #f1f1f1;
    border-color: #d6d6d6;
}
.avatar-upload .avatar-edit label:after {
    content: "\f040";
    font-family: 'FontAwesome';
    color: #757575;
    position: absolute;
    top: 6.5px;
    left: 1.3px;
    right: 0;
    text-align: center;
    margin: auto;
}
.avatar-upload .avatar-preview {
    width: 192px;
    height: 192px;
    position: relative;
    border-radius: 100%;
    border: 6px solid #F8F8F8;
    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);
}
.avatar-upload .avatar-preview > div {
    width: 100%;
    height: 100%;
    border-radius: 100%;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
}

input[type=text], input[type=password] {
    text-indent: 5px;
    border: 1px solid #d6d6d6;
    height: 45px;
    line-height: 31px;
    color: #666;
    font-size: 1.3vw;
}
.login-btn {
  margin: 50px;
  /* width: 29vw !important; */
  text-align: center;
}
.login-btn button{
  width: 100%;
}
</style>
