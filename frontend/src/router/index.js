import Vue from 'vue'
import Router from 'vue-router'
import Register from '../pages/Register'
import Blogs from '../pages/Blogs'
import Login from '../pages/Login'
import Home from '../pages/Home';
import UserPanel from '../pages/UserPanel';
import BlogsPanel from '../pages/BlogsPanel';

Vue.use(Router);

/* eslint-disable */

export default new Router({

  routes: [

    {
      path: '/Profile',
      name: '/Profile',
      component: UserPanel
    },
    {
      path: '/Register',
      name: '/Register',
      component: Register
    },
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/BlogsPanel',
      name: '/BlogsPanel',
      component: BlogsPanel,
    },
    {
      path: '/:blogName',
      name: '/:blogName',
      component: Blogs,
      props: true
    },
  ]
})
