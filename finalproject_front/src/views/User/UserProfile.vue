<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-4 bg-gradient-success"> 
      <h1 class="display-2 text-white">마이페이지</h1>
    </base-header> 
    <b-container class="bv-example-row">
      <card>
        <b-card-header>
          {{userData.userId}}님의 정보
        </b-card-header>
        <b-card-body>
          <base-input alternative
                      class="mb-3"
                      prepend-icon="ni ni-lock-circle-open"
                      placeholder="password"
                      type="password"
                      name="Password"
                      :rules="{required: true}"
                      v-model="userData.userPw">
          </base-input>
          <base-input alternative
                      class="mb-3"
                      prepend-icon="ni ni-hat-3"
                      placeholder="Name"
                      name="Name"
                      :rules="{required: true}"
                      v-model="userData.userName">
          </base-input>

          <base-input alternative
                      class="mb-3"
                      prepend-icon="ni ni-email-83"
                      placeholder="Email"
                      name="Email"
                      :rules="{required: true, email: true}"
                      v-model="userData.email">
          </base-input>

          <base-input alternative
                      class="mb-3"
                      prepend-icon="ni ni-bell-55"
                      placeholder="PhoneNumber"
                      name="PhoneNumber"
                      :rules="{required: true}"
                      v-model="userData.phoneNumber">
          </base-input>
        </b-card-body>
        <b-button variant="primary" @click="goUpdate">수정하기</b-button>
      </card>
    </b-container>
  </div>
</template>
<script>

import { mapActions,mapState } from "vuex";
const memberStore = "memberStore";
export default {
  created(){
    this.userData.userId = this.userInfo.userId;
    this.userData.userPw = this.userInfo.userPw;
    this.userData.userName = this.userInfo.userName;
    this.userData.email = this.userInfo.email;
    this.userData.phoneNumber = this.userInfo.phoneNumber;
    // this.userData = this.userInfo;
  },
  data(){
    return{
      userData:{
        userId:"",
        userPw:"",
        userName:"",
        email:"",
        phoneNumber:""
      }
    }
  },
  methods:{
    ...mapActions(memberStore, ["getUserInfo","userUpdate"]),
    goUpdate(){
      this.userUpdate({...this.userData});
      alert("정보수정 완료")
    }
  },
  computed:{
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
};
</script>
<style>
</style>
