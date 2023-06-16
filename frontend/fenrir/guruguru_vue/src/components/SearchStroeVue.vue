<template>
<v-app>
  <div class="stsch_container">
    <div class="brd">
      <div class="border rounded-4 p-3 mb-2">
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
                    ">
                  検索
                </h2>
              </div>
          
              <form id="form" class="needs-validation" @submit.prevent="search">
                <div class="mb-3">
                  <label for="gps" class="form-label">現在地で検索</label>
                  <v-checkbox label="現在地" id="gps" @click="findMyPlace" value=true></v-checkbox>
                  <!-- <input type="text" v-model="range"> -->
                  <v-select
                      label="検索範囲"
                      item-text="name"
                      solo
                      v-model="range"
                      return-object
                      :items="rangeSelectArr"
                  ></v-select>
                      <!-- :items="['300m', '500m', '1000m', '2000m', '3000m']" -->
                </div>

                <div class="mb-3">
                  <label for="email" class="form-label">店舗名</label>
                    <v-text-field
                      clearable
                      v-model="keyword"
                    ></v-text-field>
                </div>

                <div class="mb-3">
                  <label for="address" class="form-label">アドレス</label>
                  <v-text-field
                      clearable
                      v-model="address"
                  ></v-text-field>
                </div>

                <div class="mb-3">
                  <label for="email" class="form-label">サービス</label>
                  <div class="service">
                    <v-checkbox label="Wi-Fi" v-model="wifi" value="1"></v-checkbox>
                    <v-checkbox label="食べ放題" v-model="free_food" value="1"></v-checkbox>
                    <v-checkbox label="駐車場" v-model="parking" value="1"></v-checkbox>
                    <v-checkbox label="ペット" v-model="pet" value="1"></v-checkbox>
                    <v-checkbox label="ランチ" v-model="lunch" value="1"></v-checkbox>
                    <v-checkbox label="ミッドナイト" v-model="midnight" value="1"></v-checkbox>
                  </div>
                </div>

                <div class="d-none d-md-flex justify-content-center searchBtn">
                  <span>
                    
                    <!-- 検索 -->
                  </span>
                  <button type="submit" class="
                            btn btn-warning btn-lg btn-warning-hover
                            px-5
                            rounded-pill
                            mt-2
                          "
                  >
                  <span class="search-icon"><svg-icon type="mdi" :path="path"></svg-icon></span>
                    検索
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>    
      </div>
    </div>

    <v-card class="sch-result brd">
      <div class="table-wrap">
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
              <td class="align-middle text-center">{{ shorten(shop.name) }}</td>
              <td class="align-middle text-center">{{ shorten(shop.address) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    

      <div>
        <table class="table">
          <thead>
            <tr>
              <td class="text-center">地図</td>
            </tr>
            
          </thead>
          <tbody>
            <tr>
              <td class="align-middle text-center">
                <div class="map">
                  <v-progress-circular
                    indeterminate
                    color="primary"
                    class="circular"
                    :size="80"
                    v-show="locating"
                  ></v-progress-circular>
                </div>
                <div>

                  <gmap-map
                      :zoom="14"    
                      :center="center"
                      style="width:100%;  height: 70vh;"
                      class="gmap"
                  >
                  </gmap-map>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </v-card>
    <div class="page-wrap">
      <v-pagination
        v-model="page"
        :length="pageCnt"
        min="1"
        @input="pageLoad()"
        total-visible="9"
        class="my-pagination"
      >
      </v-pagination>
    </div>
    <Ribbon></Ribbon>
  </div>
  </v-app>
</template>

<script>
  import 'bootstrap'
  import 'bootstrap/dist/css/bootstrap.min.css'
  import axios from 'axios'
  import { VPagination } from 'vuetify/lib'
  import SvgIcon from '@jamescoyle/vue-icon'
  import { mdiMagnify } from '@mdi/js'
  import ribbon from './GitRibbon.vue';

  export default {
    components: {
      VPagination,
      SvgIcon,
      Ribbon: ribbon
    },
    computed: {
      condition() {
        return {
          keyword: this.keyword,
          range: this.range.value,
          format: 'json',
          count: this.pageSize,
          start: this.pageSize * (this.page - 1) + 1,
          address: this.address,
          wifi: this.wifi,
          free_food: this.free_food,
          parking: this.parking,
          pet: this.pet,
          lunch: this.lunch,
          midnight: this.midnight,
          lat: this.latitude,
          lng: this.longitude,
          
          // 35.652243448 lat
          // 139.5444876358 lng
        }
      }
    },

    data() {
      return {
        shopData: null,
        path: mdiMagnify,

        range: "",
        keyword: "",
        address: null,
        recordCnt: null,
        pageCnt: null,
        pageStart: 0,
        pageSize: 4,
        page: 1,
        
        latitude: '',       // my latitude
        longitude: '',      // my longitude
        textContent: '',
        // center: { lat: 37.5642135, lng: 127.0016985 },
        center: { lat: null, lng: null },    // map
        locationMarkers: [],
        locPlaces: [],
        existingPlace: null,

        wifi: null,
        free_food: null,
        parking: null,
        pet: null,
        lunch: null,
        midnight: null,
        

        isGps: false,
        locating: false,
        rangeSelectArr: [
            {
              name: "300m",
              value: 1,
            },
            {
              name: "500m",
              value: 2,
            },
            {
              name: "1000m",
              value: 3,
            },
            {
              name: "2000m",
              value: 4,
            },
            {
              name: "3000m",
              value: 5,
            }
        ],
      }
    },

    methods: {
      search() {
        this.pageCnt = null;
        this.page = null;
        console.log('currentPage by Computed: ');
        console.log(this.condition);
        axios.get('/v1/?key=a6d3bb26218771ec', {
          params: this.condition
        })
          .then((res) => {
            const errCode = res.data.results?.error?.[0]?.code;
            const errMsg = res.data.results?.error?.[0]?.message;
            
            if(errCode == 3000) {
              alert(errMsg);
              return;
            }
            this.page = 1;
            this.shopData = res.data.results.shop;
            this.recordCnt = res.data.results.results_available;
            this.pageCnt = Math.ceil(this.recordCnt / 4);
            this.center.lat = this.shopData[0].lat;
            this.center.lng = this.shopData[0].lng;
          })
          .catch((err) => {
            console.log('errcode');
          
            console.error(err);
            
          });
      },
      pageLoad() {
        console.log('currnet page: ' + this.page);
        console.log(this.condition);
        axios.get('/v1/?key=a6d3bb26218771ec&range=500', {
          params: this.condition
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
        this.locating = true;
        this.textContent = 'Locating...'
          
        // get position
        navigator.geolocation.getCurrentPosition(pos => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;
          this.textContent = 'Your location data is ' + this.latitude + ', ' + this.longitude;
          this.locating = false;
          this.center.lat = this.latitude;
          this.center.lng = this.longitude;
          this.search();
          // : { lat: 35.7111, lng: 139.7611 }
        }, err => {
          this.textContent = err.message;
        })
      },

      goToStoreDetail(shop) {
        this.$store.commit('changeShop', shop);
        this.$router.push('/store/detail');
      },
      findMyPlace() {
        this.isGps = !this.isGps;
        if(this.isGps) {
          this.getCurrentPostion();
        } else {
          this.latitude = null;
          this.longitude = null;
        }
      },
      shorten(text) {
        return text.length > 20 ? text.substr(0, 20) + '...' : text;
      },
    }
  }
</script>

<style scoped>
@import url('https://netdna.bootstrapcdn.com/font-awesome/2.0/css/font-awesome.css');
  
.brd {
  margin-top: 7vh;
  min-width: 500px;
}
.stsch_container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.sch-result {
  display: flex;
}
.sch-result table {
  height: 70vh;
}
.map {
  width: 30vw;
}
.page-wrap {
  margin: 5vh 0;
}
option {
  padding: 10px;
  font-weight: normal;
  display: block;
  white-space: nowrap;
  min-height: 1.2em;
  padding: 0px 2px 1px;
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
  width: 36vw;
  position: relative;
}
.td-img-wrapper {
  width:16vw;
  height: 16vh;
}
.td-img-wrapper img {
    max-width:16vw;
    max-height:16vh;
    object-fit:cover;
    cursor: pointer;
}
.my-pagination >>> .v-pagination__item--active {
  color: black;
}
.address {
  position: relative;
  right: 20px;
}
.service {
  display: flex;
  gap: 2vw;
  min-width: 450px;
}
.searchBtn button{
  width: 70vw;
}
.search-icon {
  position: relative;
  bottom: 1px;
}
.circular {
  color: #0d6efd !important;
  position: absolute;
  left: 43.3%;
  top: 41.5%;
  z-index: 200;
}
.v-application >>> .primary {
  background-color:  #CFD8DC!important;
  border-color: white !important;
}
</style>