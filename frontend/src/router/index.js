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
Vue.use(Router)
Vue.use(VueCkeditor.plugin, options);

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
  ]
})
