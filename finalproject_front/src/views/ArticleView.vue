<template>
<div>
  <base-header class="pb-6 pb-8 pt-5 pt-md-4 bg-gradient-success"> 
    <h1 class="display-2 text-white">반딧불 축제게시글</h1>
  </base-header> 
  
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
            <template v-for="(ele,i) in comments">
              <b-row class="comment"  align-h="center" align-v="center" :key="i">
                <b-col cols="10" >
                  {{ele.content}}
                </b-col>
                <b-col >
                  <b-row>
                    {{ele.userId}}
                  </b-row>
                  <b-row >
                    <b-button squared variant="danger" size="sm">삭제</b-button>
                  </b-row>
                </b-col>
              </b-row>
            </template>


          </div>
          <hr class="my-4"/>
          <b-row align-v="center"  align-h="center">
            <b-col cols="10" >
              <b-form-input v-model="commentText" placeholder="댓글"></b-form-input>
            </b-col>
            <b-col >
              <b-button squared variant="primary" size="sm">등록</b-button>
            </b-col>
          </b-row>
      </card>
      </b-col>
    </b-row>
    <b-row align-h="center" align-v="center" >

        <b-button variant="primary">
          목록
        </b-button>
        <div class="author" >
          <b-button variant="warning">
            수정
          </b-button>
          <b-button variant="danger">
            삭제
          </b-button>
        </div>


    </b-row>
  </b-container>    
</div>
</template>

<script>
import {getArticle,getImage} from "@/util/boardApi"
import {mapState} from "vuex";

const memberStore = "memberStore";


export default {

  async created(){
    this.festivalId = this.$route.params.festivalId;
    this.boardId = this.$route.params.boardId;
    await getArticle(
      this.boardId,
      ({data})=>{
        // console.log("무야호")
        this.article = data.board[0]

        //댓글
        this.comments = data.board.filter(ele=>ele.commentId!=null).map(ele=>{
          return {
            id:commentId,
            content:ele.commentContent,
            userId:ele.commentUserId,
            registDate:ele.commentRegisterTime
            }
        })
        console.log(this.article)
        // console.log(this.comments)
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
      commentText:"",//입력한 게시글
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
    authorCheck(id){

      //id와 현재 id를 비교하여 true, false로 작성자 유무를 판별
      return this.userInfo.userId===id;
    },
    showImage(boardId){
      let tmp = getImage(boardId)
      return tmp
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

