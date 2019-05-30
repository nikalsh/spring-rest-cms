import Vue from 'vue';
import Router from 'vue-router';
import CKtest from '../components/CKtest';
import FileUpload from '../components/FileUpload';
import CKEditor from '@ckeditor/ckeditor5-vue';
import RegisterUser from '../components/RegisterUser';
import LandingPage from '../components/LandingPage';
// import Autosave from '@ckeditor/ckeditor5-autosave/src/autosave'
// const options = {
//
//   editorConfig:{
//     extraPlugins:[
//     Autosave]
//   },
//   name: 'ckeditor'
// }

Vue.use(CKEditor);
Vue.use(Router);

/* eslint-disable */
export default new Router({
  routes: [

    {
      path: '/CKtest',
      name: 'CKtest',
      component: CKtest
    },
    {
      path: '/RegisterUser',
      name: 'RegisterUser',
      component: RegisterUser, FileUpload
    },
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage
    }
  ]
})
