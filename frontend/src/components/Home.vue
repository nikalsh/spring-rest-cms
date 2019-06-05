<template>
  <div id="container">
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"

    >
      <b-carousel-slide
        v-for="banner in banners"
        caption="First slide"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        :img-src="banner"
        v-bind:key="banner"
      ></b-carousel-slide>

    </b-carousel>
    <BlogsPanel></BlogsPanel>
  </div>
</template>


<script>
  import UserPanel from './UserPanel';
  import BlogsPanel from './BlogsPanel'
  export default {
    name: "Home",
    data: function () {
      return {
        banners: [
          "https://picsum.photos/1024/480/?image=55",
          "https://picsum.photos/1024/480/?image=52",
          "https://picsum.photos/1024/480/?image=58"
        ]
      };
    },
    components: {
      BlogsPanel
    }
  }
</script>

<style scoped>

</style>
