<template>
  <div>
    <router-link
      tag="li"
      to="/RegisterUser">
      <a>Register Here</a>

    </router-link>
    <router-link
      tag="li"
      to="/Login">
      <a>Login</a>

    </router-link>
  </div>
</template>

<script>

  import Navbar from "./Navbar";

  export default {
    name: 'LandingPage',
    components: {Navbar}
  };
</script>

<style scoped>

</style>
