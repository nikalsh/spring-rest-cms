<template>
  <div id="app">
<Navbar></Navbar>
    <b-container><router-view/></b-container>
  </div>
</template>

<script>
/* eslint-disable */
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Navbar from "./components/Navbar";
Vue.use(BootstrapVue);
  export default {
  name: 'App',
    components: {Navbar}
  }
</script>

<style>
#app {

}

body {
  padding: 0;
  margin: 0;

}

html {
  overflow-y:scroll;
}


</style>
