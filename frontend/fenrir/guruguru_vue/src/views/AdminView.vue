<template>
<div>
  <v-card class="nav-tabs-wrap">
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
    <UserManageVue v-if="selectedTab === 0"></UserManageVue>
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
    
    data: () => ({
      tabs: ['会員管理', 'オーナー承認管理'],
      selectedTab: 0,
      
    }),
    methods: {
      
    }
  }
</script>
<style scoped>
</style>