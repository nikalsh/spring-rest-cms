<template>
  <div id="app">
<Navbar></Navbar>
    <b-container fluid style="padding:0"><router-view/></b-container>
  </div>
</template>

<script>
/* eslint-disable */
import Vue from 'vue'

import Navbar from "./components/Navbar";

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
