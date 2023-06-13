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
            <div>
              <label for="store_code" class="form-label">店舗ID</label>
              <input type="text" 
                  :class="isStoreValid ? 'form-control is-valid' : 'form-control is-invalid'"
                  id="store_code"
                  data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                  @keyup="getStoreLoadByStoreId()"
                  name="userName" v-model="storeId" required />
              <div :class="!isStoreValid ? 'span-none' : 'valid-feedback'">正しい店のIDです。</div>
            </div>

            <div>
              <label for="email" class="form-label">店舗名</label>
              <input type="text" class="form-control" id="email" name="userName" v-model="storeName" required />
            </div>

            <div>
              <label for="email" class="form-label">アドレス</label>
              <input type="text" class="form-control" id="email" name="userName" v-model="storeAddress" required />
            </div>

              <div>
                <label for="email" class="form-label">店舗ID</label>
                <input type="file" class="form-control" @change="getUrl()" ref="file" required />
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

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">会員情報修正</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- modal body -->
        <v-card>
          <v-container fluid>
            <v-row>
              <v-col cols="4">
                <div>お店の検索</div>
              </v-col>
              <v-col cols="8">
                <v-text-field
                  label="Search"
                  v-model="searchKeyword"
                  @input="getStoreLoad"
                ></v-text-field>
                  <!-- @keyup="getStoreLoad" -->
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </div>
      <div class="sch-result">
        <table class="table table-hover store-list">
          <thead>
            <tr>
              <td class="text-center">店名</td>
              <td class="text-center">アドレス</td>
              <td class="text-center">ストアコード</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(result, index) in searchResults" :key="index" data-bs-dismiss="modal" @click="selectStore(result)">
              <td class="align-middle text-center">{{ shorten(result.name) }}</td>
              <td class="align-middle text-center">{{ shorten(result.address) }}</td>
              <td class="align-middle text-center">{{ result.id }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    <!-- <div class="page-wrap">
      <v-pagination
        v-model="pages.page"
        :length="pages.totalPages"
        :start="0"
        @input="loadUserData()"
        total-visible="10"
        class="my-pagination"
      >
      </v-pagination>
    </div> -->
      <div class="modal-footer">
        
      </div>
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
      storeAddress: null,
      isStoreValid: false,
      searchKeyword: null,
      searchResults: null,

      presignedUrl: null,
      encodedFileName: null,
      selectedFile: null
    }
  },
  methods: {
    async registerRequest() {
      if(this.isStoreValid !== true) {
        alert('正しいストアIDを入力してください。');
        return;
      }
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
    async getStoreLoadByStoreId() {
      try {
        const res = await axios.get('/v1/?key=a6d3bb26218771ec&format=json', {
          params: {
            id: this.storeId
            // keyword: this.searchKeyword,
            // count: 9
          }
        });
        console.log(res);
          if(res.data.results.shop[0].id == this.storeId) {
            this.searchResults = res.data.results.shop;
            this.isStoreValid = true;
          }
        
        else {
          this.isStoreValid = false;
        }
      } catch(error) {
        console.log(error);
        this.isStoreValid = false;
      }
    },

    async getStoreLoad() {
      try {
        const res = await axios.get('/v1/?key=a6d3bb26218771ec&format=json', {
          params: {
            // id: this.storeId
            keyword: this.searchKeyword,
            count: 9
          }
        });
        if(res.status === 200) {
          this.searchResults = res.data.results.shop;
        }
      } catch(error) {
        console.log(error);
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
    shorten(text) {
      return text.length > 15 ? text.substr(0, 15) + '...' : text;
    },
    selectStore(store) {
      this.storeId = store.id;
      this.storeName = store.name;
      this.storeAddress = store.address;
      this.getStoreLoadByStoreId();
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

.sch-result{
  min-height: 425px !important;
}
.sch-result tbody td{
  cursor: pointer;
}
#store_code {
  cursor: pointer;
}
</style>
