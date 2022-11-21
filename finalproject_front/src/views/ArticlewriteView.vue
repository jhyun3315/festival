<template>
<div>
  <base-header class="pb-6 pb-8 pt-5 pt-md-4 bg-gradient-success"> 
    <h1 class="display-2 text-white">반딧불 축제게시글 작성하기</h1>
  </base-header> 
  
  <!-- <div class="write-wrap  pt-md-4"> -->
  <b-container>
    <b-row>
      <b-col class="card-center">
        <card class="col-md-6">
          <b-row>
            말머리
            <b-form-select v-model="cate" :options="cateoptions" ></b-form-select>
          </b-row>
          <b-row>
            제목
            <b-form-input v-model="title" required></b-form-input>
          </b-row>
          <b-row>
            내용
            <b-form-textarea rows="8" no-resize v-model="content" required></b-form-textarea>
          </b-row>
          <b-row>
            <b-form-file accept="image/*" v-model="file" class="mt-3" plain></b-form-file>
          </b-row>
          <b-row align-h="center" align-v="center" >
            <div>
              <b-button variant="primary" @click="writeSubmit">
                작성
              </b-button>
              <b-button variant="danger">
                취소
              </b-button>
            </div>

          </b-row>
        </card>
      </b-col>
    </b-row>
  </b-container>

  
  <!-- </div> -->
   
</div>
</template>

<script>
import { mapState} from "vuex";
import {fileApi} from "@/util/api.js";
const memberStore = "memberStore";

export default {
 data(){
  return{
    festivalId:"1095732", // 페스티벌 아이디
    cate:"",
    cateoptions:[
    { value: "", text: '말머리를 선택해주세요' },
    { value: "deal", text: '거래' },
    { value: "hugi", text: '후기' }
    ],
    title:"",
    content:"",
    file:null,
  }
 },
 methods:{
  writeSubmit(){
    let formData = new FormData(); // formData 객체를 생성한다.

    formData.append("userId", this.userInfo.userId);//작성자 유저 아이디
    formData.append("festivalId", this.festivalId);

    formData.append("cate", this.cate);
    formData.append("title", this.title);
    formData.append("content", this.content);
    formData.append("file", this.file);
    fileApi.post("/board/testfile",formData)
  }
 },computed:{
    ...mapState(memberStore, ["userInfo"]),
  }
}

</script>

<style>
.write-wrap{
  display: flex;
  justify-content: center;
  width:1000px;
}

.card-center{
  display: flex;
  justify-content: center;
  /* background-color: red; */
}

</style>

