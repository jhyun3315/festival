<template>
    <b-row>      
        <b-col>
            <div id="cal">
                <calendar
                        :first-day="1"
                        :all-events="events"
                ></calendar>
            </div>
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
</template>

<script>
import api from '@/util/api';
import Calendar from '@/views/Festival/Calendar/Calendar.vue'; 

  export default {
      name: 'cal',
      data() {
          return {
              festivals: [], 
              events: [],
              test:[],
              curr: "",
              text:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
          }
      },
      components: {
          Calendar
    },
    methods: {
        getData() {
            console.log(this.day.date._d);
         },
    },
 
    mounted() {    
        api.get(`/festival/list`).then(({ data }) => {
            if (data.status == "ok") { 
                let lst;
                lst = data.festivalList; 

                for (var i = 0; i <lst.length; i++) {
                    var obj = lst[i];  
                     this.events.push({
                        id: obj.festivalId,
                        title: obj.festivalName,
                        color: 'panel-danger',
                        date: obj.startDate,
                        end: obj.endDate,
                        info: obj.festivalContent,
                        origin: obj.originImage,
                        thumbnail: obj.thumbnailImage,
                        orgname: obj.orgName,
                        hompage: obj.hompage,
                        phonenumber: obj.phoneNumber  
                    })
 
                } 
            } else {
                alert("Error");
                this.$router.go();
            }
        })  
      },
  }
</script>
<style>
#listgroup-ex{
    margin-top: 150px;
    margin-left: 20px;
}
</style>