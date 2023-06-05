<template>
<div>
  
<TopSectionVue></TopSectionVue>
<NavTabVue></NavTabVue>

<div class="container">
  <div class="my-4">
    <div class="row g-md-5">
      <!-- 左邊 tab 區塊 -->
      <div class="col-lg-8">
        <DetailTabContentVue></DetailTabContentVue>
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
            贊助專案
          </h2>
        </div>
        <form id="form" class="needs-validation" novalidate>
          <div class="mb-3">
            <label for="project" class="form-label">贊助方案</label>
            <select class="form-select" id="project" aria-label="select project" required>
              <option value="1">方案1</option>
              <option value="2">方案2</option>
              <option value="3">方案3</option>
            </select>
            <div class="invalid-feedback">請選擇一個方案</div>
          </div>

          <div class="mb-3">
            <label for="name" class="form-label">收件人姓名</label>
            <input type="text" class="form-control" id="name" required />
            <div class="invalid-feedback">請輸入正確的收件人姓名</div>
          </div>

          <div class="mb-3">
            <label for="address" class="form-label">收件地址</label>
            <input type="text" class="form-control" id="address" required />
            <div class="invalid-feedback">請輸入正確的收件地址</div>
          </div>

          <div class="mb-3">
            <label for="phone" class="form-label">聯絡電話</label>
            <input type="text" class="form-control" id="phone" required />
            <div class="invalid-feedback">請輸入正確的電話號碼</div>
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">聯絡信箱</label>
            <input type="text" class="form-control" id="email" required />
            <div class="invalid-feedback">請輸入正確的郵件格式</div>
          </div>

          <div class="mb-3">
            <label for="payment" class="form-label">付款方式</label>
            <select id="payment" class="form-select" aria-label="select payment" required>
              <option value="1">信用卡付款</option>
              <option value="2">貨到付款</option>
            </select>
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
              贊助專案
            </button>
          </div>
        </form>
      </div>

      
      <!-- 右邊 card 區塊 -->
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
    贊助專案
  </button>
</div>

</div>
</template>

<script>
import navTabVue from '../components/DetailNavTab.vue'
import topSectionVue from '../components/DetailTopSection.vue'
import detailSidebarVue from '../components/DetailSidebar.vue'
import detailTabContentVue from '../components/DetailTabContent.vue'
import infoCardVue from '../components/DetailInfoCardVue.vue'

export default {
  components: {
    NavTabVue: navTabVue,
    TopSectionVue: topSectionVue,
    DetailSidebarVue: detailSidebarVue,
    DetailTabContentVue: detailTabContentVue,
    InfoCardVue: infoCardVue
  }
}
</script>

<style>

</style>