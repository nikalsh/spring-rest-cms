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
      <!--<div class="carousel-item" v-for="(banner,idx) in banners" :class="{ active: idx==0 }">-->
        <!--<img :src="banner" alt="" class="img-fluid">-->
      <!--</div>-->
      <!--&lt;!&ndash; Text slides with image &ndash;&gt;-->
      <!--<b-carousel-slide-->
        <!--caption="First slide"-->
        <!--text="Nulla vitae elit libero, a pharetra augue mollis interdum."-->
        <!--img-src="https://picsum.photos/1024/480/?image=52"-->
      <!--&gt;</b-carousel-slide>-->

      <!--&lt;!&ndash; Slides with custom text &ndash;&gt;-->
      <!--<b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">-->
        <!--<h1>Hello world!</h1>-->
      <!--</b-carousel-slide>-->

      <!--&lt;!&ndash; Slides with image only &ndash;&gt;-->
      <!--<b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>-->

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <!--<b-carousel-slide>-->
        <!--<img-->
          <!--slot="img"-->
          <!--class="d-block img-fluid w-100"-->
          <!--width="1024"-->
          <!--height="480"-->
          <!--src="https://picsum.photos/1024/480/?image=55"-->
          <!--alt="image slot"-->
        <!--&gt;-->
      <!--</b-carousel-slide>-->

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <!--<b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">-->
        <!--<p>-->
          <!--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt-->
          <!--a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.-->
        <!--</p>-->
      <!--</b-carousel-slide>-->
    </b-carousel>

    <UserPanel/>
  </div>
</template>


<script>
  import UserPanel from './UserPanel';

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
      UserPanel
    }
  }
</script>

<style scoped>

</style>
