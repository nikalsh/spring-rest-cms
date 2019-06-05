<template>
  <b-navbar
    toggleable="lg"
    type="dark"
    variant="info"
    sticky>
    <b-container>
      <b-navbar-brand href="/">NavBar</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"/>

      <b-collapse
        id="nav-collapse"
        is-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
        <b-navbar-nav>
          <b-nav-item v-show="this.$store.state.currentUser.username" to="blog">my blog</b-nav-item>
        </b-navbar-nav>

          <b-nav-item-dropdown right>
            <template slot="button-content">
              <em   >
                {{this.$store.state.currentUser.username||"User"}}
              </em>

            </template>
            <b-dropdown-item to="Login">Login</b-dropdown-item>
            <b-dropdown-item to="Profile">Profile (placeholder)</b-dropdown-item>
            <b-dropdown-item to="RegisterUser">Register User</b-dropdown-item>
            <b-dropdown-item @click="logout">Log out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-container>
  </b-navbar>
</template>

<script>
export default {
    name: 'Navbar',
    data: function () {
      return {

      };
    },
    methods:{
        logout(){
          this.$store.dispatch('logout')
            .then(() => this.$router.push('/'))
            .catch(()=> this.$router.push('/'))
        }
    }
};
</script>

<style scoped>

</style>
