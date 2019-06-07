<template>
  <div id="blogsPanel">
    <b-container>

        <b-row class="justify-content-md-center">
          <b-col  md="auto">
            <b-list-group class="text-center" v-for="blog in blogs" :key="blog">
              <b-list-group-item :to="blog">{{ blog }}

              </b-list-group-item>
            </b-list-group>
          </b-col>
        </b-row>


    </b-container>
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
          // console.log(response.data)
        }).catch(error => {
          console.log(error)
        })
      // console.log(this.$router)
      this.$forceUpdate()
    }
  }
</script>

<style scoped>

</style>
