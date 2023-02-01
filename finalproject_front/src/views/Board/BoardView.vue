<template>

<b-container class="bv-example-row">
<b-row>
    <b-col>
    <b-card class="comment-wrap">
        <b-row align-v="center" slot="header" >
        <b-col cols="8">
            <h3 class="mb-0">[{{article.cate | cateKor}}]{{article.title}}</h3>
        </b-col>
        <b-col cols="4" class="text-right">
            <h6 class="mb-0">작성자 {{article.userId}}</h6>
        </b-col>
        </b-row>
        <div class="article-wrap">
        <!-- <b-card-img src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1666590171973.png"/> -->
        <b-card-img :src="showImage(article.boardId)"/> 
        <b-card-text>
            {{article.content}}
        </b-card-text>
        </div>
    </b-card>
    </b-col>
    <b-col >
    <card class="comment-wrap" >
        <div class="comment-content">
        <!-- 댓글 들어가는 자리-->
        <board-comment v-for="(ele,i) in comments" :key=i :comment="ele" :userInfo="userInfo" v-on:deleteComment="commentDel"></board-comment>

        </div>
        <hr class="my-4"/>
        <b-row align-v="center"  align-h="center">
        <b-col cols="10" >
            <b-form-input v-model="commentText" placeholder="댓글" @keydown.native="keydown_handler"></b-form-input>
        </b-col>
        <b-col >
            <b-button squared variant="primary" size="sm" @click="commentWrite">등록</b-button>
        </b-col>
        </b-row>
    </card>
    </b-col>
</b-row>
<b-row align-h="center" align-v="center" >

    <b-button variant="primary" @click="golist">
        목록
    </b-button>
    <div class="author" v-if="authorCheck(article.userId)">
        <b-button variant="warning" @click="articleModi">
        수정
        </b-button>
        <b-button variant="danger" @click="articleDel">
        삭제
        </b-button>
    </div>


</b-row>
</b-container>    
</template>

<script>
import {getArticle,getImage,articleDelete} from "@/util/boardApi"
import {mapState, mapGetters, mapActions} from "vuex";
import {writeComment,commentList,commentDelete} from "@/util/commentApi"
import BoardComment from "@/views/Board/BoardComment.vue"


const memberStore = "memberStore";


export default {
  components:{
    BoardComment
  },
  async created(){
    this.festivalId = this.$route.params.festivalId;
    this.boardId = this.$route.params.boardId;
    await getArticle(
      this.boardId,
      ({data})=>{
        this.article = data.board[0]
        //댓글
        let test = data.board.filter(ele=>ele.commentId!=null).map(ele=>{
          return {
            id:ele.commentId,
            content:ele.commentContent,
            userId:ele.commentUserId,
            registDate:ele.commentRegisterTime
            }
        })
        this.comments = test;
      },
      ()=>{
        alert("잘못된 접근입니다.")
        this.$router.push("/")
      }
    )
  },
  data(){
    return {
      festivalId:"",
      boardId:"",
      commentText:"",//입력한 댓글
      article:{//게시글 정보
        boardId:"",
        cate:"",
        content:"",
        festivalId:"",
        registerTime:"",
        title:"",
        userId:""
      },
      comments:[]//댓글들
    }
  },
  computed:{
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods:{
    ...mapActions(memberStore, ["getUserInfo"]),
    authorCheck(id){
      //id와 현재 id를 비교하여 true, false로 작성자 유무를 판별
      if(this.userInfo===null){//로그인 하지 않으면 안나옴
        return false;
      }else{
        return this.userInfo.userId===id;
      }
    },
    showImage(boardId){//이미지 출력
      let tmp = getImage(boardId)
      return tmp
    },
    async commentWrite(){//댓글 작성
      if(this.commentText==="") return;
      
      await this.getUserInfo(); // 토큰 확인 및 재발급진행
      await writeComment({
        boardId:this.boardId,
        content:this.commentText
      },
      ()=>{
        this.commentText = "";
        //댓글 재 호출
        this.getComment();
      },
      (error)=>{
        console.log(error.response.status)
        //토큰 재발급 
        alert("잘못된 접근")
        // this.$router.push("/")
      })
    },
    getComment(){      
      commentList(this.boardId,
      ({data})=>{
        //댓글 재 호출
        this.comments = data.commentlist.map(ele=>{
          return {
            id:ele.commentId,
            content:ele.content,
            userId:ele.userId,
            registDate:ele.registerTime
            }
        });
      },
      ()=>{
        console.log("잘못된 접근")
        this.$router.push("/")
      })

    },
    golist(){
      this.$router.push(`/boardlist/${this.festivalId}`)
    },
    async commentDel(id){
      await this.getUserInfo(); // 토큰 확인 및 재발급진행
      await commentDelete(
        id,
        ()=>{
          this.getComment();//댓글 재호출
        },
        ()=>{
          alert("잘못된 접근")
        })
    },
    async articleDel(){
      await this.getUserInfo(); // 토큰 확인 및 재발급진행
      await articleDelete(
        this.boardId,
        ()=>{
          this.golist();
        },
        ()=>{
          alert("잘못된 접근입니다.")
        }
      )
    },
    articleModi(){
      this.$router.push(`/boardmodify/${this.festivalId}/${this.boardId}`)
    },
    keydown_handler(e){//엔터키 누르면 동작
      if (e.which === 13) {
        this.commentWrite();
     }
    }
  },
  filters:{
    cateKor(cate){
      if(cate==="hugi"){
        return "후기"
      }else if(cate==="deal"){
        return "거래"
      }else{
        return "기타"
      }
    }
  }
}

</script>

<style>
.artcle-wrap{
  display: flex;
  justify-content: center;
  width: 600px;
}

.red{
  background-color: red;
}
.blue{
  background-color: blue;
}

.comment{
  min-height: 50px;
  width:480px;
  background-color:rgb(252, 245, 236);
  margin-bottom: 10px;
}
.comment-wrap{
  height: 500px;
}

.comment-content{
  height: 370px;
  overflow-y: auto;
}
.article-wrap{
  height:400px;
  overflow-y: auto;
}
.author{
  display: flex;
}
</style>

