<template>
  <div>
    <button @click="test">btn</button>
    <SearchStoreVue></SearchStoreVue>
  </div>
</template>

<script>
import axios from 'axios'
import searchStoreVue from '../components/SearchStroeVue.vue'

export default {
  components: {
    SearchStoreVue: searchStoreVue
  },
data() {
    return {
      m: ""
    }
  },
  methods: {
    test() {
      axios.get('/v1/?key=a6d3bb26218771ec&format=json&lat=35.7111&lng=139.7611&range=500', {
        headers: {
          'Accept': 'application/json'
        }
      }
      )
      .then((res) => {
        console.log(res);
        this.m = res.data;
    });
    },
  }
}
</script>

<style>

</style>