<template>
  <b-navbar
    toggleable="sm"
    type="light"
    variant="light"
    style="background-color: lightcoral !important;"
    sticky>
    <b-container>
      <b-navbar-brand to="/">Bloggportalen</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"/>

      <b-collapse
        id="nav-collapse"
        is-nav>
        <b-navbar-nav>
          <b-nav-item to="/BlogsPanel">Blogs</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-navbar-nav>
            <b-nav-item v-if="this.$store.getters.isLoggedIn" :to="this.$store.getters.getBlogName">Welcome
              {{this.$store.getters.getUser.username}}
            </b-nav-item>

          </b-navbar-nav>

          <b-nav-item-dropdown right>
            <template slot="button-content">
              <em>
                user
              </em>

            </template>
            <b-dropdown-item to="/Login" v-show="!this.$store.getters.isLoggedIn">Login</b-dropdown-item>
            <b-dropdown-item to="/Profile" v-show="this.$store.getters.isLoggedIn">Profile</b-dropdown-item>
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
      return {};
    },
    methods: {
      logout() {
        console.log(this.$store.getters.getUser);
        this.$store.dispatch('logout')
          .then(() => this.$router.push('/'))
          .catch(() => this.$router.push('/'))
      }
    }
  };
</script>

<style scoped>

</style>
