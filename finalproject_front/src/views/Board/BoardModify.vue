<template>
<b-container>
<b-row>
    <b-col class="card-center">
    <card class="col-md-6">
        <b-row>
        말머리
        <b-form-select v-model="cate" :options="cateoptions"></b-form-select>
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
        <b-form-file accept="image/*" v-model="file" class="mt-3" plain  ></b-form-file>
        </b-row>
        <b-row align-h="center" align-v="center" >
        <div>
            <b-button variant="primary" @click="modiSubmit">
            수정
            </b-button>
            <b-button variant="danger" @click="modiCancle">
            취소
            </b-button>
        </div>

        </b-row>
    </card>
    </b-col>
</b-row>
</b-container>
</template>

<script>
import {mapState,  mapActions} from "vuex";
import {articleUpdate,getArticle} from "@/util/boardApi.js";
const memberStore = "memberStore";

export default {
  created(){
    this.festivalId = this.$route.params.festivalId;
    this.boardId = this.$route.params.boardId;

    getArticle(
      this.boardId,
      ({data})=>{
        this.cate = data.board[0].cate;
        this.title= data.board[0].title;
        this.content = data.board[0].content;
        this.userId = data.board[0].userId;
      },
      ()=>{
        alert("잘못된 접근")
      }
      )

  },
 data(){
  return{
    festivalId:"",
    boardId:"",
    userId:"",
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
  ...mapActions(memberStore, ["getUserInfo"]),
  async modiSubmit(){
    let formData = new FormData(); // formData 객체를 생성한다.
    formData.append("userId", this.userId);//작성자 아이디
    formData.append("festivalId", this.festivalId);
    formData.append("boardId",this.boardId);

    formData.append("cate", this.cate);
    formData.append("title", this.title);
    formData.append("content", this.content);
    formData.append("file", this.file);

    await this.getUserInfo();
    await articleUpdate(formData,
    ({data})=>{
      if(data.status==="ok"){
        alert("수정완료")
        this.$router.push(`/boardlist/${this.festivalId}`);
      }else{
        alert("수정실패")
        this.$router.push("/")
      }
    },
    ()=>{
      alert("수정실패")
      this.$router.push("/")
    })
  },
  modiCancle(){
    this.$router.push(`/boardlist/${this.festivalId}`)
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

