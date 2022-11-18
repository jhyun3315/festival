<template>
    <div id="FestivalCalendar">
        <calendar
                :first-day="1"
                :all-events="events"
        ></calendar>
    </div>
</template>

<script>
    import FestivalCalendar from 'vue-bootstrap-calendar';
    export default {
        name: 'FestivalCalendar',
        data() {
            return {
                events: []
            }
        },
        components: {
            FestivalCalendar
        },
        mounted() {
            let me = this;
            setTimeout(function () {
                me.events = [ // you can make ajax call here
                    {
                        id:1,
                        title:'Event 1',
                        color: 'panel-danger',
                        date: new Date()
                    }, 
                ];
            }, 1000);
        }
    }

</script>