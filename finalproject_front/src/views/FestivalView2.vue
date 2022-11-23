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
          <b-card no-body class="overflow-hidden" style="max-width: 600px; max-height: 400px;" >
            <b-row no-gutters align-v="center">
              <b-col md="6" >
                <b-card-img v-if="ele.originImage!==''" :src="ele.originImage"  :alt="ele.festivalName" class="rounded-0"></b-card-img>
                <b-card-img v-else :src="defaultImage()"  alt="기본이미지" class="rounded-0"></b-card-img>
              </b-col>
              <b-col md="6">
                <b-card-body :title="ele.festivalName">
                  <b-card-text >
                    {{ele.startDate}} ~ {{ele.endDate}}
                  </b-card-text>
                  <b-card-text >
                    <div class="festivalContent">
                      {{ele.festivalContent}}
                    </div>
                  </b-card-text>
                  <div class="buttonRight">
                    <b-button size="sm" variant="primary" v-b-modal.modal-center>상세보기</b-button>
                    <b-button size="sm" variant="success">
                      즐겨찾기 등록
                    </b-button>
                  </div>
                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
        </div>
        <div v-if="festivals.length===0 && area!==''">
          진행중인 축제가 없어요!
        </div>
        <!-- -->

      </div>
		</b-col>    
  </b-row>
</b-container>

<!--상세 축제 정보 모달-->
<b-modal id="modal-center" centered title="BootstrapVue">
  <p class="my-4">Vertically centered modal!</p>
</b-modal>

</div>
</template>
<script>
import {getAreaFestival} from "@/util/festivalApi.js"
import {getDefaultImage} from "@/util/boardApi"


import FestivalMap from '@/views/Festival/DrawMap';  
export default { 
  components: {
      FestivalMap, 
  },
  data() {
    return {
      area:"",
      festivals:[]
    }
  },
  methods:{
    getFestival(area){
      this.area = area;
      getAreaFestival(
        area,
        ({data})=>{
          this.festivals = data.festivalList;
          console.log(this.festivals)
        },
        ()=>{
          alert("에러야")
        })
    },
    defaultImage(){
      return getDefaultImage();
    }
  }

}
</script>
<style >
.map{
  margin-top: 10px;
}
.festivalContent{
  white-space: normal;
  overflow-y: hidden;
  height: 120px;
}
.buttonRight{
  display: flex;
  justify-content: right;
}
</style>