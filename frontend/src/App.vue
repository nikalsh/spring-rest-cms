<template>
  <div id="app">
    <Navbar></Navbar>
    <b-container fluid style="padding:0">
      <router-view/>
    </b-container>
  </div>
</template>

<script>

  import Navbar from "./components/Navbar";
  import BlogsPanel from "./pages/BlogsPanel";

  export default {
    name: 'App',
    components: {Navbar, BlogsPanel}
  }
</script>

<style>

</style>
