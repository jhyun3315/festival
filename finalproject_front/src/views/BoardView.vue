<template>
<div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-5 bg-gradient-success"> 
      <h1 class="display-2 text-white">반딧불 축제게시판</h1>
    </base-header> 
    <router-view></router-view>
</div>
</template>

<script>
export default {
  created(){
    console.log("확인")
  }
}
</script>

