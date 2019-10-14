<template>
  <div id="container">
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      no-hover-pause
      background="#ababab"
      style="text-shadow: 2px 2px 3px #333;"

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
    <a class="center"
      href="https://test-www.payson.se/pay?De=K%c3%b6p+av+luft&Se=niklas%40helander.se&Cost=0&Currency=SEK&Sp=1&Lang=SE"
      title="Payson internetbetalningar"
      style="width: 170px; height: 70px; background: url('https://www.payson.se/sites/all/files/images/external/PayBtn_BgImg.png') no-repeat scroll 0% 0% transparent; display: block; text-indent: 75px; padding-top: 15px; text-decoration: none; font-size: 16px; font-family: Arial; font-weight: bold; color: #00245D;">Köp
      luft</a>
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
         this.$http.get('api/user/getFour')
          .then(response => {
            this.topUsers=response.data;
            // console.log(this.topUsers[0].blog);
            // console.log(response);
            this.isReady=true
          }).catch(error => {
          console.log(error);
        });
      }
    },
  }
</script>

<style scoped>

  .center {
    display: inline-block;
    text-align: center;
    margin-left: 46%;
    /*margin-right: 30%;*/
    overflow: visible;

  }

</style>
