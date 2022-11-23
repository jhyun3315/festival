<template>
    <b-card
        overlay
        :img-src="checkImage(festivalInfo.originImage)"
        :img-alt="festivalInfo.festivalName"
        text-variant="white"
        :title="festivalInfo.festivalName"
        :sub-title="festivalInfo.startDate"
    >
    </b-card>
</template>

<script>
import {getDefaultImage} from "@/util/boardApi"

export default {
    props:{
        festivalInfo:Object
    },
    methods:{
        checkImage(imgsrc){
            if(imgsrc){
                return imgsrc;
            }else{
                return getDefaultImage();
            }
        }
    }
}
</script>
