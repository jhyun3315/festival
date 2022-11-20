import MainLayout from '@/views/Layout/MainLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';

import NotFound from '@/views/ErrorView.vue';

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
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/UserProfile.vue')
      },
      {
        path: '/festival',
        name: 'festival',
        component: () => import(/* webpackChunkName: "demo" */ '../views/FestivalView.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import(/* webpackChunkName: "demo" */ '../views/UserView.vue')
      },
      {
        path: '/board',
        name: 'festivalboard',
        component: () => import ('../views/BoardView.vue')
      },
      {
        path: '/article',
        name: 'articleboard',
        component: () => import ('../views/ArticleView.vue')
      },
      {
        path: '/articlewrite',
        name: 'articlewriteboard',
        component: () => import ('../views/ArticlewriteView.vue')
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
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Login.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Register.vue')
      },
      { path: '*', component: NotFound }
    ]
  }
];

export default routes;
