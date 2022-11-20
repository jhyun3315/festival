<template>
  <div class="wrapper">
    <notifications></notifications>
    <side-bar>
      <template slot="links">
        <sidebar-item
          :link="{
            name: 'Main',
            path: '/main',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
        </sidebar-item>  
        <sidebar-item
              :link="{
                name: '축제 캘린더',
                path: '/festival',
                icon: 'ni ni-pin-3 text-orange'
              }">
        </sidebar-item>

        <sidebar-item
                :link="{
                  name: '축제 지도',
                  path: '/user',
                  icon: 'ni ni-bullet-list-67 text-red'
                }">
        </sidebar-item>
        <sidebar-item
              :link="{
                name: 'User Profile',
                path: '/profile',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>
        <sidebar-item
              :link="{
                name: 'test게시판',
                path: '/board',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>
        <sidebar-item
              :link="{
                name: 'test게시글',
                path: '/article',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>
        <sidebar-item
              :link="{
                name: 'test게시글작성',
                path: '/articlewrite',
                icon: 'ni ni-single-02 text-yellow'
                }">
        </sidebar-item>


        <sidebar-item
                  :link="{
                    name: 'Login',
                    path: '/login',
                    icon: 'ni ni-key-25 text-info'
                  }">
        </sidebar-item>
        <sidebar-item
                  :link="{
                    name: 'Register',
                    path: '/register',
                    icon: 'ni ni-circle-08 text-pink'
                  }">
        </sidebar-item>
      </template> 
    </side-bar>
    <div class="main-content">
      <!-- <main-navbar :type="$route.meta.navbarType"></main-navbar>  -->
      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
  /* eslint-disable no-new */
  import PerfectScrollbar from 'perfect-scrollbar';
  import 'perfect-scrollbar/css/perfect-scrollbar.css';

  function hasElement(className) {
    return document.getElementsByClassName(className).length > 0;
  }

  function initScrollbar(className) {
    if (hasElement(className)) {
      new PerfectScrollbar(`.${className}`);
    } else {
      // try to init it later in case this component is loaded async
      setTimeout(() => {
        initScrollbar(className);
      }, 100);
    }
  }

  import MainNavbar from './MainNavbar.vue';
  import ContentFooter from './ContentFooter.vue';
  import MainContent from './Content.vue';
  import { FadeTransition } from 'vue2-transitions';

  export default {
    components: {
      MainNavbar,
      ContentFooter,
      MainContent,
      FadeTransition
    },
    methods: {
      initScrollbar() {
        let isWindows = navigator.platform.startsWith('Win');
        if (isWindows) {
          initScrollbar('sidenav');
        }
      }
    },
    mounted() {
      this.initScrollbar()
    }
  };
</script>
<style lang="scss">
</style>
