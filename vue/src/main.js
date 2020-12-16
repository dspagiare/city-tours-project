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
import VueSlideoutPanel from 'vue2-slideout-panel';

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyC044Lz-PYzRTw3JHlYN7IIX4UBRnOHyBw",
    libraries: ["places", "geometry"]
  }
});

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(draggable)
Vue.use(VueSlideoutPanel);

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
