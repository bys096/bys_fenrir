<template>
  <div class="stsch_container">
    <div class="stsch_box">
      <div class="stsch">
        <ul class="stsch_form">
          <li>
            <span class="st_dot">
              <label for="stb1">地域区分</label>
            </span>
            <div class="stb_sel">
              <div class="sel5_w">
                <select name="" id="stb1" class="select1">
                  <option value="" selected>地域選択</option>
                </select>
              </div>
              <div class="sel5_w">
                <select name="" id="stb1" class="select2">
                  <option value="" selected>지역 선택</option>
                </select>
              </div>
              <div class="sel5_w">
                <select name="" id="stb1" class="select3">
                  <option value="" selected>지역 선택</option>
                </select>
              </div>
            </div>
          </li>
          <li>
            <span class="st_dot">
              <label for="stb1">地域区分</label>
            </span>
            <div class="stb_sel">
              <div class="sel5_w">
                <input type="text" class="stsch_ipt" v-model="keyword">
              </div>  
            </div>
          </li>
        </ul>
        <div class="stb_btnwrap">
          <div @click="search()" class="btn sch blue" id="searchButton">
            <span>
              <i class="fa-solid fa-magnifying-glass"></i>
              <!-- 検索 -->
            </span>
          </div>
          <a href="" class="btn sch_all gray">
            <span>詳細</span>
          </a>
        </div>
      </div>
      <div class="stsch_navwrap">
        <div class="stsch_nav_tlt">원하는 서비스를 선택하세요.</div>
        <div class="stsch_nav">
          <ul class="nav_top"></ul>
          <ul></ul>
        </div>
      </div>
    
      
    </div>
    
    
      <table class="table table-hover">
        <thead>
          <tr>
            <td class="text-center">#</td>
            <td class="text-center">店舗名</td>
            <td class="text-center">住所/サービス</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(shop, index) in shopData" :key="index">
            <td>
              <div class="td-img-wrapper">
                <img :src="shop.photo.pc.m" alt="">
              </div>
            </td>
            <td class="text-center">{{shop.name}}</td>
            <td class="text-center">{{shop.address}}</td>
          </tr>
        </tbody>
      </table>
      <br><br>
      <div>

 <div class="text-center">
    <!-- <v-pagination
      v-model="page"
      :length="pageCnt"
      rounded="circle"
    ></v-pagination> -->

    <v-pagination
          v-model="page"
          :length="pageCnt"
          @input="pageLoad()"
          total-visible="7"
    class="my-pagination"
      ></v-pagination>
  </div>

      </div>
  </div>
</template>

<script>

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'
import { VPagination } from 'vuetify/lib';


export default {
  components: {
    VPagination
  },
  data() {
    return {
      shopData: null,
      keyword: null,
      recordCnt: null,
      pageCnt: null,
      pageStart: 1,
      pageSize: 5,
      page: 1,
      isActivePage: false

    }
  },

  methods: {
    search() {
      axios.get('/v1/?key=a6d3bb26218771ec&format=json&lat=35.7111&lng=139.7611&range=500',
        {
          params: {
            keyword: this.keyword,
            start: 0,
            count: this.pageSize
          }
        }
      )
        .then((res) => {
          console.log(res);
          this.shopData = res.data.results.shop;
          this.recordCnt = res.data.results.results_available;
          this.pageCnt = this.recordCnt / 5;
      });
    },
    pageLoad() {
      // alert(this.page);
      axios.get('/v1/?key=a6d3bb26218771ec&format=json&lat=35.7111&lng=139.7611&range=500',
        {
          params: {
            keyword: this.keyword,
            start: this.pageSize * this.page + 1,
            count: this.pageSize
          }
        }
      )
        .then((res) => {
          console.log(res);
          this.shopData = res.data.results.shop;
      });
      
    }
  }
}
</script>

<style scoped>
  @import url('https://netdna.bootstrapcdn.com/font-awesome/2.0/css/font-awesome.css');
  

    @import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css";

  .stsch_box {
    border: 1px solid #cccccc;
    padding: 20px 57px 20px 60px;
  }
  .stsch_form li {
    display: flex;
    flex-direction: row;
    /* background-color: ; */
  }
  .stb_sel {
    display: flex;
    flex-direction: row;
  }
  .stb_sel select {
    margin-left: 1.3vw;
    /* width: 100%; */
    width: 15vw;
    border: 1px solid #dddddd;
    color: #999999;
    font-size: 14px;
    padding: 10px;
  }
  option {
    padding: 10px;
    font-weight: normal;
    display: block;
    white-space: nowrap;
    min-height: 1.2em;
    padding: 0px 2px 1px;
  }

  .stsch_form li {
    border-bottom: 1px dotted #cccccc;
    padding: 2vw 0vh 3vh 2vw;
    margin-bottom: 2vh
  }
  .stsch_form .st_dot {
      display: inline-block;
      vertical-align: top;
      width: 140px;
      /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat; */
      color: #444;
      font-size: 16px;
      padding: 8px 0px 0px 12px;
  }
  .stb_btnwrap .sch {
      padding: 12px 0;
      width: 94px;
      /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat #397ca8; */
      /* color: #fff; */
      background-color: #ffdf65;
      color: white;
      width: 100px;
      height: 78px;
      text-align: center;
  }
  .stb_btnwrap .sch {
      padding: 12px 0;
      width: 94px;
      /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat #397CB6; */
      /* color: #fff; */
      width: 100px;
      height: 78px;
      text-align: center;
  }
  .stsch_navwrap {
      clear: both;
      border-top: 1px dotted #cccccc;
  }
  .stsch_navwrap .stsch_nav_tlt {
      vertical-align: top;
      /* background: url(/_ui/desktop/common/images/gscvs/store/icon_store.png) no-repeat; */
      color: #444;
      font-size: 16px;
      padding: 8px 0px 0px 20px;
      background-position: 10px 14px;
      margin: 12px 0px 10px;
  }
  .stsch_form .stsch_ipt {
      width: 418px;
      height: 34px;
      border: 2px solid #666;
  }
  input[type=text], input[type=password] {
      text-indent: 5px;
      border: 1px solid #d6d6d6;
      height: 29px;
      line-height: 31px;
      color: #666;
      font-size: 13px;
  }

  .stsch_container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* height: 100vh; */
  }
  .fa-magnifying-glass {
    font-size: 5vmin;
  }

  table {
    width: 40vw;
    position: relative;
    right: 12vw;
    top: 5vh;
  }
  /* td {
    width: 20vw;
    height: 20vh;
  }
  img {
      width: 100%;
      height: 100%;
      object-fit: contain;

      box-sizing: border-box;
  } */
  td {
    width: 20vw;
    height: 20vh;
    overflow: hidden;  /* 셀의 크기를 넘어가는 내용을 숨김 */
}

 .td-img-wrapper {
    width: 100%;
    height: 100%;
    overflow: hidden;  /* 셀의 크기를 넘어가는 이미지를 숨김 */
}

.td-img-wrapper img {
    width: 100%;  /* 이미지를 셀의 너비에 맞게 조절 */
    height: auto;  /* 이미지의 높이를 자동으로 조절하여 비율을 유지 */
}


.my-pagination >>> .v-pagination__item--active {
  /* background-color: blue; */
  color: black; /* 원하는 색상으로 변경하세요 */
}

</style>