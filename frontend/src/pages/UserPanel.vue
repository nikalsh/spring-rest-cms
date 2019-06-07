<template>
  <div>
    <b-container style="width:auto">
      <div class="asd" style="height:1000px">
        <p>{{ this.$store.getters.getUser.email }}</p>
        <p>{{ this.$store.getters.getUser.username }}</p>
        <b-img fluid :src="this.$store.getters.getUserImage" alt="profile picture"/>
        <div v-html="this.$store.getters.getUser.profile"></div>
      </div>
    </b-container>
  </div>
</template>

<script>

  export default {
    name: 'UserPanel',
    data() {
      return {}
    },
    methods: {},
  }
</script>

<style scoped>
  .asd {
    background-color: lightgray;
  }
</style>
