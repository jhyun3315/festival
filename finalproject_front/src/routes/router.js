import Vue from 'vue';
import VueRouter from 'vue-router';

import MainLayout from '@/views/Layout/MainLayout.vue';
import AuthLayout from '@/views/User/AuthLayout.vue';
import NotFound from '@/views/ErrorView.vue';
import store from "@/store";

Vue.use(VueRouter);


// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    // await store.dispatch("memberStore/getUserInfo", token);
    await store.dispatch("memberStore/getUserInfo");
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: '/',
    redirect: 'main',
    component: MainLayout,
    children: [
      {
        path: '/main',
        name: 'main', 
        component: () => import(/* webpackChunkName: "demo" */ '../views/MainView.vue')
      },
      {
        path: '/profile',
        name: 'profile',
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "demo" */ '../views/User/UserProfile.vue')
      },
      {
        path: '/myFestival',
        name: 'myfes',
        beforeEnter: onlyAuthUser,
        redirect: 'mycalendar',
        component: () => import('../views/MyFestivalView.vue'),
        children:[
          {
            path: '/mymap',
            name: 'mymap',
            component: () => import ('@/views/Festival/DrawMap')
          },
          {
            path: '/mycalendar',
            name: 'mycal',
            component: () => import ('@fullcalendar/vue')
          }
        ]
      },
      {
        path: '/festival',
        name: 'festival',
        redirect: 'calendar',
        component: () => import(/* webpackChunkName: "demo" */ '../views/FestivalView.vue'),
        children:[
          {
            path: '/map',
            name: 'mapmap',
            component: () => import ('@/views/Festival/DrawMap')
          },
          {
            path: '/calendar',
            name: 'mapcal',
            component: () => import ('@fullcalendar/vue')
          }
        ]
      },
      {
        path: '/board/:festivalId',
        name: 'festivalboard',
        redirect: 'boardlist/:festivalId',
        component: () => import ('../views/BoardView.vue'),
        children:[
          {
            path: '/boardlist/:festivalId',
            name: 'boardList',
            component: () => import ('../views/Board/BoardList.vue')
          },
          {
            path: '/boardmodify/:festivalId/:boardId',
            name: 'boardModify',
            component: () => import ('../views/Board/BoardModify.vue')
          },
          {
            path: '/boardview/:festivalId/:boardId',
            name: 'boardView',
            component: () => import ('../views/Board/BoardView.vue')
          },
          {
            path: '/boardwrite/:festivalId',
            name: 'boardWrite',
            beforeEnter: onlyAuthUser,
            component: () => import ('../views/Board/BoardWrite.vue')
          }
        ]
      }
    ]
  },
  {
    path: '/',
    redirect: 'login',
    component: AuthLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "demo" */ '../views/User/Login.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "demo" */ '../views/User/Register.vue')
      },
      { path: '*', component: NotFound }
    ]
  }
];

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'active',
  scrollBehavior: (to, from ,savedPosition) => {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  }
});

export default router;
