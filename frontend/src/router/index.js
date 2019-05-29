import Vue from 'vue'
import Router from 'vue-router'
import CKtest from '../components/blogpost-container'
import FileUpload from '../components/FileUpload'
import CKEditor from '@ckeditor/ckeditor5-vue';
import RegisterUser from "../components/RegisterUser";
import Blog from "../components/Blog";
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
    },{
      path: '/Blog',
      name: 'Blog',
      component: Blog
    },
    {
      path: '/',
      name: 'RegisterUser',
      component: RegisterUser, FileUpload
    }
  ]
})
