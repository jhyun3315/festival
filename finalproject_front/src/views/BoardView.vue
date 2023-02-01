<template>
<div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-5 bg-gradient-success"> 
      <h1 class="display-2 text-white">{{festivalInfo.festivalName}} 축제게시판</h1>
    </base-header> 
    <router-view></router-view>
</div>
</template>

<script>
import {getFestival} from "@/util/festivalApi.js"
export default {
  data(){
    return{
      festivalInfo:{}
    }
  },
  created(){
    getFestival(
      this.$route.params.festivalId,
      ({data})=>{
        this.festivalInfo = data.festival;
      },
      ()=>{
        console.log("오류")
      })
  }
}
</script>

