<template>
  <div id="app">
<Navbar></Navbar>
    <router-view/>
  </div>
</template>

<script>
/* eslint-disable */
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'

Vue.use(BootstrapVue);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Navbar from "./components/Navbar";
  export default {
  name: 'App',
    components: {Navbar}
  }
</script>

<style>
#app {

}
</style>
