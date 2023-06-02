<template>
  <div>
    <button @click="test">btn</button>
    <SearchStoreVue></SearchStoreVue>
  </div>
</template>

<script>
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
      console.log('a');
    },
  }
}
</script>

<style>

</style>