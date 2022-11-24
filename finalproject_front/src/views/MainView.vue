<template>
  <div>
    <base-header class="pb-3 pb-6 pt-5 pt-md-8 bg-gradient-success">
      <div class="wrap-home-resources">
        <div class="home-resources-content">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <div
                  id="carousel-home-resources"
                  class="carousel-home-resources"
                >
                  <div v-if="festivals.length">
                    <carousel-3d
                      :autoplay="true"
                      :autoplay-timeout="5000"
                      :perspective="30"
                      :border="0"
                      :width="960"
                      :height="540"
                      :controls-visible="true"
                      :space="500"
                      :clickable="true"
                    >
                      <!-- <slide v-for="(slide, i) in slides" :index="i" :key="i">
                          <img src="https://source.unsplash.com/featured/?festival">
                        </slide> -->
                      <slide v-for="(ele, i) in festivals" :index="i" :key="i">
                        <!-- <img :src="checkImage(slide.originImage)"> -->
                        <festival-card :festivalInfo="ele"></festival-card>
                      </slide>
                    </carousel-3d>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </base-header>
  </div>
</template>
<script>
import { Carousel3d, Slide } from "vue-carousel-3d";
import { getNowFestival } from "@/util/festivalApi.js";
import FestivalCard from "@/views/Festival/FestivalBigCard.vue";

export default {
  created() {
    getNowFestival(
      ({ data }) => {
        this.festivals = data.festivalList;
      },
      () => {
        console.log("에러야");
      }
    );
  },
  components: {
    Carousel3d,
    Slide,
    FestivalCard,
  },
  data() {
    return {
      festivals: [],
    };
  },
  methods: {},
};
</script>
<style>
.carousel-home-resources .left-1 {
  transform: translateX(-500px) translateZ(-400px) rotateY(-30deg) !important;
}

.carousel-home-resources .right-1 {
  transform: translateX(500px) translateZ(-400px) rotateY(30deg) !important;
}
</style>
