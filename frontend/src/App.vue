<template>
  <div id="app">
<Navbar></Navbar>
<BlogsPanel></BlogsPanel>
    <b-container fluid style="padding:0"><router-view/></b-container>
  </div>
</template>

<script>
/* eslint-disable */
import Vue from 'vue'

import Navbar from "./components/Navbar";
import BlogsPanel from "./components/BlogsPanel";

  export default {
  name: 'App',
    components: {Navbar, BlogsPanel}
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
