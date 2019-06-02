<template>
  <div id="login">
    <h1>Login</h1>
    <input
      v-model="email"
      type="text"
      name="email"
      placeholder="Email">
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
import axios from 'axios'


export default {
    name: 'Login',
    data() {
        return {
            email: '',
            password: '',
        }
    },
    methods: {
        login() {
        // eslint-disable-next-line no-console
            console.log(this.email)
            // eslint-disable-next-line no-console
            console.log(this.password)
            if (this.email !== '' && this.password !== '') {

                axios.post('http://localhost:8080/blogadmin/me', {
                    withCredentials: true,
                },{
                    auth: {
                        username: this.email,
                        password: this.password
                    }
                }
                ).then((response => {
                    // eslint-disable-next-line no-console
                  console.log(response.data)
                    this.$globalUserDetails.name = response.data.name
                    this.$globalUserDetails.email = response.data.email
                    this.$globalUserDetails.img = (response.data.photo == null ? this.$globalUserDetails.img : response.data.photo)
                    this.$router.push({name: 'LandingPage'})

                })).catch((error => {
                    // eslint-disable-next-line no-console
                    console.log(error)

                }))

            }   else {
                // eslint-disable-next-line no-console
                console.log('Email eller lösenord kan inte vara tomt')
            }
        },

    }
}
</script>

<style scoped>

</style>
