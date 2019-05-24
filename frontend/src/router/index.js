import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import CKtest from '../components/CKtest'
import CKtest2 from '../components/CKtest2'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import VueCkeditor from 'vue-ckeditor5'

const options = {
  editors: {
    classic: ClassicEditor,
  },
  name: 'ckeditor'
}
import FileUpload from '../components/FileUpload'

Vue.use(Router)
Vue.use(VueCkeditor.plugin, options);

/* eslint-disable */
export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/CKtest',
      name: 'CKtest',
      component: CKtest
    },
    {
      path: '/CKtest2',
      name: 'CKtest2',
      component: CKtest2
    }
    ,
    {
      path: '/',
      name: 'FileUpload',
      component: FileUpload
    }
  ]
})
