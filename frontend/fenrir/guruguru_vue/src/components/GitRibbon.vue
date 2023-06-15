<template>
  <div>
    <a href="https://github.com/bys096/bys_guruguru" id="ribbon" target="_blank">
      <i class="fa fa-github" aria-hidden="true"></i>
      View on GitHub
    </a>
  </div>
</template>

<script>
export default {

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
</style>