<template>
  <div id="login">
    <h1>Login</h1>
    <input
      v-model="username"
      type="text"
      name="Username"
      placeholder="Username">
    <input
      v-model="password"
      type="password"
      name="password"
      placeholder="Password">
    <button
      type="button"
      @click="login()">Login
    </button>
  </div>
</template>

<script>
import axios from 'axios';
import {myLoginRoutine} from "../scripts/MyLoginRoutine";

export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: '',
        };
    },
    methods: {
        login() {
          const { username, password } = this;
          myLoginRoutine({ username, password }).then(() => {
            this.$router.push('/')
          });
        },

    }
};
</script>

<style scoped>

</style>
