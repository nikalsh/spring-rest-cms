<template>
  <b-container>
    <b-form @submit.prevent="register">
      <b-container fluid style="padding: 0">
        <b-form-group
          id="input-group-1"
          label="Your Username:"
          label-for="input-1"
          label-cols-sm="3"
          label-cols-lg="2">
          <b-form-input
            id="input-1"
            v-model="username"
            type="text"
            required
            placeholder="Enter username"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-2"
                      label="Your Email:"
                      label-for="input-2"
                      label-cols-sm="3"
                      label-cols-lg="2">
          <b-form-input
            id="input-2"
            v-model="email"
            type="email"
            required
            placeholder="Enter Email"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-3"
                      label="Your Password:"
                      label-for="input-3"
                      label-cols-sm="3"
                      label-cols-lg="2">
          <b-form-input
            id="input-3"
            v-model="password"
            type="password"
            required
            placeholder="Enter password"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-4"
                      label="Your Face:"
                      label-for="input-3"
                      label-cols-sm="3"
                      label-cols-lg="2">
          <b-form-file
            id="input-4"
            v-model="file"
            accept="image/*"
            placeholder="Choose a file..."
            drop-placeholder="Drop file here..."
            required
          ></b-form-file>
        </b-form-group>
        <b-form-group id="input-group-5"
                      label="Your Profile:"
                      label-for="input-5"
                      label-cols-sm="3"
                      label-cols-lg="2">

          <ckeditor id="input-5" :editor="editor" v-model="profile" :config="editorConfig"></ckeditor>
        </b-form-group>

        <b-button style="margin: auto; display:block"
                  type="submit"
                  variant="primary">Register</b-button>

        <b-alert v-model="registerFail" variant="danger" dismissible>
          Registrering misslyckades
        </b-alert>
      </b-container>
    </b-form>
  </b-container>
</template>
<script>
  import InlineEditor from '@ckeditor/ckeditor5-build-inline';

  export default {
    name: 'RegisterUser',
    data() {
      return {
        editor: InlineEditor,
        profile: '<p>Your profile</p>',
        editorConfig: {

          toolbar: ['heading', 'bold', 'italic']
        },
        username: '',
        email: '',
        password: '',
        file: '',
        registerFail: false
      };
    },

    methods: {
      register: function () {
        console.log(this.file);
        const username = this.username;
        const email = this.email;
        const password = this.password;
        const profile=this.profile;
        const file = this.file;
        this.$store.dispatch('register', {username, email, password,profile, file})
          .then(() => {
            this.$store.dispatch('login', {username, password})
              .then(() => {
                this.$store.dispatch('downloadUser')
                  .then(() => this.$router.push('/'))
                  .catch(() => this.registerFail = true)
              })
              .catch(() => this.registerFail = true);
          })
          .catch(() => this.registerFail = true)
      }
    }
  };
</script>

<style scoped>

  #input-5 {
    text-align: center;
    border: lightgrey 1px solid;
    min-height: 8em
  }
</style>
