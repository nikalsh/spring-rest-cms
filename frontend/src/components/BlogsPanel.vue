<template>
<!--  <div id ="blogsPanel" v-for="blog in blogs">-->

<!--    {{ blog.name }}-->

  <div>

  </div>

<!--  </div>-->
</template>

<script>

  export default {

    name: "BlogsPanel",

    data() {
      return {
        blogs: this.getBlogs(),

      }
    },


    methods: {

      getBlogs() {
        this.$http.get("http://localhost:8080/blog/all")
          .then(response => {

            console.log(response)

          }).catch(error => {

          console.log(error)

        })
      }

    }
  }
</script>

<style scoped>

  .blogsPanel{
    background-color: gray;
  }

</style>
