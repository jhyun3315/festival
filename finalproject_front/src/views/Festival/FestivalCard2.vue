<template>
<div>
    <b-card no-body class="overflow-hidden" style="max-width: 600px; max-height: 400px;" >
        <b-row no-gutters align-v="center">
            <b-col md="6" >
                <b-card-img :src="checkImage(festivalInfo.originImage)"  :alt="festivalInfo.festivalName" class="rounded-0"></b-card-img>
            </b-col>
            <b-col md="6">
            <b-card-body :title="festivalInfo.festivalName">
                <b-card-text >
                {{festivalInfo.startDate}} ~ {{festivalInfo.endDate}}
                </b-card-text>
                <b-card-text >
                <div class="festivalContent" v-html="festivalInfo.festivalContent">
                    {{festivalInfo.festivalContent}}
                </div>
                </b-card-text>
                <hr class="my-4"/>
                <div class="buttonRight">
                <b-button size="sm" variant="danger" @click="goBoard(festivalInfo.festivalId)">게시판</b-button>
                <b-button size="sm" variant="primary" v-b-modal="festivalInfo.festivalId">상세보기</b-button>
                <b-button size="sm" variant="success">
                    즐겨찾기 등록
                </b-button>
                </div>
            </b-card-body>
            </b-col>
        </b-row>
    </b-card>
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

</div>

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
        }
    }
}
</script>
