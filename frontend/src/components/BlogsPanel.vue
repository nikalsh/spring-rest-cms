<template>

  <div id="blogsPanel">
    BLOGSPANEL
    <a class = "text-center" v-for="blog in blogs">
      <b-button :to="blog">{{ blog }}</b-button>
    </a>


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

        })
      console.log(this.$router)
      this.$forceUpdate()
    }
  }
</script>

<style scoped>

  #blogsPanel {
    background-color: lightcoral;
  }

  #buttons{
  display: flex;
  align-items: center;
  }

  #center{
    text-align: center;
    vertical-align: middle;
  }

</style>
