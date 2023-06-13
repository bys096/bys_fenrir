<template>
<div>
  <v-card>
    <v-tabs
      v-model="selectedTab"
      bg-color="red-lighten-2"
    >
      <v-tab
        v-for="tab in tabs"
        :key="tab"
        :value="tab"
      >
        {{ tab }}
      </v-tab>
    </v-tabs>
  </v-card>
    <UserManageVue :props="users" :pages="pages" @pageChange="loadUserData" v-if="selectedTab === 0"></UserManageVue>
    <OwnerManageVue v-if="selectedTab === 1"></OwnerManageVue>
  </div>
</template>
<script>
import userManageVue from '../components/UserManageVue.vue'
import ownerManageVue from '../components/OwnerManageVue.vue';
import axios from 'axios';


  export default {
    components: {
      UserManageVue: userManageVue,
      OwnerManageVue: ownerManageVue
    },
    mounted() {
      this.loadUserData();
    },
    data: () => ({
      tabs: ['会員管理', 'オーナー承認管理'],
      selectedTab: 0,
      users: null,
      
      pages: {
        totalPages: null,
        totalElements: null,
        pageSize: 10,
        page: null
      }
    }),
    methods: {
      async loadUserData() {
        try {
          const pageable = {
            page: this.pages.page == null ? 0 : this.pages.page - 1,
            limit: this.pages.pageSize
          }
          const res = await axios.get('/api/admin/user/list', {params: pageable},{
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
          console.log(err);
        }
      }
    }
  }
</script>