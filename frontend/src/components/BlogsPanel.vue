<template>

  <div id="blogsPanel">
    BLOGSPANEL
    <div v-for="blog in blogs">
      <b-button :to="'blogs/'+ blog.blogName ">{{ blog.blogName }}</b-button>
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
      await this.$http.get("http://localhost:8080/blog/all")
        .then(response => {
          this.blogs = response.data
          console.log(response.data)

        }).catch(error => {

          console.log(error)

        })
      console.log(this.$router)
    }
  }
</script>

<style scoped>

  #blogsPanel {
    background-color: lightcoral;
  }

</style>
