<template>
  <v-app>
    <section class="py-3 py-md-5 bg-light">
      <div class="container">
        <span class="badge bg-secondary px-3">{{ shopDetail.genre.name }}</span>
        <h1 class="
                    fs-lg-2 fs-mobile-1
                    fw-bold
                    mt-1
                    mb-0
                    pb-4
                    letter-spacing-22
                  ">
          {{ shopDetail.name }}
        </h1>
        <div class="row align-items-center">
          <div class="col-md-auto col-lg-7 img-wrap">
            <v-img
            class="bg-white"
            width="32vw"
            cover
            :aspect-ratio="1"
            :src="shopDetailPhoto"
          ></v-img>
          </div>
          <div class="col-md col-lg-5">
            <small class="text-dark font-baloo-tamma lh-lg d-block mt-3 mt-md-0">目標 $600,000</small>
            <div class="font-baloo-tamma fs-2 lh-xl-lg fw-bold">$280,047</div>
            <div class="row">
              <div class="col-6 mt-3 mt-md-4">
                <div class="position-relative">
                  <small class="
                        position-absolute
                        top-0
                        start-0
                        translate-middle-y
                        fw-normal
                        pb-xl-3
                        text-dark
                  ">
                    許容人数
                  </small>
                  <span class="font-baloo-tamma fs-2 lh-lg fw-bold">{{ shopDetail.capacity }}</span>
                  <span class="fs-4 fw-bolder lh-lg">人</span>
                </div>
              </div>
              <div class="col-6 mt-3 mt-md-4">
                <div class="position-relative">
                  <small class="
                            position-absolute
                            top-0
                            start-0
                            translate-middle-y
                            fw-normal
                            pb-xl-3
                            text-dark
                          ">
                    <v-icon class="like-icon">mdi-heart</v-icon>いいね
                  </small>
                  <span class="font-baloo-tamma fs-2 lh-lg fw-bold">27</span>
                  <span class="fs-4 fw-bolder lh-lg">人</span>
                </div>
              </div>
              <ul class="
                    d-flex
                    justify-content-between justify-content-md-start
                    list-unstyled
                    mb-3
                    mt-2
              ">
                <li class="me-0 me-md-3">
                  <a href="#" class="link-dark text-decoration-none" data-bs-toggle="tooltip" data-bs-placement="top" title="認證標章" data-bs-customClass="mytooltip">
                    <img src="https://WangShuan.github.io/bootstrap5-project/images/icon_good01.svg" alt="認證標章圖標" class="me-1 square-1 square-md-2" />
                    <small class="d-md-none align-bottom">認證標章</small></a>
                </li>
                <li class="me-0 me-md-3">
                  <a href="#" class="link-dark text-decoration-none" data-bs-toggle="tooltip" data-bs-placement="top" title="品質保證" data-bs-customClass="mytooltip">
                    <img src="https://WangShuan.github.io/bootstrap5-project/images/icon_good02.svg" alt="品質保證圖標" class="me-1 square-1 square-md-2" />
                    <small class="d-md-none align-bottom">品質保證</small>
                  </a>
                </li>
                <li class="me-0 me-md-3">
                  <a href="#" class="link-dark text-decoration-none" data-bs-toggle="tooltip" data-bs-placement="top" title="原生創意" data-bs-customClass="mytooltip">
                    <img src="https://WangShuan.github.io/bootstrap5-project/images/icon_good03.svg" alt="原生創意圖標" class="me-1 square-1 square-md-2" />
                    <small class="d-md-none align-bottom">原生創意</small>
                  </a>
                </li>
                <li class="me-0 me-md-3">
                  <a href="#" class="link-dark text-decoration-none" data-bs-toggle="tooltip" data-bs-placement="top" title="人氣王" data-bs-customClass="mytooltip">
                    <img src="https://WangShuan.github.io/bootstrap5-project/images/icon_good04.svg" alt="人氣王圖標" class="me-1 square-1 square-md-2" />
                    <small class="d-md-none align-bottom">人氣王</small></a>
                </li>
              </ul>
            </div>
            <div class="
                  mb-4
                  border-3
                  bg-white
                  rounded
                  border-start border-warning
                  p-3
                  text-dark
                  fs-lg-6 fs-mobile-5
            ">
              オープン時間 <br/>
              <span class="fw-bold">{{ shopDetail.open }}</span>
            </div>
            <div class="
                  mb-4
                  border-3
                  bg-white
                  rounded
                  border-start border-warning
                  p-3
                  text-dark
                  fs-lg-6 fs-mobile-5
            ">
              アクセス <br />
              <span class="fw-bold">{{ shopDetail.access }}</span>
            </div>
            <button class="
                  btn-warning btn btn-warning-hover
                  w-100
                  rounded-pill
                  btn-lg
                  mb-2
                  "
                  @click="scrollToBottom()
            ">
              レヴュー
            </button>
            <div class="row g-2">
              <div class="col-6 col-md-7">
                <button class="
                      btn-outline-secondary btn-outline-secondary-hover
                      border-2
                      btn
                      w-100
                      rounded-pill
                      btn-lg
                    "
                    @click="notComplete()"
                >
                  <i class="fas fa-heart custom-heart"></i>
                  いいね
                </button>
              </div>
              <div class="col">
                <button class="
                      btn-outline-secondary btn-outline-secondary-hover
                      border-2
                      btn btn-lg
                      w-100
                      rounded-pill
                      "
                      @click="notComplete()"
                >
                  <i class="fas fa-share-alt"></i>
                  シェア
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </v-app>
</template>

<script>
export default {
  computed: {
    shopDetail() {
      return this.$store.state.shopDetail;
    },
    shopDetailPhoto() {
      if (this.shopDetail && this.shopDetail.photo && this.shopDetail.photo.pc && this.shopDetail.photo.pc.l) {
        return this.shopDetail.photo.pc.l;
      }
      return '';
    },
  },
  methods: {
    notComplete() {
      alert('まだ開発中の機能です。');
    },
    scrollToBottom() {
      window.scrollTo({
        top: document.body.scrollHeight,
        behavior: "smooth"
      });
    }
    
  }
}
</script>

<style scoped>
.like-icon {
  color: #e91e63;
}

.img-wrap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>