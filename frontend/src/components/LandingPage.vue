<template>
  <div id="container">
    <UserPanel/>
    <BlogsPanel></BlogsPanel>
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
import BlogsPanel from './BlogsPanel'

export default {
    name: 'LandingPage',
    components: {
        UserPanel,
        BlogsPanel
    }
};
</script>

<style scoped>

</style>
