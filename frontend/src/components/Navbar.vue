<template>
  <b-navbar
    toggleable="sm"
    type="light"
    variant="light"

    sticky>
    <b-container>
      <b-navbar-brand to="/">Bloggportalen</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"/>

      <b-collapse
        id="nav-collapse"
        is-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
        <b-navbar-nav>
          <b-nav-item v-show="this.$store.getters.isLoggedIn" :to="this.$store.getters.getUser.username">My Blog</b-nav-item>
        </b-navbar-nav>

          <b-nav-item-dropdown right>
            <template slot="button-content">
              <em   >
                {{this.$store.getters.getUser.username||"New User"}}
              </em>

            </template>
            <b-dropdown-item to="/Login" v-show="!this.$store.getters.isLoggedIn">Login</b-dropdown-item>
            <b-dropdown-item to="/Profile" v-show="this.$store.getters.isLoggedIn">Profile </b-dropdown-item>
            <b-dropdown-item to="/Register" v-show="!this.$store.getters.isLoggedIn">Register User</b-dropdown-item>
            <b-dropdown-item @click="logout" v-show="this.$store.getters.isLoggedIn">Log out</b-dropdown-item>
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
