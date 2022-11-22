<template>
<b-row class="comment"  align-h="center" align-v="center">
    <b-col cols="10" >
        {{comment.content}}
    </b-col>
    <b-col >
        <b-row>
        {{comment.userId}}
        </b-row>
        <b-row v-if="authorCheck(comment.userId)">
        <b-button squared variant="danger" size="sm" @click="commentDel(comment.id)">삭제</b-button>
        </b-row>
    </b-col>
</b-row>
</template>

<script>
export default {
    props:{
        comment:Object,
        userInfo:Object
    },
    methods: {
        authorCheck(id){
        //id와 현재 id를 비교하여 true, false로 작성자 유무를 판별
        if(this.userInfo===null){//로그인 하지 않으면 안나옴
            return false;
        }else{
            return this.userInfo.userId===id;
        }
        }, 
        commentDel(id){
            console.log(id)
            this.$emit("deleteComment",id)
        }
    },
}
</script>
