<template>   
  <div>
    <div class="header pb-8 pt-5 pt-lg-8 d-flex" > 
    </div>

    <b-container fluid class="mt--6">
      <b-row>
        
        <b-col>
            <b-button class="btn btn-info">축제 지도</b-button>
            <festival-map></festival-map> 
            <b-button class="btn btn-info">축제 캘린더</b-button>
            <festival-calendar></festival-calendar>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
 

<script>
import FestivalMap from '@/views/Festival/DrawMap';  
import FestivalCalendar from '@/views/Festival/FestivalCalendar';

export default {
    components: {
        FestivalMap,
        FestivalCalendar
    }
}
    
</script>  