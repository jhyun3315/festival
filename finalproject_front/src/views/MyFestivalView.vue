<template>
    <div>
    <base-header class="pb-2 pt-5 pt-md-1 bg-gradient-success">
      <h1 class="display-2 text-white">나의 축제</h1>
    </base-header>
    <b-container fluid >
        <b-row>
            <b-col class="rows" id="calframe">
                <router-view v-on:getFestival="getFestival" :options="calendarOptions" ref="fullCalendar" :area="area"></router-view>
            </b-col> 
        <b-col cols="6" class="festival-wrap">
            <div>
                <b-button @click="goFestivalV('/mycalendar')" :variant="colorCheck('/mycalendar')">캘린더</b-button>
                <b-button @click="goFestivalV('/mymap')" :variant="colorCheck('/mymap')">지도</b-button>
            </div>
            <div class="festival-list">
                <!--축제 리스트-->
                <div v-for="(ele, i) in festivals" :key="i">
                <festival-card :festivalInfo="ele" myfestival="true" v-on:refreshData="getMyFestival"></festival-card>
                </div>
                <!-- -->
            </div>
            </b-col> 
        </b-row>
    </b-container>
  </div>
</template>


<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import { getDefaultImage } from "@/util/boardApi";  
import FestivalCard from "@/views/Festival/FestivalSmallCard.vue"
import {mapActions} from "vuex";
import {favorGet} from "@/util/favorApi.js"
const memberStore = "memberStore";

export default {
components: {
  FestivalCard
},
async mounted() {
  await this.getMyFestival();
}, 
data() {
  return {
    calendarOptions: {
      plugins: [
        dayGridPlugin, 
        interactionPlugin, // needed for dateClick
      ],
      headerToolbar: { 
        left: '',
        right: "prev today next",
        center: "title", 
      },
      height: 1000,
      events : [],
      initialEvents: [], // alternatively, use the `events` setting to fetch from a feed
      // editable: true,
      selectable: true,
      selectMirror: true,
      dayMaxEvents: true,
      weekends: true,
      // select: this.handleDateSelect,
      eventClick: this.handleEventClick,
      eventsSet: this.handleEvents
    },

    date: "",
    events: [],
    festivals: [],
    backupFestivals: [],  
    areaFestivals:[],
    colors: [
      "#FFB2D9", "#8BBDFF", "#FFE08C", "#F15F5F", "#86E57F",
      "#8F8AFF", "#5CD1E5", "#BCE55C", "#8C8C8C", "#F29661"
    ],
    curr: "",
    text: "",
    area:"",
  }
},
methods: { 
  ...mapActions(memberStore, ["getUserInfo"]),
  async getMyFestival(){
    await this.getUserInfo(); // 토큰 확인 및 재발급진행
    await favorGet(({ data }) => {
    if (data.status == "ok") {
      // console.log("데이터 테스트");
      let lst = data.favorlist;
      this.festivals = data.favorlist;
      this.backupFestivals = data.favorlist;
      this.events=[]
        // console.log(this.festivals);  
        for (var i = 0; i < lst.length; i++) {
          var obj = lst[i];  
          let tmp = new Date(obj.endDate);
          this.events.push({
            id: obj.festivalId,
            title: obj.festivalName, 
            date: new Date(obj.startDate),
            end: tmp.setDate(tmp.getDate()+1),  
            backgroundColor: this.randomColor(),
            borderColor: "#FFFFFF",
            allDay: true,
            festival: obj
          });
      }
      this.calendarOptions.events = this.events; 
      } else {
        alert("Error");
        this.$router.go();
      } 
    });
  },
  colorCheck(path){
    if(this.$router.currentRoute.path===path){
        return "primary"
    }else{
        return "light"
    }
  },
  goFestivalV(path){   
    if(this.$router.currentRoute.path!==path){
      this.festivals = this.backupFestivals;
      this.area=""
      this.$router.push(path)
    }
  },
  randomColor() {
    return this.colors[Math.floor(Math.random() * 10)];
  },

  handleEventClick(event) {
    this.$bvModal.show(event.event._def.extendedProps.festival.festivalId)
  },
  handleEvents(events) {
    this.currentEvents = events;
  },
  handleDateClick: function (arg) {
    alert('date click! ' + arg.dateStr)
  },
  defaultImage() {
    return getDefaultImage();
  },
  getFestival(area) {
    this.area = area;
    this.festivals = this.backupFestivals.filter(ele=>{
      return ele.address.includes(area)
    })
  },
},
};
</script>

<style scoped>
.festival-wrap{
  margin-top: 50px;
  margin-left: 60px;
}
.festival-list {
  margin-top: 20px;
  overflow-y: auto; 
  width: 600px; 
  height: 940px;
}

#calframe {
  margin-left: 60px;
  margin-top: 50px;
}

#listgroup-ex::-webkit-scrollbar {
  display: none;
}
</style>
