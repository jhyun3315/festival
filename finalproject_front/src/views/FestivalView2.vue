<template>
  <div>
    <base-header class="pb-2 pt-5 pt-md-1 bg-gradient-success">
      <h1 class="display-2 text-white">지역 축제현황</h1>
    </base-header>
    <b-container class="map">
      <b-row align-h="center" align-v="center">
        <b-col>
          <festival-map v-on:getFestival="getFestival"></festival-map>
      </b-col>
      <b-col cols="6">
			<div id="listgroup-ex" style="overflow-y:auto; width:600px; height:600px">
        <!--축제 리스트-->
        <div v-for="(ele,i) in festivals" :key="i">
          <festival-card2 :festivalInfo="ele"></festival-card2>
        </div>
        <div v-if="festivals.length===0 && area!==''">
          진행중인 축제가 없어요!
        </div>
      </div>
		</b-col>    
  </b-row>
</b-container>
</div>
</template>
<script>
import {getAreaFestival} from "@/util/festivalApi.js"
import FestivalCard2 from "@/views/Festival/FestivalCard2.vue"

import FestivalMap from "@/views/Festival/DrawMap";
export default {
  components: {
      FestivalMap, 
      FestivalCard2
  },
  data() {
    return {
      area:"",
      festivals:[],
      festivalInfo:{}
    }
  },
  methods: {
    getFestival(area) {
      this.area = area;
      getAreaFestival(
        area,
        ({ data }) => {
          this.festivals = data.festivalList;
        },
        () => {
          alert("에러야");
        }
      );
    },
  }

}
</script>
<style>
.map {
  margin-top: 50px;
}
.festivalContent {
  white-space: normal;
  overflow-y: hidden;
  height: 110px;
}
.buttonRight {
  display: flex;
  justify-content: right;
}
</style>