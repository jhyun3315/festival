<template>
    <b-card
        overlay
        :img-src="checkImage(festivalInfo.originImage)"
        :img-alt="festivalInfo.festivalName"
        img-height="455"
        text-variant="white"
        class="imgRatio"
    >
    <b-card-title>
        <b-button size="sm" variant="success" @click="goBoard(festivalInfo.festivalId)">게시판으로 </b-button>
        <b-button size="sm" variant="primary" v-b-modal="festivalInfo.festivalId">상세보기</b-button>
    </b-card-title>
    <template #header>
        <h3 class="mb-0">{{festivalInfo.festivalName}}</h3>
        <h5  class="mb-0">지역: {{festivalInfo.address}} 일시: {{festivalInfo.startDate}}</h5>
    </template>
    <!--상세 축제 정보 모달-->
    <b-modal  size="lg" :id="festivalInfo.festivalId"  centered :title="festivalInfo.festivalName" button-size="sm" hide-footer>
        <b-container>
            <b-row>
                <h1>{{festivalInfo.festivalName}}</h1>
            </b-row>
            <b-row>
            <b-col>주소 : {{festivalInfo.address}}</b-col>
            <b-col>날짜 : {{festivalInfo.startDate}}~{{festivalInfo.endDate}}</b-col>
            </b-row>
            <b-row>
            <b-col cols="6">
                <b-img :src="checkImage(festivalInfo.originImage)" fluid :alt="festivalInfo.festivalName"></b-img>
            </b-col>
            <hr class="my-4"/>
            <b-col cols="6">
                주관 : {{festivalInfo.orgName}}<br>
                연락처 : {{festivalInfo.phoneNumber}}<br>
                <template v-if="festivalInfo.homepage">
                홈페이지 : <div v-html="festivalInfo.homepage"></div><br>
                </template>
                <b-button size="sm" variant="success" @click="goBoard(festivalInfo.festivalId)">게시판으로 </b-button><br>
            </b-col>
            </b-row>
            <b-row v-html="festivalInfo.festivalContent">
            </b-row>
        </b-container>
    </b-modal>
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
        },
        goBoard(festivalId){
            this.$router.push(`/board/${festivalId}`)
        },
    }
}
</script>

<style scoped>
.imgRatio img{
    object-fit: contain;
}
</style>
