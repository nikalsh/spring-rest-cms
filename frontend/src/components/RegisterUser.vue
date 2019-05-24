<template>
<div>
  <FileUpload></FileUpload>
  <p>
      <label for="name">Name</label>
      <input
        id="name"
        v-model="name"
        type="text"
        name="name"
      >
      </p>

  <p>
      <label for="email">Email</label>
      <input
        id="email"
        v-model="email"
        type="text"
        name="email"
        min="0"
      >
  </p>
  <p>
      <label for="password">Password</label>
      <input
        id="password"
        v-model="password"
        type="text"
        name="password"
        min="0"
      >
  </p>
  <p>
      <input
        type="submit"
        value="Submit"
      >
  </p>
</div>
</template>

<script>
import FileUpload from './FileUpload'
export default {
    components: {
        FileUpload
    },
    data () {
        return {
            password: '',
            name: '',
            email: ''
        }
    }
}
</script>
