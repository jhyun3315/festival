<template>
  <b-container class="map"> 
    <b-row>
        <b-col >
          <festival-map></festival-map>
      </b-col>
      <b-col cols="6">
			<div id="listgroup-ex" style="overflow-y:auto; width:500px; height:600px">
                <h4 id="list-item-1">Item 1</h4>
                <p>{{ text }}</p>
                <h4 id="list-item-2">Item 2</h4>
                <p>{{ text }}</p>
                <h4 id="list-item-3">Item 3</h4>
                <p>{{ text }}</p>
                <h4 id="list-item-4">Item 4</h4>
                <p>{{ text }}</p>
                <h4 id="list-item-5">Item 5</h4>
                <p>{{ text }}</p>
		    </div>
		</b-col>    
  </b-row>
</b-container>
</template>
<script>

import FestivalMap from '@/views/Festival/DrawMap';  
export default { 
    components: {
        FestivalMap, 
  },
  data() {
    return {
      text:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        
    }
  },

}
</script>
<style >
 .map{
    margin-top: 200px;
  }
</style>