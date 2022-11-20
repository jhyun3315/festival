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
              <h3 class="mb-0">제목이요 </h3>
            </b-col>
            <b-col cols="4" class="text-right">
              <h6 class="mb-0">글쓴이요</h6>
            </b-col>
          </b-row>
          <div class="article-wrap">
            <b-card-img src="https://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1666590171973.png"/>
            <b-card-text>
              이런일을 했었따 - 좀더 이쁘게 보임 <br/>
              hi
              fdsaf<br/>
              fdsaf<br/>
              sefdsa<br/>
              default<br/>
              dhsafejio<br/>
              러ㅑ덜아ㅣㅁㄴ<br/>
              ㅓ댜<br/>
            </b-card-text>
          </div>
        </b-card>
      </b-col>
      <b-col >
        <card class="comment-wrap" >
          <div class="comment-content">
            <!-- 댓글 들어가는 자리-->
            <b-row class="comment"  align-h="center" align-v="center" >
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row v-show="isAuthor">
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 
              </b-col>
              <b-col >
                <b-row>
                  작성자아이디
                </b-row>
                <b-row>
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="comment">
              <b-col cols="10" >
                댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용댓글내용
              </b-col>
              <b-col >
                <b-row>
                  작성자이름
                </b-row>
                <b-row >
                  <b-button squared variant="danger" size="sm">삭제</b-button>
                </b-row>
              </b-col>
            </b-row>

          </div>
          <hr class="my-4"/>
          <b-row align-v="center"  align-h="center">
            <b-col cols="10" >
              <b-form-input v-model="commenttext" placeholder="댓글"></b-form-input>
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
        <div class="author" v-show="isAuthor">
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
export default {
  data(){
    return {
      isAuthor:false,
    }
  },
  computed:{
    authorCheck(){
      this.isAuthor
    }
  },
 methods:{
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

