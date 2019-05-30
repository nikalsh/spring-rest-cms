<template>
  <div id="login">
    <h1>Login</h1>
    <input type="text" name="username" v-model="input.username" placeholder="Username"/>
    <input type="password" name="password" v-model="input.password" placeholder="Password"/>
    <button type="button" v-on:click="login()">Login</button>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'Login',
    data() {
      return {
        input: {
          email: "",
          password: ""
        }
      }
    },
    methods: {
      login() {
        if (this.input.email != "" && this.input.password != "") {


          if (true) {

          } else {
            console.log("Fel email eller lösenord!");
          }
        } else {
          console.log("Email eller lösenord kan inte vara tomt");
        }
      }
    }
  }
</script>

<style scoped>

</style>
