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
            店舗登録
          </h2>
        </div>
        <!-- <h3>店舗のオーナーを証明するシステムは織り込んでおりません。</h3> -->
        <form id="form" class="needs-validation" @submit.prevent="registerRequest">
          <div class="mb-3">
            <label for="email" class="form-label">店舗ID</label>
            <input type="text" 
                :class="isStoreValid ? 'form-control is-valid' : 'form-control is-invalid'"
                id="email"
                @keyup="getStoreLoad()"
                name="userName" v-model="storeId" required />
            <div :class="!isStoreValid ? 'invalid-feedback' : 'span-none'">店舗のオーナーを証明するシステムは織り込んでおりません。</div>
            <div :class="!isStoreValid ? 'span-none' : 'valid-feedback'">正しい店のIDです。</div>
            <div>
              <input type="file" @change="getUrl()" ref="file" required />
            </div>
          </div>

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

export default {
  data() {
    return {
      storeId: null,
      storeName: null,
      isStoreValid: false,

      presignedUrl: null,
      encodedFileName: null,
      selectedFile: null
    }
  },
  methods: {
    async registerRequest() {

      


      const store = {
        storeCode: this.storeId,
        storeName: this.storeName,
        fileName: this.encodedFileName
      }
      console.log('전송전 store 정보확인');
      console.log(store);



      await axios.post('/api/or', store, {
        headers: this.$store.getters.headers
      })
        .then((res) => {
          if(res.status === 201) {
            this.uploadFile();
            this.$router.push('/');
          }
        })
        .catch((err) => {
          console.log(err);
          const errContext = err.response.data;
          // const errCode = err.response.data.code;
          // const errMsg = err.response.data.message;
          if(errContext.code === "O001") {
            console.log(errContext);
            alert(errContext.message);
          }
        });
    },
    async getStoreLoad() {
      if(this.storeId != null && this.storeId !== "") {
        await axios.get('/v1/?key=a6d3bb26218771ec&format=json', {
          params: {
            id: this.storeId
          }
        })
          .then((res) => {
            const resLen = res.data.results.shop.length;
            if(resLen == 0 || resLen == null) {
              this.isStoreValid = false;
            }
            else if(this.storeId === res.data.results.shop[0].id){
              this.isStoreValid = true;
              this.storeName = res.data.results.shop[0].name;
            }

          })
          .catch((err) => console.log(err));
        }
      },



      async getUrl() {
        try {
        const selectedFile = this.$refs.file.files[0];
        const maxSize = 5 * 1024 * 1024;
        const fileSize = selectedFile.size;
        if (fileSize > maxSize) {
          alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
          return;
        }
        const filename = selectedFile.name;
        const filetype = selectedFile.type;
        console.log(filetype);
        const res = await axios.get('/api/aws/s3/url', {
          params: { filename, filetype },
          headers: this.$store.getters.headers
        });
        const encodedFileName = res.data.encodedFileName
        const presignedUrl = res.data.preSignedUrl;

        this.presignedUrl = presignedUrl;
        this.encodedFileName = encodedFileName;
        this.selectedFile = selectedFile;

        console.log('presign');
        console.log(res);
        console.log('presignedUrl: ' + presignedUrl);
        console.log('endcodedFileName: ' + encodedFileName);

        
    } catch(err) {
      console.log(err);
    }
  },
  async uploadFile() {
    const presignedUrl = this.presignedUrl;
    const selectedFile = this.selectedFile;
    const encodedFileName = this.encodedFileName;
    await axios.put(presignedUrl, selectedFile)
          .then((res) => {
            this.image = presignedUrl + encodedFileName;
            console.log(res);
            console.log('s3 업로드 완료');
            
          })
        .catch(err => {
          if (err.response.status === 419) {
            this.$store.dispatch('handleTokenExpired');
          } 
          else console.error('s3 업로드 오류:', err);
        })
  },
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

.span-none {
  display: none;
}

</style>
