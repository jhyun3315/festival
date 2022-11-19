<template>   
  <div>
    <div class="header pb-8 pt-5 pt-lg-8 d-flex" > 
    </div>

    <b-container fluid class="mt--6">
      <b-row>
        
        <!-- <b-col>
            <b-button class="btn btn-info">축제 지도</b-button>
            <festival-map></festival-map> 
        </b-col>  -->
        <b-col>
            <b-button class="btn btn-info">축제 캘린더</b-button> 
            <festival-calendar></festival-calendar>
          </b-col>
      <b-col>
        <b-container fluid>
    <b-row>
      <b-col cols="4">
        <b-list-group v-b-scrollspy:listgroup-ex>
          <b-list-group-item href="#list-item-1">Item 1</b-list-group-item>
          <b-list-group-item href="#list-item-2">Item2</b-list-group-item>
          <b-list-group-item href="#list-item-3">Item 3</b-list-group-item>
          <b-list-group-item href="#list-item-4">Item 4</b-list-group-item>
          <b-list-group-item href="#list-item-5">Item 5</b-list-group-item>
        </b-list-group>
      </b-col>

      <b-col cols="6">
        <div id="listgroup-ex" style="overflow-y:auto; height:6npm00px">
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
      </b-col>
      
    </b-row>
    </b-container>
  </div>
</template>  
<script> 
import FestivalMap from '@/views/Festival/DrawMap';  
import FestivalCalendar from '@/views/Festival/FestivalCalendar';
import table from '@/views/Tables/RegularTables/LightTable.vue';

export default { 
    components: {
        FestivalMap,
    FestivalCalendar,
    table
  },
  data() {
    return {
      text: `
          Quis magna Lorem anim amet ipsum do mollit sit cillum voluptate ex nulla
          tempor. Laborum consequat non elit enim exercitation cillum aliqua
          consequat id aliqua. `

    }
  },
  props: {
    columns: {
      type: Array,
      default: () => [],
      description: 'Table columns'
    },
    data: {
      type: Array,
      default: () => [],
      description: 'Table data'
    },
    type: {
      type: String, // striped | hover
      default: '',
      description: 'Whether table is striped or hover type'
    },
    theadClasses: {
      type: String,
      default: '',
      description: '<thead> css classes'
    },
    tbodyClasses: {
      type: String,
      default: '',
      description: '<tbody> css classes'
    }
  },
  computed: {
    tableClass() {
      return this.type && `table-${this.type}`;
    }
  },
  methods: {
    hasValue(item, column) {
      return item[column.toLowerCase()] !== 'undefined';
    },
    itemValue(item, column) {
      return item[column.toLowerCase()];
    }
  }
}
    
</script>  