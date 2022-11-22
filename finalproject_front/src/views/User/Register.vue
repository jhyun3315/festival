<template >
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9" id="vantaRef">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">회원가입</h1>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
 
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8" >
          <b-card no-body class="bg-secondary border-0">
            <!-- <b-card-header class="bg-transparent pb-5"> -->
              <!-- <div class="text-muted text-center mt-2 mb-4"><small>Sign up with</small></div>
              <div class="text-center">
                <a href="#" class="btn btn-neutral btn-icon mr-4">
                  <span class="btn-inner--icon"><img src="img/icons/common/github.svg"></span>
                  <span class="btn-inner--text">Github</span>
                </a>
                <a href="#" class="btn btn-neutral btn-icon">
                  <span class="btn-inner--icon"><img src="img/icons/common/google.svg"></span>
                  <span class="btn-inner--text">Google</span>
                </a>
              </div> -->
            <!-- </b-card-header> -->
            <b-card-body class="px-lg-5 py-lg-5">
              <!-- <div class="text-center text-muted mb-4">
                <small>Or sign up with credentials</small>
              </div> -->
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(submitJoin)">
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-badge"
                              placeholder="ID"
                              name="id"
                              :rules="{required: true}"
                              v-model="user.userId">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="password"
                              type="password"
                              name="Password"
                              :rules="{required: true}"
                              v-model="user.userPw">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="Name"
                              name="Name"
                              :rules="{required: true}"
                              v-model="user.userName">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-email-83"
                              placeholder="Email"
                              name="Email"
                              :rules="{required: true, email: true}"
                              v-model="user.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-bell-55"
                              placeholder="PhoneNumber"
                              name="PhoneNumber"
                              :rules="{required: true}"
                              v-model="user.phoneNumber">
                  </base-input>

                  <!-- <div class="text-muted font-italic"><small>아이디 확인: 
                    <span class="text-success font-weight-700">사용가능</span></small>
                  </div> -->
                  <!-- <b-row class=" my-4">
                    <b-col cols="12">
                      <base-input :rules="{ required: { allowFalse: false } }" name=Privacy Policy>
                        <b-form-checkbox v-model="model.agree">
                          <span class="text-muted">I agree with the <a href="#!">Privacy Policy</a></span>
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row> -->
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4">Create account</b-button>
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>


<script>
import { join } from "@/util/userApi.js";
import * as THREE from '@/assets/three.r134.min.js';
  export default {
    name: 'register',
    data() {
      return {
        user: {
          userId:'',
          userPw: '',
          userName: '',
          email: '',
          phoneNumber: ''
        }
      }
    },
    methods: {
      submitJoin() {
        join(this.user,({data})=>{
          // console.log("가입 완료")
          // console.log(data)
          alert("가입완료")
          this.$router.push("/")
        },(e)=>{
          console.log("잘못됨")
          alert("가입실패")
          this.$router.push("/")
        })
      }
  },
  async mounted() {
    if (process.browser) {  
      const { default: BIRDS } = await import("vanta/dist/vanta.birds.min");
      BIRDS({
        el: "#vantaRef",
        mouseControls: true,
      touchControls: true,
      gyroControls: false,
      minHeight: 200.00,
      minWidth: 200.00,
      scale: 1.00,
      scaleMobile: 1.00,
      backgroundColor: 0x5bb19b,
      color1: 0xef00ff,
        THREE: window.THREE
      });
    }
  },

  };
</script>
<style></style>
