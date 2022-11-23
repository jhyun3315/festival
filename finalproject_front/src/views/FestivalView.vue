<template>
  <div>
    <base-header class="pb-2 pt-5 pt-md-1 bg-gradient-success">
      <h1 class="display-2 text-white">지역 축제현황 캘린더</h1>
    </base-header>
    <b-container fluid>
      <b-row>
        <b-col class="row" id="calframe">
          <calendar
            :first-day="1"
            :all-events="events"
            @changeDate="changeDate"
          ></calendar>
        </b-col>
        <b-col cols="6">
          <div
            id="listgroup-ex"
            style="overflow-y: auto; width: 600px; height: 850px"
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
import { getEveryFestival, getMonthFestival } from "@/util/festivalApi.js";
import { getDefaultImage } from "@/util/boardApi";
import Calendar from "@/views/Festival/Calendar/Calendar.vue";

export default {
  name: "cal",
  data() {
    return {
      date: "",
      festivals: [],
      events: [],
      test: [],
      curr: "",
      text: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    };
  },
  components: {
    Calendar,
  },
  methods: {
    changeDate(date) {
      this.date = date;
      // console.log(date);
      getMonthFestival(date, ({ data }) => {
        this.festivals = data.festivalList;
        console.log(this.festivals);
      });
    },

    defaultImage() {
      return getDefaultImage();
    },
  },

  mounted() {
    getEveryFestival(({ data }) => {
      if (data.status == "ok") {
        console.log("데이터 테스트");
        let lst = data.festivalList;
        this.festivals = data.festivalList;
        // console.log(this.festivals);

        for (var i = 0; i < lst.length; i++) {
          var obj = lst[i];
          this.events.push({
            id: obj.festivalId,
            title: obj.festivalName,
            color: "panel-danger",
            date: obj.startDate,
            end: obj.endDate,
            info: obj.festivalContent,
            origin: obj.originImage,
            thumbnail: obj.thumbnailImage,
            orgname: obj.orgName,
            hompage: obj.hompage,
            phonenumber: obj.phoneNumber,
          });
        }
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
  margin-top: 130px;
  /* margin-left: 10px; */
}

#calframe {
  margin-left: 20px;
  margin-top: 20px;
}

#listgroup-ex::-webkit-scrollbar {
  display: none;
}
</style>
