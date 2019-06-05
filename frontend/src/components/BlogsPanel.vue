<template>

  <div id="blogsPanel">
    BLOGSPANEL
    <p v-for="blog in blogs">
      {{ blog.blogName }}
    </p>
<!--    <div v-for="blog in blogs">-->
<!--            <router-link :to="{name: blog.blogName}"></router-link>-->
<!--    </div>-->



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
