<template>
  <div id="container">
    <br>
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
import UserPanel from './UserPanel';

export default {
    name: 'LandingPage',
    components: {
    }
};
</script>

<style scoped>

</style>
