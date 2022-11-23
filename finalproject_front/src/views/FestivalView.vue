<template>
      <div>
      <base-header class="pb-2 pt-5 pt-md-1 bg-gradient-success">
        <h1 class="display-2 text-white">지역 축제현황 캘린더</h1>
      </base-header>
      <b-container fluid >
        <b-row>
          <b-col class="rows" id="calframe">
            <FullCalendar :options="calendarOptions" ref="fullCalendar"/>
          </b-col> 
        <b-col cols="6">
            <div
              id="listgroup-ex"
              style="overflow-y: auto; width: 600px; height: 940px"
            >
              <!--축제 리스트-->
              <div v-for="(ele, i) in festivals" :key="i">
                <b-card
                  no-body
                  class="overflow-hidden"
                  style="max-width: 600px; max-height: 400px"
                >
                  <b-row no-gutters align-v="center">
                    <b-col md="6">
                      <b-card-img
                        v-if="ele.originImage !== ''"
                        :src="ele.originImage"
                        :alt="ele.festivalName"
                        class="rounded-0"
                      ></b-card-img>
                      <b-card-img
                        v-else
                        :src="defaultImage()"
                        alt="기본이미지"
                        class="rounded-0"
                      ></b-card-img>
                    </b-col>
                    <b-col md="6">
                      <b-card-body :title="ele.festivalName">
                        <b-card-text>
                          {{ ele.startDate }} ~ {{ ele.endDate }}
                        </b-card-text>
                        <b-card-text>
                          <div
                            class="festivalContent"
                            style="overflow-y: auto; width: 270px; height: 200px"
                          >
                            {{ ele.festivalContent }}
                          </div>
                        </b-card-text>
                        <div class="buttonRight">
                          <b-button
                            size="sm"
                            variant="primary"
                            v-b-modal.modal-center
                            >상세보기</b-button
                          >
                          <b-button size="sm" variant="success">
                            즐겨찾기 등록
                          </b-button>
                        </div>
                      </b-card-body>
                    </b-col>
                  </b-row>
                </b-card>
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
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import { getEveryFestival, getMonthFestival } from "@/util/festivalApi.js";
import { getDefaultImage } from "@/util/boardApi"; 
import { mapGetters } from 'vuex';

export default {
  components: {
    FullCalendar
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
      colors: [
        "#FFB2D9", "#8BBDFF", "#FFE08C", "#F15F5F", "#86E57F",
        "#8F8AFF", "#5CD1E5", "#BCE55C", "#8C8C8C", "#F29661"
      ],
      curr: "",
      text: "",
    }
  },
  methods: {

    handleMonthChange(date) {
      alert(date);  
    },

    randomColor() {
      return this.colors[Math.floor(Math.random() * 10)];
    },

    handleEventClick(event) {
      console.log(event.event._def.extendedProps.festival);  
    },

    handleEvents(events) {
      this.currentEvents = events;
    },
    handleDateClick: function (arg) {
      alert('date click! ' + arg.dateStr)
    },
    changeDate(date) {
        this.date = date;
        // console.log(date);
        getMonthFestival(date, ({ data }) => {
          this.festivals = data.festivalList;
          // console.log(this.festivals);
        });
      },
      defaultImage() {
        return getDefaultImage();
      },
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
  };
</script>
 <style>
  #listgroup-ex {
    margin-top: 115px;
    margin-left: 60px;
  }
  
  #calframe {
    margin-left: 60px;
    margin-top: 50px;
  }
  
  #listgroup-ex::-webkit-scrollbar {
    display: none;
  }
  </style>
