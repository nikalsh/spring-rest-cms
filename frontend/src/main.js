// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import router from './router'
import Vue from 'vue'
import App from './App'
import axios from 'axios'

import store from './store'
import CKEditor from '@ckeditor/ckeditor5-vue';
Vue.use(CKEditor);
Vue.config.productionTip = false;
Vue.prototype.$bus = new Vue();
Vue.prototype.$http = axios;
const access_token = localStorage.getItem('access_token');

if (store.state.access_token) {

  Vue.prototype.$http.defaults.headers.common['Authorization'] = 'bearer ' + access_token;
  store.dispatch('downloadUser')
    .then(() => "")
    .catch(() => "");
}


/* eslint-disable */
/* eslint-disable no-new */

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app');
