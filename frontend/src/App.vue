<template>
  <div id="app">
    <Navbar></Navbar>
    <!--<BlogsPanel></BlogsPanel>-->
    <b-container fluid style="padding:0">
      <router-view/>
    </b-container>
    <!--<Footer></Footer>-->
  </div>
</template>

<script>

  import Navbar from "./components/Navbar";
  import Footer from "./components/Footer";
  import BlogsPanel from "./pages/BlogsPanel";

  export default {
    name: 'App',
    components: {Navbar, BlogsPanel, Footer}
  }
</script>

<style>

</style>
