<template>
    <div>
    <base-header class="pb-2 pt-5 pt-md-1 bg-gradient-success">
      <h1 class="display-4 text-white">지역 축제현황 캘린더</h1>
    </base-header>
    <b-container fluid >
        <b-row>
            <b-col class="rows" id="calframe">
                <router-view v-on:getFestival="getFestival" :options="calendarOptions" ref="fullCalendar" :area="area"></router-view>
            </b-col> 
        <b-col cols="6" class="festival-wrap">
            <div>
                <b-button @click="goFestivalV('/calendar')" :variant="colorCheck('/calendar')">캘린더</b-button>
                <b-button @click="goFestivalV('/map')" :variant="colorCheck('/map')">지도</b-button>
            </div>
            <div class="festival-list">
                <!--축제 리스트-->
                <div v-for="(ele, i) in festivals" :key="i">
                <festival-card :festivalInfo="ele"></festival-card>
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
import { getEveryFestival, getNowFestival, getMonthFestival } from "@/util/festivalApi.js";
import { getDefaultImage } from "@/util/boardApi";  
import FestivalCard from "@/views/Festival/FestivalSmallCard.vue"
import {getAreaFestival} from "@/util/festivalApi.js"

export default {
components: {
  FestivalCard
},
mounted() {
 getEveryFestival(({ data }) => {
  if (data.status == "ok") {
    // console.log("데이터 테스트");
    let lst = data.festivalList;
    this.festivals = data.festivalList;
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
      customButtons: { 
        today: { // this overrides the prev button
          text: "TODAY",
          click: () => {
            let calendarApi = this.$refs.fullCalendar.getApi();
            calendarApi.today();
            let date = document.getElementsByClassName("fc-toolbar-title")[0].innerText;
            var dateString = date.split(" ");
            var dat = new Date('1 ' + dateString[0] + ' 1999');
            var firstDate = (dateString[1] + "-" + (dat.getMonth() + 1) + "-01"); 
          
          this.changeDate(firstDate);
          }
        },
      prev: {
        text: "PREV", 
        click: () => {       
          let calendarApi = this.$refs.fullCalendar.getApi();
          calendarApi.prev();
          let date = document.getElementsByClassName("fc-toolbar-title")[0].innerText;
          var dateString = date.split(" ");
          var dat = new Date('1 ' + dateString[0] + ' 1999');
          var firstDate = (dateString[1] + "-" + (dat.getMonth() + 1) + "-01"); 
          
          this.changeDate(firstDate);

        }
      },
      next: { // this overrides the next button
        text: "NEXT",
        click: () => {
          
          let calendarApi = this.$refs.fullCalendar.getApi(); 
          calendarApi.next(); 
          let date = document.getElementsByClassName("fc-toolbar-title")[0].innerText;
          var dateString = date.split(" ");
          var dat = new Date('1 ' + dateString[0] + ' 1999');
          var firstDate = (dateString[1] + "-" + (dat.getMonth() + 1) + "-01"); 

          this.changeDate(firstDate);
        }
      }
    },
      height: 800,
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
  colorCheck(path){
    if(this.$router.currentRoute.path===path){
        return "primary"
    }else{
        return "light"
    }
  },
  goFestivalV(path){   
    if(this.$router.currentRoute.path!==path){
        this.festivals=[]
        this.area=""
        this.$router.push(path)
        if(path==="/calendar"){
            let date = new Date();
            // console.log(date)
            let firstDate = `${date.getFullYear()}-${date.getMonth()+1}-01`
            // console.log(firstDate)
            this.changeDate(firstDate);
        }
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
  changeDate(date) {
    this.date = date;
    console.log(date);
    getMonthFestival(date, ({ data }) => {
    this.festivals = data.festivalList;
    console.log(this.festivals);
    });
  },
  defaultImage() {
    return getDefaultImage();
  },
  getFestival(area) {
        this.area = area;
        getAreaFestival(
        area,
        ({ data }) => {
            this.festivals = data.festivalList;
        },
        () => {
            alert("Error");
        }
        );
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
  height: 750px;
}

#calframe {
  margin-left: 60px;
  margin-top: 50px;
}

#listgroup-ex::-webkit-scrollbar {
  display: none;
}
</style>
