import Vue from 'vue'
import Router from 'vue-router'
import CKtest from '../components/CKtest'
import FileUpload from '../components/FileUpload'
import CKEditor from '@ckeditor/ckeditor5-vue';
import RegisterUser from "../components/RegisterUser";

Vue.use(CKEditor);
Vue.use(Router)

/* eslint-disable */
export default new Router({
  routes: [

    {
      path: '/CKtest',
      name: 'CKtest',
      component: CKtest
    },
    {
      path: '/',
      name: 'RegisterUser',
      component: RegisterUser, FileUpload
    }
  ]
})
