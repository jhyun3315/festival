<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-4 bg-gradient-success"> 
      <h1 class="display-2 text-white">마이페이지</h1>
    </base-header> 
    <b-container class="bv-example-row">
      <card>
        <b-card-header>
          {{userData.userId}}
        </b-card-header>
        <b-card-body>

          gfd
        </b-card-body>
      </card>
    </b-container>
  </div>
</template>
<script>
import { mapActions,mapState } from "vuex";
const memberStore = "memberStore";

export default {
  async created(){
    console.log(this.userInfo)
  },
  data(){
    return{
      userData:""
    }
  },
  methods:{
    ...mapActions(memberStore, ["getUserInfo"]),
  },
  computed:{
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  components: {
  }
};
</script>
<style>
</style>
