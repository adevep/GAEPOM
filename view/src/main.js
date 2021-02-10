import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueSession from 'vue-session';
import App from './App.vue';
import store from './store';
import { router } from './router';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
} from '@fortawesome/free-solid-svg-icons';

import vuetify from './plugins/vuetify';


library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false;

// Vue.prototype.$http = axios;
// Vue.prototype.$axios = axios;

Vue.use(VueAxios, axios);
Vue.use(VueSession);
Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App)
}).$mount('#app');
