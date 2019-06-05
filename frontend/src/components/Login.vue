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
  import {myLoginRoutine} from "../scripts/MyLoginRoutine";

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
          .then(() => this.$router.push('/'))
          .catch(()=> this.loginFail=true)
      }
      // login() {
      //   const {username, password} = this;
      //   myLoginRoutine({username, password}).then(() => {
      //     this.$router.push('/')
      //   });
      // eslint-disable-next-line no-console
      //     console.log(this.email);
      //     // eslint-disable-next-line no-console
      //     console.log(this.password);
      //     if (this.email !== '' && this.password !== '') {
      //
      //         axios.post('http://localhost:8080/blogadmin/me', {
      //             withCredentials: true,
      //         },{
      //             auth: {
      //                 username: this.email,
      //                 password: this.password
      //             }
      //         }
      //         ).then((response => {
      //             // eslint-disable-next-line no-console
      //           console.log(response.data);
      //             this.$globalUserDetails.name = response.data.name;
      //             this.$globalUserDetails.email = response.data.email;
      //             this.$globalUserDetails.img = (response.data.photo === null ? this.$globalUserDetails.img : response.data.photo);
      //             this.$router.push({name: 'LandingPage'});
      //
      //         })).catch((error => {
      //             // eslint-disable-next-line no-console
      //             console.log(error);
      //
      //         }));
      //
      //     }   else {
      //         // eslint-disable-next-line no-console
      //         console.log('Email eller lösenord kan inte vara tomt');
      //     }
      // },

    }
  };
</script>

<style scoped>

</style>
