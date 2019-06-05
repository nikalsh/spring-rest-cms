<template>
  <div id="login">
    <h1>Login Page</h1>
    <b-form @submit.prevent="login">
      <b-form-group
        id="input-group-1"
        label="Username:"
        label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="username"
          required
          placeholder="Enter username"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="password"
          type="password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>


      <b-button type="submit" variant="primary">Login</b-button>
      <b-alert v-model="loginFail" variant="danger" dismissible>
        Inloggning misslyckades
      </b-alert>

    </b-form>

  </div>
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
<<<<<<< HEAD
          .then(() => this.$router.push('/'))
=======
          .then(() => {
            this.$store.dispatch('downloadUser')
              .then(() => this.$router.push('/'))
              .catch(() => this.loginFail = true);
          })
>>>>>>> 7e0b16bf2602b80389b696e2198f262987b189b3
          .catch(() => this.loginFail = true)
      }
    }
  };
</script>

<style scoped>

</style>
