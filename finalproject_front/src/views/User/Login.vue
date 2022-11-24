<template>
  <!-- Header -->
  <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9" id="vantaRef">
    <b-container class="container">
      <div class="header-body text-center mb-8">
        <b-row class="justify-content-center"> 
            <h1 class="text-white">로그인</h1>
          </b-col>
        </b-row>
      </div>
    </b-container>
    <div class="separator separator-bottom separator-skew zindex-100"></div>

    <!-- Page content -->
    <b-row class="justify-content-center">
      <b-col md="3.5">
        <b-card no-body style="width: 500px">
          <b-card-body>
            <div class="text-center text-muted mb-4">
              <!-- <small>Or sign in with credentials</small> -->
            </div>
            <validation-observer v-slot="{ handleSubmit }" ref="formValidator">
              <b-form role="form" @submit.prevent="handleSubmit(login)">
                <base-input
                  alternative
                  class="mb-3"
                  name="id"
                  :rules="{ required: true }"
                  prepend-icon="ni ni-badge"
                  placeholder="ID"
                  v-model="user.userId"
                >
                </base-input>

                <base-input
                  alternative
                  class="mb-3"
                  name="Password"
                  :rules="{ required: true }"
                  prepend-icon="ni ni-lock-circle-open"
                  type="password"
                  placeholder="Password"
                  v-model="user.userPw"
                >
                </base-input>

                <!-- <b-form-checkbox v-model="model.rememberMe">Remember me</b-form-checkbox> -->
                <div class="text-center">
                  <base-button type="primary" native-type="submit" class="my-4"
                    >Sign in</base-button
                  >
                </div>
              </b-form>
            </validation-observer>
          </b-card-body>
        </b-card>
        <b-row class="mt-3">
          <b-col cols="6">
            <!-- <router-link to="/dashboard" class="text-light"><small>Forgot password?</small></router-link> -->
          </b-col>
          <b-col cols="6" class="text-right">
            <router-link to="/register" class="text-light"
              ><small>Create new account</small></router-link
            >
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";

import * as THREE from "@/assets/three.r134.min.js";
export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  data() {
    return {
      user: {
        userId: "",
        userPw: "",
        // rememberMe: false
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async login() {
      //로그인 진행
      // console.log(this.tttmp)
      await this.userConfirm(this.user);
      // let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(); //유저정보 가져오기
        this.$router.push({ name: "main" });
      } else {
        console.log("로그인 안됨");
      }
    },
  },
  async mounted() {
    if (process.browser) {
      const { default: BIRDS } = await import("vanta/dist/vanta.birds.min");
      BIRDS({
        el: "#vantaRef",
        mouseControls: true,
        touchControls: true,
        gyroControls: false,
        minHeight: 200.0,
        minWidth: 200.0,
        scale: 1.0,
        scaleMobile: 1.0,
        backgroundColor: 0x63d7b1,
        color1: 0xff00e0,
        THREE: window.THREE,
      });
    }
  },

  beforeDestroy() {
    if (this.vantaEffect) {
      this.vantaEffect.destroy();
    }
  },
};
</script>
