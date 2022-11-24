import Vue from "vue";
import DashboardPlugin from "./plugins/dashboard-plugin";
import App from "./App.vue";
// import VueI18n from 'vue-i18n'
// router setup
import router from "./routes/router";
// plugin setup
import store from "./store";

Vue.use(DashboardPlugin);
// Vue.use(VueI18n);
// window.i18n = new VueI18n({
//   locale: 'en',
// });

/* eslint-disable no-new */
new Vue({
  el: "#app",
  // i18n,
  store,
  render: (h) => h(App),
  router,
});
