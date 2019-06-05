import Vue from 'vue'
import Router from 'vue-router'
import BlogpostContainer from '../components/BlogpostContainer'
import FileUpload from '../components/FileUpload'
import RegisterUser from "../components/RegisterUser";
import LandingPage from '../components/LandingPage'
import Blog from "../components/Blog";
import Blogs from '../components/Blogs'
import Login from '../components/Login'
import Navbar from '../components/Navbar'
import axios from 'axios'

Vue.use(Router);

/* eslint-disable */






export default new Router({


  routes: [

    {
      path: '/Navbar',
      name: 'Navbar',
      component: Navbar
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
      path: '/RegisterUser',
      name: 'RegisterUser',
      component: RegisterUser, FileUpload
    },
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage
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
