import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import Register from '../pages/Register'
import Blogs from '../pages/Blogs'
import Login from '../pages/Login'
import Home from '../pages/Home';
import UserPanel from '../pages/UserPanel';
import BlogsPanel from '../pages/BlogsPanel';

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
