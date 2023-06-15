<template>
<div>
  
<TopSectionVue ></TopSectionVue>
<NavTabVue></NavTabVue>

<div class="container">
  <div class="my-4">
    <div class="row g-md-5">
      <!-- 左邊 tab 區塊 -->
      <div class="col-lg-8">
        <DetailTabContentVue :props="reviewList" @addReply="saveReplyAndLoad" @deleteReply="deleteReplyAndLoad" @editReview="editReviewAndLoad"></DetailTabContentVue>
        <!-- 手機版逼人贊助卡片區塊 -->
        <InfoCardVue></InfoCardVue>
        <!-- 表單 -->
        <div class="d-flex justify-content-center">
          <img src="https://WangShuan.github.io/bootstrap5-project/images/hezuo.svg" alt="合作圖標" />
        </div>
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
            レビュー
          </h2>
        </div>
        <form id="form" class="needs-validation" @submit.prevent="saveReviewAndLoad()">


          <div class="text-center">
            <v-rating
              class="my-rating"
              v-model="rating"
              color="purple"
              bg-color="orange-lighten-1"
              id="t"
            ></v-rating>
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">タイトル</label>
            <input type="text" class="form-control" v-model="reviewTitle" required />
            <div class="invalid-feedback">請輸入正確的郵件格式</div>
          </div>


          <div class="mb-3">
            <label for="payment" class="form-label">付款方式</label>
            <select id="payment" class="form-select" aria-label="select payment" required>
              <option value="1">静かで良かったです。</option>
              <option value="2">写真がカッコよく映るお店でした。</option>
              <option value="2">景色が良かったです。</option>
            </select>
            <div class="invalid-feedback">請選擇一種付款方式</div>
          </div>

          <div class="mb-3">
            <label for="payment" class="form-label">レビュー</label>
            <v-container fluid>
              <v-textarea
                name="input-7-1"
                variant="filled"
                auto-grow
                v-model="reviewText"
                placeholder="お店に関しての正直なレビューを残してください。"
                required
                model-value="The Woodman set to work at once, and so sharp was his axe that the tree was soon chopped nearly through."
              ></v-textarea>
            </v-container>
            <div class="invalid-feedback">請選擇一種付款方式</div>
          </div>

          <button class="
                    btn btn-warning btn-lg btn-warning-hover
                    px-5
                    fw-bold
                    rounded-pill
                    mt-2
                    w-100
                    d-md-none
                  " type="submit">
            贊助專案
          </button>

          <div class="d-none d-md-flex justify-content-center">
            <button type="submit" class="
                      btn btn-warning btn-lg btn-warning-hover
                      px-5
                      fw-bold
                      rounded-pill
                      mt-2
                    ">
              レビュー登録
            </button>
          </div>
        </form>
      </div>

      <DetailSidebarVue></DetailSidebarVue>
    </div>
  </div>
</div>
<div class="position-sticky bottom-0" style="z-index: 999">
  <button class="
            btn btn-warning btn-warning-hover btn-lg
            px-5
            fw-bold
            rounded-0
            mt-2
            w-100
            d-md-none
          " onclick="goForm()" id="bottomBtn">
    レビュー登録
  </button>
</div>
<!-- <Ribbon></Ribbon> -->
</div>
</template>

<script>
import navTabVue from '../components/DetailNavTab.vue'
import topSectionVue from '../components/DetailTopSection.vue'
import detailSidebarVue from '../components/DetailSidebar.vue'
import detailTabContentVue from '../components/DetailTabContent.vue'
import infoCardVue from '../components/DetailInfoCardVue.vue'
// import ribbon from '../components/GitRibbon.vue'
import { mapState } from 'vuex'
import axios from 'axios';

export default {
  created() {
    this.getReviewList();
  },
  computed: {
      // return this.$store.state.shopDetail;
      ...mapState(['shopDetail']),
      
  },
  data() {
    return {
      shop: null,
      rating: 5, // rating,
      reviewText: null,
      reviewTitle: null,
      reviewList: [],
      sort: null
    }
  },
  components: {
    NavTabVue: navTabVue,
    TopSectionVue: topSectionVue,
    DetailSidebarVue: detailSidebarVue,
    DetailTabContentVue: detailTabContentVue,
    InfoCardVue: infoCardVue,
    // Ribbon: ribbon
  },
  methods: {
    async saveReviewAndLoad() {
      const review = {
        reviewRating: this.rating,
        reviewText: this.reviewText,
        storeCode: this.shopDetail.id,
        storeName: this.shopDetail.name
      }
      if(!this.reviewText || !this.rating) {
        alert('空欄があります。');
      } else {
        await this.saveReview(review);
        await this.getReviewList();
        this.reviewText = "";
        this.reviewTitle = "";
        this.rating = 5;
      }
    },
    async saveReview(review) {
      
      try {
        const res = await axios.post(`/api/review`, review, {
          headers: this.$store.getters.headers
        });
        console.log(res);
      } catch(err) {
        console.log(err);
        const errRes = err.response.data;
        if(errRes.code === "R002")
          alert(errRes.message);
      }
    
    },
    async getReviewList() {
      try {
        // const page = this.reviewList == null ? 0 : this.reviewList
        const params =  { params: { page: this.page, limit: this.limit } };
        const config = this.$store.getters.headers;
        console.log('getReviewList');
        const res = await axios.get(`/api/review/list/${this.shopDetail.id}`, params, config);
        if(res.status === 200) {
          console.log('새롭게 리뷰 리스트반환');
          console.log(res);
          this.reviewList = res.data.content;
        }
      } catch(error) {
        console.log('실패');
        console.log(error);
      }
    },
    async saveReplyAndLoad(reply) {
      await this.saveReply(reply);
      await this.getReviewList();  
    },
    async saveReply(reply) {
      try {
        await axios.post('/api/reply', reply, {
          headers: this.$store.getters.headers
        });
      } catch(error) {
        console.log(error);
      }
    },
    async deleteReplyAndLoad(reviewObj) {
      try {
        if(reviewObj.replyId === null) {
          console.log('review Object axios 직전');
          console.log(reviewObj);
          await axios.delete(`/api/review/${reviewObj.reviewId}`,{
            headers: this.$store.getters.headers
          });
        } else {
          console.log('review Object axios 직전');
          console.log(reviewObj);
          await axios.delete(`/api/review/${reviewObj.reviewId}/${reviewObj.replyId}`,{
            headers: this.$store.getters.headers
          });
        }
        await this.getReviewList();
      } catch(error) {
        console.log(error);
      }
    },
    async editReviewAndLoad(review) {
      console.log(review.reviewId, review.reviewText);
      const reviewRequest = {
        reviewText: review.reviewText
      }
      try {
        const res = await axios.patch(`/api/review/${review.reviewId}`, reviewRequest, {
          headers: this.$store.getters.headers
        });
        if(res.status === 200) {
          await this.getReviewList();
        }
        console.log(res);
      } catch(err) {
        console.log(err);
      }
    }
  }
}
</script>

<style scoped>
.my-rating >>> .theme--light.v-icon {
  color: #FFD700;
}

</style>