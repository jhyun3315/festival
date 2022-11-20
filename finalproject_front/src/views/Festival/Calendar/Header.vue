<template>
    <div class="row"> 
        <div class="col-sm-4 header-center">
            <div class="col-sm-4 center">
            <div class="title"> {{title}}</div> 
            </div>
            <div class="btn-group">
                <button @click.stop="goPrev" class="btn btn-outline btn-primary">&lArr; Previous</button>
                <button @click.stop="goToday" class="btn btn-outline btn-default today-button"> Today</button>
                <button @click.stop="goNext" class="btn btn-outline btn-primary"> &rArr; Next</button>
            </div>
        </div>
        </div>  
</template>
<script>
    import moment from 'moment';
    import {CHANGE_MONTH} from './actions';

    export default {
        data (){
            return {
                localeSelect:'en'
            }
        },
        props : {
            currentMonth : {},
            locale       : {
                type: String
            }
        },
        computed: {
            title () {
                if (!this.currentMonth) return;
                return this.currentMonth.locale(this.locale).format('MMMM YYYY')
            }
        },
        methods : {
            goPrev () {
                let payload = moment(this.currentMonth).subtract(1, 'months').startOf('month');
                this.$root.$emit(CHANGE_MONTH, payload);
            },
            goNext () {
                let payload = moment(this.currentMonth).add(1, 'months').startOf('month');
                this.$root.$emit(CHANGE_MONTH, payload);
            },
            goToday () {
                this.$root.$emit(CHANGE_MONTH, moment());
            },
            setLocale () {
                if(i18n) {
                    i18n.locale = this.localeSelect;
                }
                else {
                    console.warn('Please define global vue locale object named "i18n"!');
                }
            }
        }
    }
</script>
<style>
    .full-calendar-header{
        display: flex;
        align-items: center;
    }
    .header-center{
        flex:3;
        text-align:center;
    }
    .title {
        text-align: center;
        font-size: 1.5em;
        font-weight: bolder;
    }
    .language-select {
        display: inline-block;
        width: 50%;
    }
</style>