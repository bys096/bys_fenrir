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
        <div class="stsch_nav_tlt">必要なサービスを選択してください。</div>
          <div class="stsch_nav">
            <ul class="nav_top"></ul>
            <ul></ul>
          </div>
      </div>
    </div>
    
    <div class="sch-result">
      <table class="table table-hover store-list">
        <thead>
          <tr>
            <td class="text-center"></td>
            <td class="text-center">店舗名</td>
            <td class="text-center">住所/サービス</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(shop, index) in shopData" :key="index" @click="goToStoreDetail(shop)">
            <td class="align-middle text-center">
              <div class="td-img-wrapper">
                <img :src="shop.photo.pc.m" alt="" class="img-thumbnail">
              </div>
            </td>
            <td class="align-middle text-center">{{ shop.name }}</td>
            <td class="align-middle text-center">{{ shop.address }}</td>
          </tr>
        </tbody>
      </table>

      <div class="card">
        
        
          <div class="map">
        <!-- <div @click="test">btn</div> -->
        <button @click="getCurrentPostion()">getCurrentPostion</button>
        <p>{{textContent}}</p>
        <gmap-map
            :zoom="14"    
            :center="center"
            style="width:100%;  height: 600px;"
        >
        </gmap-map>
      </div>
        
      </div>

      
    </div>

    <div class="page-wrap">
        <v-pagination
          v-model="page"
          :length="pageCnt"
          @input="pageLoad()"
          total-visible="9"
          class="my-pagination"
        >
        </v-pagination>
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
    computed: {
      
    },

    data() {
      return {
        shopData: null,
        keyword: null,
        recordCnt: null,
        pageCnt: null,
        pageStart: 1,
        pageSize: 4,
        page: 1,
        
        latitude: '',       // my latitude
        longitude: '',      // my longitude
        textContent: '',
        // center: { lat: 37.5642135, lng: 127.0016985 },
        center: { lat: 35.7111, lng: 139.7611 },    // 
        locationMarkers: [],
        locPlaces: [],
        existingPlace: null
      }
    },

    methods: {
      search() {
        axios.get('/v1/?key=a6d3bb26218771ec&format=json&lat=35.7111&lng=139.7611&range=500', {
          params: {
            keyword: this.keyword,
            start: 0,
            count: this.pageSize
          }
        })
          .then((res) => {
            console.log(res);
            this.shopData = res.data.results.shop;
            this.recordCnt = res.data.results.results_available;
            this.pageCnt = this.recordCnt / 5;
          
            this.$store.commit('testArr', this.shopData);
          })
          .catch((error) => {
            console.error(error);
          });
      },
      pageLoad() {
        axios.get('/v1/?key=a6d3bb26218771ec&format=json&lat=35.7111&lng=139.7611&range=500', {
          params: {
            keyword: this.keyword,
            start: this.pageSize * this.page + 1,
            count: this.pageSize
          }
        })
          .then((res) => {
            console.log(res);
            this.shopData = res.data.results.shop;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      getCurrentPostion() {
        if(!("geolocation" in navigator)) {
          this.textContent = 'Geolocation is not available.';
          return;
        }
        this.textContent = 'Locating...'
          
        // get position
        navigator.geolocation.getCurrentPosition(pos => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;
          this.textContent = 'Your location data is ' + this.latitude + ', ' + this.longitude;
          this.center.lat = this.latitude;
          this.center.lng = this.longitude;
          // : { lat: 35.7111, lng: 139.7611 }
        }, err => {
          this.textContent = err.message;
        })
      },
      test() {
        alert('aa');
      },
      goToStoreDetail(shop) {
        // $router.push('/store/detail');
        console.log('넘길 값');
        console.log(shop);
        // this.$router.push({
        //   path: `/store/detail`,
        //   query: {
        //     shop: shop
        //   }
        // });
        this.$store.commit('changeShop', shop);
        this.$router.push('/store/detail');
      }
    }
  }
</script>

<style scoped>
  @import url('https://netdna.bootstrapcdn.com/font-awesome/2.0/css/font-awesome.css');
  

  .stsch_container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .stsch_box {
    border: 1px solid #cccccc;
    padding: 20px 57px 20px 60px;
  }
  .sch-result {
    display: flex;
  }
  .map {
    width: 30vw;
    position: relative;
    top: 10vh;
  }
  .page-wrap {
    margin-top: 10vh;
  }
  .stsch_form li {
    display: flex;
    flex-direction: row;
  }
  .stb_sel {
    display: flex;
    flex-direction: row;
  }
  .stb_sel select {
    margin-left: 1.3vw;
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
    color: #444;
    font-size: 16px;
    padding: 8px 0px 0px 12px;
  }
  .stb_btnwrap .sch {
    padding: 12px 0;
    width: 94px;
    background-color: #ffdf65;
    color: white;
    width: 100px;
    height: 78px;
    text-align: center;
  }
  .stb_btnwrap .sch {
    padding: 12px 0;
    width: 94px;
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
  .fa-magnifying-glass {
    font-size: 5vmin;
  }
  .sch-result table {
    width: 40vw;
    position: relative;
    right: 2vw;
    top: 5vh;
  }
  .td-img-wrapper {
    width:20vw;
    height:20vh;
  }
  .td-img-wrapper img {
      max-width:20vw;
      max-height:20vh;
      object-fit:cover;
      cursor: pointer;
  }
  .my-pagination >>> .v-pagination__item--active {
    color: black;
  }
</style>