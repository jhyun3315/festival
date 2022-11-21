<template>
  <div> 
    <base-header class="pb-3 pb-6 pt-5 pt-md-8 bg-gradient-success" > 
 
        <div class="wrap-home-resources"> 
          <div class="home-resources-content">
            <div class="container">
              <div class="row">
                <div class="col-md-12">
                  <div id="carousel-home-resources" class="carousel-home-resources">
                    <carousel-3d :autoplay="true" :autoplay-timeout="5000"  
                                :perspective="30"
                                :border="0"
                                :width="960"
                                :height="540"
                                :controls-visible="true"
                                :space="500"
                                :clickable="true">
                                
                      <slide v-for="(slide, i) in slides" :index="i" :key="i">
                        <!--https://source.unsplash.com/user/erondu/960x540-->
                      <img src="https://source.unsplash.com/featured/?festival">
                    </slide>
                   </carousel-3d>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>  
    </base-header> 
    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row>
        <b-col xl="8" class="mb-5 mb-xl-0">
          <card type="default" header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                <h6 class="text-light text-uppercase ls-1 mb-1">Overview</h6>
                <h5 class="h3 text-white mb-0">Sales value</h5>
              </b-col>
              <b-col>
                <b-nav class="nav-pills justify-content-end">
                  <b-nav-item
                       class="mr-2 mr-md-0"
                       :active="bigLineChart.activeIndex === 0"
                       link-classes="py-2 px-3"
                       @click.prevent="initBigChart(0)">
                      <span class="d-none d-md-block">Month</span>
                      <span class="d-md-none">M</span>
                  </b-nav-item>
                  <b-nav-item
                    link-classes="py-2 px-3"
                    :active="bigLineChart.activeIndex === 1"
                    @click.prevent="initBigChart(1)"
                  >
                    <span class="d-none d-md-block">Week</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                </b-nav>
              </b-col>
            </b-row>
            <line-chart
              :height="350"
              ref="bigChart"
              :chart-data="bigLineChart.chartData"
              :extra-options="bigLineChart.extraOptions"
            >
            </line-chart>
          </card>
        </b-col>

        <b-col xl="4" class="mb-5 mb-xl-0">
          <card header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                <h6 class="text-uppercase text-muted ls-1 mb-1">Performance</h6>
                <h5 class="h3 mb-0">Total orders</h5>
              </b-col>
            </b-row>

            <bar-chart
              :height="350"
              ref="barChart"
              :chart-data="redBarChart.chartData"
            >
            </bar-chart>
          </card>
        </b-col>
      </b-row>
      <!-- End charts-->

      <!--Tables-->
      <b-row class="mt-5">
        <b-col xl="8" class="mb-5 mb-xl-0">
          <page-visits-table></page-visits-table>
        </b-col>
        <b-col xl="4" class="mb-5 mb-xl-0">
          <social-traffic-table></social-traffic-table>
        </b-col>
      </b-row>
      <!--End tables-->
    </b-container>

  </div>
</template>
<script>
  import { Carousel3d, Slide} from 'vue-carousel-3d';

  export default {
    components: {
      Carousel3d,
      Slide 
    },
  data() {
    return {
        slides: 4, 

      };
    },
  methods: { 
  }
}
 
</script>
<style>  
.carousel-home-resources .left-1{ 
  transform: translateX(-500px) translateZ(-400px) rotateY(-30deg) !important;
}

.carousel-home-resources .right-1 {
  transform: translateX(500px) translateZ(-400px) rotateY(30deg) !important;
}
</style>
