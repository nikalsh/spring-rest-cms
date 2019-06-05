<template>

  <div id="blogsPanel">
    BLOGSPANEL
    <div v-for="blog in blogs">
      <b-button :to="'blogs/'+ blog ">{{ blog }}</b-button>
    </div>


  </div>


</template>

<script>


  export default {

    name: "BlogsPanel",

    data() {
      return {
        blogs: {},


      }
    },


    methods: {},

    async created() {
      await this.$http.get("http://localhost:8080/blog/allnames")
        .then(response => {
          this.blogs = response.data;
          console.log(response.data)

        }).catch(error => {

          console.log(error)

        });

    }
  }
</script>

<style scoped>

  #blogsPanel {
    background-color: lightcoral;
  }

</style>
