<template>
  <div class="asd">
    <p>{{ this.$store.getters.getUser.email }}</p>
    <p>{{ this.$store.getters.getUser.username }}</p>
    <img :src="img" alt="profile picture">
    <br>
    USERPANEL

  </div>
</template>

<script>

  export default {
    name: 'UserPanel',

    data() {
      return {

      }

    },
    methods: {

    },


  }


</script>

<style scoped>
  .asd {
    background-color: lightgray;
  }
</style>
