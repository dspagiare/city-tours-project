import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import draggable from 'vuedraggable'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import * as VueGoogleMaps from "vue2-google-maps"

<<<<<<< HEAD
Vue.use(VueGoogleMaps, {
  load: {
    key: `${process.env.GOOGLE_API_KEY}`,
    libraries: "places"
  }
});
=======

>>>>>>> 901b2d3af932a9947e9c1e34b008773fc4b1b0cf
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(draggable)


Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
