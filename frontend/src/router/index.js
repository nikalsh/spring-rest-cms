import Vue from 'vue'
import Router from 'vue-router';
import BlogpostContainer from '../components/BlogpostContainer';
import Register from "../components/Register";
import Blog from "../components/Blog";
import Login from '../components/Login';
import Navbar from '../components/Navbar';
import Home from '../components/Home';
import UserPanel from '../components/UserPanel';

Vue.use(Router);

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
    }
  ]
})
