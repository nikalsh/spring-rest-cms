import Vue from 'vue'
import Router from 'vue-router'
import Register from '../components/Register'
import Blogs from '../components/Blogs'
import Login from '../components/Login'
import axios from 'axios'

import Home from '../components/Home';
import UserPanel from '../components/UserPanel';

Vue.use(Router);

/* eslint-disable */


let dynRoutes = [];


// (async function ()
// {
 axios.get("http://localhost:8080/blog/allnames")
    .then(response => {
      response.data.forEach(blog => {
        console.log(blog);
        dynRoutes.push({
          name: blog,
          path: "/" + blog,
          props: true,
          params: {blogId: blog}
        })
      })
    }).catch(error => {
    console.log(error)
  });
// })


export default new Router({

  mode: 'history',
  hash: false,

  routes: [
   // { path: '*', redirect: '/' }, // catch all use case

    {
      path: '/Profile',
      name: 'Profile',
      component: UserPanel
    },
    {
      path: '/Register',
      name: 'Register',
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
      path: '/:blogName',
      name: '/:blogName',
      component: Blogs,
      props: true
    },

    ]







})
