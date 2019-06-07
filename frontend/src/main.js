// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import router from './router'
import App from './App'
import axios from 'axios'
import store from './store'
import CKEditor from '@ckeditor/ckeditor5-vue';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.use(CKEditor);
import './style/style.css'


import {library} from '@fortawesome/fontawesome-svg-core'
import {fas} from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'

library.add(fas);

Vue.component('font-awesome-icon', FontAwesomeIcon)
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

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app');
