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
