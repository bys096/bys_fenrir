<template>
<v-app>
  <div>
    <div class="sch-result">
      <table class="table table-hover store-list">
        <thead>
          <tr>
            <td class="text-center">E-Mail</td>
            <td class="text-center">ユーザー名</td>
            <td class="text-center">ニックネーム</td>
            <td class="text-center">パスワード</td>
            <td class="text-center">登録日</td>
            <td class="text-center">Role</td>
            <td class="text-center">Actions</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in users" :key="index">
            <td class="align-middle text-center">{{ user.userEmail }}</td>
            <td class="align-middle text-center">{{ user.userName }}</td>
            <td class="align-middle text-center">{{ user.userNick }}</td>
            <td class="align-middle text-center">{{ shortenPw(user.userPw) }}</td>
            <td class="align-middle text-center">{{ user.createdAt }}</td>
            <td class="align-middle text-center">{{ user.userRole }}</td>
            <td class="align-middle text-center icons">
              <v-btn class="text-none" stacked data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="clickEdit(user)">
                
                  <span class="edit-icon"><svg-icon type="mdi" :path="pencilIcon"></svg-icon></span>
                
              </v-btn>
              <v-btn class="text-none" stacked @click="deleteUser(user.userId)">
                
                  <span class="delete-icon"><svg-icon type="mdi" :path="deleteIcon"></svg-icon></span>
                
              </v-btn>
            </td>
          </tr>
        </tbody>
      </table>

    </div>
    <div class="page-wrap">
      <v-pagination
      id="page"
        v-model="pages.page"
        :length="pages.totalPages"
        :start="0"
        @input="loadUserData()"
        total-visible="10"
        class="my-pagination"
      >
      </v-pagination>
    </div>
    <a href="https://github.com/bys096/bys_guruguru" id="ribbon" target="_blank">
      <i class="fa fa-github" aria-hidden="true"></i>
      View on GitHub
    </a>

<!-- add boot -->
<!-- Button trigger modal -->


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
                <div>E-Mail</div>
              </v-col>
              <v-col cols="8">
                <v-text-field
                  label="E-Mail"
                  readonly
                  v-model="selectedUser.userEmail"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <div>ユーザー名</div>
              </v-col>
              <v-col cols="8">
                <v-text-field
                  label="ユーザー名"
                  required
                  v-model="selectedUser.userName"
                ></v-text-field>
                  
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="4">
                <div>ニックネーム</div>
              </v-col>
              <v-col cols="8">
                <v-text-field
                  label="ニックネーム"
                  required
                  v-model="selectedUser.userNick"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="4">
                <div>パスワード</div>
              </v-col>
              <v-col cols="8">
                <v-text-field
                  label="パスワード"
                  value=""
                  required
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="4">
                <div>Role</div>
              </v-col>
              <v-col cols="8">
                <v-select
                  label="Select"
                  :items="['USER', 'OWNER']"
                  :value="selectedUser.userRole"
                  variant="underlined"
                  :item-value="selectedUser.userRole"
                  v-model="selectedUser.userRole"
                ></v-select>
                <!-- <v-text-field
                  label="Role"
                  required
                  v-model="selectedUser.userRole"
                ></v-text-field> -->
                  <!-- :value="selectedUser !== null ? selectedUser.userRole : ''" -->
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </div>
      <div class="modal-footer">
        <v-btn
          class="flex-grow-1"
          height="48"
          variant="tonal"
          data-bs-dismiss="modal"
        >
          Close
        </v-btn>
        <v-btn
          class="flex-grow-1 btn btn-secondary"
          height="48"
          variant="tonal"
          data-bs-dismiss="modal"
          @click="updateUser()"
        >
          セーブ
        </v-btn>
      </div>
    </div>
  </div>
  
    </div>
  
</div>  
</v-app>
</template>

<script>
import axios from 'axios';
import SvgIcon from '@jamescoyle/vue-icon'
import { mdiPencil, mdiDelete } from '@mdi/js';


export default {
  components: {
    SvgIcon,
  },
  computed: {
    inputUser() {
      return this.selectedUser !== null ? this.selectedUser : '';
    }
  },
  mounted() {
      this.loadUserData();
  },
  data() {
    return {
      pencilIcon: mdiPencil,
      deleteIcon: mdiDelete,
      test: ['1', '2'],
      defaultSelected: "USER",

      users: null,
      pages: {
        totalPages: null,
        totalElements: null,
        pageSize: 10,
        page: null
      },

      isShowUserUpdateForm: false,
      selectedUser: {
        userRole: "",
        userNick: "",
        userName: "",
        userEmail: ""
      }
    }
  },
  methods: {
    shortenPw(pw) {
      return pw.length > 15 ? pw.substr(0, 15) + '...' : pw;
    },
    async deleteUser(userId) {
      try {
        const res = await axios.delete(`/api/user/${userId}`, {
          headers: this.$store.getters.headers
        });
        console.log(res);
        if(res.status == 204) {
          console.log(res);
          this.loadUserData();
        }
      } catch(err) {
        console.log(err);
      }
    },
    async loadUserData() {
        try {
          const pageable = {
            page: this.pages.page == null ? 0 : this.pages.page - 1,
            limit: this.pages.pageSize
          }
          console.log('header check');
          console.log(this.$store.getters.headers);
          const res = await axios.get('/api/admin/user/list', {
            params: pageable,
            headers: this.$store.getters.headers
          });
          console.log(res);
          if(res.status === 200) {
            this.users = res.data.content;

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
          console.log(err);
        }
      },
      clickEdit(user) {
        this.selectedUser = user;
        this.isShowUserUpdateForm = !this.isShowUserUpdateForm;
      },
      updateUser() {
        const user = {
          userId: this.selectedUser.userId,
          userNick: this.selectedUser.userNick,
          userName: this.selectedUser.userName,
          userRole: this.selectedUser.userRole
        }
        console.log(user);
        try {
          const res = axios.patch('/api/admin/user', user, {
            headers: this.$store.getters.headers
          });
          console.log(res);
        } catch(err) {
          console.log(err);
        }
      }
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
.v-application >>> .primary {
  background-color: white !important;
  border-color: white !important;
}
.sch-result {
  min-height: 60vh;
  position: relative;
  top: 20px;
}
.page-wrap {
  position: relative;
  top: 20px;
  min-height: 800px;
}
.store-list {
}
.icons {
  cursor: pointer;
}
.theme--light.v-btn.v-btn--has-bg {
  background-color: white;
}
.edit-icon {
  color: #5C6BC0;
}
.delete-icon {
  color: #EC407A;
}
.mt-2 {
  visibility: visible !important;
}

</style>