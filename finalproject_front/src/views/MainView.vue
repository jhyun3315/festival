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
                                
                      <slide v-for="(slide, i) in slides" :index="i">
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

     

  </div>
</template>
<script>
  // Charts
  import * as chartConfigs from '@/components/Charts/config';
  import LineChart from '@/components/Charts/LineChart';
  import BarChart from '@/components/Charts/BarChart';

  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard'; 
  // Tables
  import SocialTrafficTable from './Main/SocialTrafficTable';
  import PageVisitsTable from './Main/PageVisitsTable'; 

  import { Carousel3d, Slide} from 'vue-carousel-3d';

  export default {
    components: {
      LineChart,
      BarChart,
      BaseProgress,
      StatsCard,
      PageVisitsTable,
      SocialTrafficTable,
      Carousel3d,
    Slide 
    },
  data() {
    return {
        slides: 4, 
            
        bigLineChart: {
          allData: [
            [0, 20, 10, 30, 15, 40, 20, 60, 60],
            [0, 20, 5, 25, 10, 30, 15, 40, 40]
          ],
          activeIndex: 0,
          chartData: {
            datasets: [
              {
                label: 'Performance',
                data: [0, 20, 10, 30, 15, 40, 20, 60, 60],
              }
            ],
            labels: ['May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          },
          extraOptions: chartConfigs.blueChartOptions,
        },
        redBarChart: {
          chartData: {
            labels: ['Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            datasets: [{
              label: 'Sales',
              data: [25, 20, 30, 22, 17, 29]
            }]
          },
          extraOptions: chartConfigs.blueChartOptions
        }, 
      };
    },
  methods: { 
    onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      },
      initBigChart(index) {
        let chartData = {
          datasets: [
            {
              label: 'Performance',
              data: this.bigLineChart.allData[index]
            }
          ],
          labels: ['May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        };
        this.bigLineChart.chartData = chartData;
        this.bigLineChart.activeIndex = index;
      }
    },
    mounted() {
      this.initBigChart(0); 
    }
};
 
</script>
<style>  
.carousel-home-resources{
  .left-1{ 
    transform: translateX(-500px) translateZ(-400px) rotateY(-30deg) !important;
  }

  .right-1 {
    transform: translateX(500px) translateZ(-400px) rotateY(30deg) !important;
  }
}

</style>
