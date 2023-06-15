<template>
  <div>
    <div class="sch-result">
      <table class="table table-hover store-list">
        <thead>
          <tr>
            <td class="text-center">E-Mail</td>
            <td class="text-center">ユーザー名</td>
            <td class="text-center">ニックネーム</td>
            <td class="text-center">パスワード</td>
            <td class="text-center">申請時間</td>
            <td class="text-center">ストアコード</td>
            <td class="text-center">店名</td>
            <td class="text-center">申請状態</td>
            <td class="text-center">ファイル</td>
            <td class="text-center">Actions</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(owner, index) in owners" :key="index">
            <td class="align-middle text-center">{{ owner.userEmail }}</td>
            <td class="align-middle text-center">{{ owner.userName }}</td>
            <td class="align-middle text-center">{{ owner.userNick }}</td>
            <td class="align-middle text-center">{{ shortenPw(owner.userPw) }}</td>
            <td class="align-middle text-center">{{ owner.createdAt }}</td>
            <td class="align-middle text-center">{{ owner.storeCode }}</td>
            <td class="align-middle text-center">{{ shortenPw(owner.storeName) }}</td>
            <td class="align-middle text-center" v-show="owner.orState==0">受諾待ち</td>
            <td class="align-middle text-center" v-show="owner.orState==1">受諾完了</td>
            <td class="align-middle text-center">
              <a :href="downloadFile(owner.orFileName)">{{ shortenPw(owner.orFileName) }}</a>
            </td>
            <td class="align-middle text-center icons">
              <div v-show="owner.orState==0">
                <v-btn class="text-none" stacked @click="acceptOwner(owner.orId)">
                  <v-badge dot color="success">
                    <span class="search-icon check-icon"><svg-icon type="mdi" :path="checkIcon"></svg-icon></span>
                  </v-badge>
                </v-btn>
                <v-btn class="text-none" stacked @click="rejectOwner(owner.orId)">
                  <v-badge dot color="error">
                    <span class="search-icon reject-icon"><svg-icon type="mdi" :path="rejectIcon"></svg-icon></span>
                  </v-badge>
                </v-btn>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- <div class="page-wrap">
      <v-pagination
        v-model="pages.page"
        :length="pages.totalPages"
        :start="0"
        @input="pageChange()"
        total-visible="10"
        class="my-pagination"
      >
      </v-pagination>
    </div> -->
  </div>  
</template>

<script>
import axios from 'axios';
import SvgIcon from '@jamescoyle/vue-icon'
import { mdiCheck, mdiClose } from '@mdi/js';

export default {
  mounted() {
    this.loadOwnerData();
  },
  components: {
    SvgIcon
  },
  computed: {
    shortPw() {
      return this.shortenPw(this.owners.userPw);
    }
  },
  data() {
    return {
      owners: null,
      pages: {
        totalPages: 0,
        totalElements: null,
        pageSize: 10,
        page: null
      },
      checkIcon: mdiCheck,
      rejectIcon: mdiClose
    }
  },
  methods: {
    async loadOwnerData() {
      try {
        const pageable = {
          page: this.pages.page == null ? 0 : this.pages.page - 1,
          limit: this.pages.pageSize
        }
        const res = await axios.get('/api/admin/owner/register/list', {
          params: pageable,
          headers: this.$store.getters.headers
        });
        console.log(res);
        if(res.status === 200) {
          this.owners = res.data.content;
          const pages = {
            totalElements: res.data.totalElements,
            totalPages: res.data.totalPages,
            pageSize: res.data.size,
            page: res.data.number+1
          }
          this.pages = pages;
        }
      } catch(err) {
        const status = err.response.status;
        if(status === 401)  this.$router.push('/login');
        else if(status === 403) this.$router.push('/error403');
      }
    },
    shortenPw(pw) {
      return pw.length > 10 ? pw.substr(0, 10) + '...' : pw;
    },
    downloadFile(fileName) {
      // alert(fileName);
      const url = `https://ns-union.s3.ap-northeast-2.amazonaws.com/guruguru/${fileName}`;
      return url
    },
    async acceptOwner(orId) {
      try {
        const res = await axios.patch(`/api/admin/owner/accept/${orId}`, null, {
          headers: this.$store.getters.headers
        });
        if(res.status === 200) {
          this.loadOwnerData();
        }
      } catch(err) {
        console.log(err);
      }
    },
    async rejectOwner(orId) {
      try {
        console.log('reject');
        const res = await axios.delete(`/api/admin/owner/reject/${orId}`, {
          headers: this.$store.getters.headers
        });
        if(res.status === 204) {
          this.loadOwnerData();
        }
      } catch(err) {
        console.log(err);
      }
    },
  }
}
</script>

<style scoped>
#ribbon {
	background: #fff none repeat scroll 0 0;
	box-shadow: 0 0 10px 0 rgba(0,0,0,0.1);
	font-weight: 400;
	padding: 15px;
	position: fixed;
	right: -269px;
	text-align: center;
	top: -125px;
	transform: rotate(45deg);
	transform-origin: 0 0 0;
	width: 500px;
	i {
		font-size: 21px;
		padding-right: 5px;
		vertical-align: middle;
	}
}
.my-pagination >>> .v-pagination__item--active {
    color: black;
}
.sch-result {
  min-height: 60vh;
}
.icons {
  cursor: pointer;
}
.check-icon {
  color: #4caf50;
}
.reject-icon {
  color: #e91e63;
}
.theme--light.v-btn.v-btn--has-bg {
  background-color: white;
}
</style>