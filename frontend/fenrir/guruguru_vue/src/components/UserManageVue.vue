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
            <td class="text-center">登録日</td>
            <td class="text-center">Role</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(member, index) in props" :key="index">
            <td class="align-middle text-center">{{ member.userEmail }}</td>
            <td class="align-middle text-center">{{ member.userName }}</td>
            <td class="align-middle text-center">{{ member.userNick }}</td>
            <td class="align-middle text-center">{{ member.userPw }}</td>
            <td class="align-middle text-center">text</td>
            <td class="align-middle text-center">{{ member.userRole }}</td>
          </tr>
        </tbody>
      </table>

      
    </div>
    <div class="page-wrap">
      <v-pagination
        v-model="pages.page"
        :length="pages.totalPages"
        :start="0"
        @input="pageChange()"
        total-visible="10"
        class="my-pagination"
      >
      </v-pagination>
    </div>
  

<a href="https://github.com/bys096/bys_guruguru" id="ribbon" target="_blank"><i class="fa fa-github" aria-hidden="true"></i>
	View on GitHub</a>

  
</div>  
</template>

<script>
import axios from 'axios';
export default {
  props: ['props', 'pages'],
  computed: {
    page() {
      
    }
  },
  methods: {
    pageChange() {
      this.$emit('pageChange');
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

</style>