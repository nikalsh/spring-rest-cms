<template>
  <div id="login">
    <h1>Login</h1>
    <input type="text" name="email" v-model="email" placeholder="Email"/>
    <input type="password" name="password" v-model="password" placeholder="Password"/>
    <button type="button" v-on:click="login()">Login</button>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'Login',
    data() {
      return {
          email: "",
        password: "",
      }
    },
    methods: {


      login() {
        console.log(this.email)
        console.log(this.password)
        if (this.email != "" && this.password != "") {

          axios.post('http://localhost:8080/blogadmin/me', {
              headers: {
                Authorization: 'Basic ' + btoa(this.email + ':' + this.password)
              }
            }
          ).then((response => {
            console.log(response)
          })).catch((error => {
            console.log(error)
          }))

          if ('auth') {

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
