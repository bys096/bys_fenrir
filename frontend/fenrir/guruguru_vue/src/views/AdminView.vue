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
    <!-- <v-card-text class="text-center">
      <v-btn
        :disabled="!length"
        variant="text"
        @click="length--"
      >
        Remove Tab
      </v-btn>
      <v-divider
        class="mx-4"
        vertical
      ></v-divider>
      <v-btn
        variant="text"
        @click="length++"
      >
        Add Tab
      </v-btn>
    </v-card-text> -->
  </v-card>
    <UserManageVue :props="members" :pages="pages" @pageChange="loadUserData" v-if="selectedTab === 0"></UserManageVue>
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
      // length: 15,
      tabs: ['会員管理', 'オーナー承認管理'],
      selectedTab: 0,
      members: null,
      
      pages: {
        totalPages: null,
        totalElements: null,
        pageSize: 10,
        page: null
      }
    }),

    watch: {
      // length (val) {
      //   this.tab = val - 1
      // },
    },
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
            this.members = res.data.content;

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