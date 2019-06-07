<template>
  <b-container>
    <h1>Login Page</h1>
    <b-form @submit.prevent="login">

      <b-form-group label="Username:"
                    label-for="login-username"
                    label-cols-sm="3"
                    label-cols-lg="2">
        <b-form-input
          id="login-username"
          v-model="username"
          required
          placeholder="Enter username"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Password:"
                    label-for="login-password"
                    label-cols-sm="3"
                    label-cols-lg="2">
        <b-form-input
          id="login-password"
          v-model="password"
          type="password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>


      <b-button id="center-button" type="submit" variant="primary">Login</b-button>
      <b-alert v-model="loginFail" variant="danger" dismissible>
        Inloggning misslyckades
      </b-alert>

    </b-form>
  </b-container>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        username: '',
        password: '',
        loginFail: false
      };
    },
    methods: {

      login: function () {
        const username = this.username;
        const password = this.password;
        this.$store.dispatch('login', {username, password})

          .then(() => {
            this.$store.dispatch('downloadUser')
              .then(() => this.$router.push('/'))
              .catch(() => this.loginFail = true);
          })
          .catch(() => this.loginFail = true)
      }
    }
  };
</script>

<style scoped>

</style>
