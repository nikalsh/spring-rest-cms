<template>
  <div id="container">
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="120"
      style="text-shadow: 1px 1px 2px #333;"

    >
      <b-carousel-slide
        v-if="isReady"
        v-for="(user, index) in topUsers"
        :caption="user.blog.blogName"
        :text="user.profile"

        v-bind:key="index"
        @click="user.blog.blogName"
      >
        <img slot="img" class="d-block img-fluid w-100"
             :src="'data:;base64,'+user.blog.headerImage"

             v-on:click="goto(user.blog.blogName)">

      </b-carousel-slide>

    </b-carousel>
  </div>
</template>

<script>
  import BlogsPanel from './BlogsPanel'

  export default {
    name: "Home",
    data: function () {
      return {
        topUsers:[],
        isReady:false

      };
    },
    created () {

      this.getFour();

    },
    components: {
      BlogsPanel
    },
    methods: {
      goto(blogname){
        this.$router.push(blogname);
        console.log(blogname);
      },
      getFour() {
        this.isReady=false
         this.$http.get('http://localhost:8080/user/getFour')
          .then(response => {
            this.topUsers=response.data;
            console.log(this.topUsers[0].blog);
            console.log(response);
            this.isReady=true
          }).catch(error => {
          console.log(error);
        });
      }
    },
  }
</script>

<style scoped>

</style>
