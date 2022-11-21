<template>
  <base-nav
    container-classes="container-fluid"
    class="navbar-top navbar-expand"
    :class="{'navbar-dark': type === 'default'}"
  >
    <!-- <a href="#" aria-current="page" class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block active router-link-active"> {{$route.name}} </a> -->
    <!-- Navbar links -->
    <b-navbar-nav class="align-items-center ml-md-auto">
      <!-- This item dont have <b-nav-item> because item have data-action/data-target on tag <a>, wich we cant add -->
      <div v-if="userInfo">
        <base-dropdown menu-on-right
                        class="nav-item"
                        tag="li"
                        title-tag="a"
                        title-classes="nav-link pr-0">
            <a href="#" class="nav-link pr-0" @click.prevent slot="title-container">
            <b-media no-body class="align-items-center">
                    <span class="avatar avatar-sm rounded-circle">
                        <img alt="Image placeholder" src="img/theme/team-4.jpg">
                    </span>
                <b-media-body class="ml-2 d-none d-lg-block">
                <span class="mb-0 text-sm  font-weight-bold">{{userInfo.userName}}님 안녕하세요</span>
                </b-media-body>
            </b-media>
            </a> 
            <template>

            <b-dropdown-header class="noti-title">
                <h6 class="text-overflow m-0">Welcome!</h6>
            </b-dropdown-header>
            <b-dropdown-item @click="profile">
                <i class="ni ni-single-02"></i>
                <span>회원정보</span>
            </b-dropdown-item>
            <b-dropdown-item href="#!">
                <i class="ni ni-calendar-grid-58"></i>
                <span>나의 축제</span>
            </b-dropdown-item>
            <div class="dropdown-divider"></div>
            <b-dropdown-item @click="gologout">
                <i class="ni ni-user-run"></i>
                <span>Logout</span>
            </b-dropdown-item>

            </template>
        </base-dropdown>
      </div>
      <div v-else>
        <b-button size="sm" variant="primary" @click="gologin">
            <i class="ni ni-badge"></i> 로그인</b-button>
        <b-button size="sm" variant="dark"  @click="gojoin">
            <i class="ni ni-single-02"></i> 회원가입</b-button>
      </div>
    </b-navbar-nav>
  </base-nav>
</template>
<script>
import { CollapseTransition } from 'vue2-transitions';
import { BaseNav, Modal } from '@/components';
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal
  },
  props: {
    type: {
      type: String,
      default: 'default', // default|light
      description: 'Look of the dashboard navbar. Default (Green) or light (gray)'
    }
  },
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: ''
    };
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    profile(){
        this.$router.push("/profile")
    },
    gologin(){
        this.$router.push("/login")
    },
    gojoin(){
        this.$router.push("/register")
    }
    ,
    async gologout(){
        console.log(this.userInfo.userId)
        console.log(this.isLogin)
        await this.userLogout(this.userInfo.userId);
        console.log("로그아웃")
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        console.log("끝")
        if (this.$route.path != "/main") this.$router.push({name:"main"});//메인으로 이동
    }
  },
  computed:{
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  }
};
</script>
