import Vue from 'vue'
import Router from 'vue-router'
import BlogpostContainer from '../components/BlogpostContainer'

import LandingPage from '../components/LandingPage'
import Register from '../components/Register'
import Blog from "../components/Blog";
import Blogs from '../components/Blogs'
import Login from '../components/Login'
import Navbar from '../components/Navbar'
import axios from 'axios'

import Home from '../components/Home';
import UserPanel from '../components/UserPanel';

Vue.use(Router);

/* eslint-disable */


let dynRoutes = []


// (async function ()
// {
 axios.get("http://localhost:8080/blog/all")
    .then(response => {
      response.data.forEach(blog => {
        dynRoutes.push({
          name: blog.blogName,
          path: "/" + blog.blogName,
          component: BlogTest,
          props: true,
          params: {blogId: blog.id}
        })
      })
    }).catch(error => {
    console.log(error)
  })
// })


export default new Router({


  routes: [

    {
      path: '/Navbar',
      name: 'Navbar',
      component: Navbar
    },{
      path: '/Profile',
      name: 'Prtofile',
      component: UserPanel
    },
    {
      path: '/BlogpostContainer',
      name: 'BlogpostContainer',
      component: BlogpostContainer
    },
    {
      path: '/Blog',
      name: 'Blog',
      component: Blog
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
      path: '/blogs/:blogName',
      component: Blogs,
      props: true
    }
    ]







})
