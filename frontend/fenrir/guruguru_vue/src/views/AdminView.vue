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
    <UserManageVue :props="members" v-if="selectedTab === 0"></UserManageVue>
  </div>
</template>
<script>
import userManageVue from '../components/UserManageVue.vue'
import axios from 'axios';
  export default {
    components: {
      UserManageVue: userManageVue
    },
    mounted() {
      this.loadUserData();
    },
    data: () => ({
      // length: 15,
      tabs: ['会員管理', 'オーナー承認管理'],
      selectedTab: 0,
      members: null
    }),

    watch: {
      // length (val) {
      //   this.tab = val - 1
      // },
    },
    methods: {
      async loadUserData() {
        try {
          const res = await axios.get('/api/admin/user/list', {
            headers: this.$store.getters.headers
          });
          console.log(res);
          if(res.status === 200) {
            this.members = res.data.content;
          }
        } catch(err) {
          console.log(err);
        }
      }
    }
  }
</script>