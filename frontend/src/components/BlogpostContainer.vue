<template>
  <div style="border: lightgrey 1px solid;  margin: 20px 0; position:relative;">
    <div @click.once="swap">
      <div ref="contents" :is="currentComponent" :post="post"></div>
    </div>
  </div>
</template>

<script>
  import BlogPostEdit from './BlogPostEdit';
  import BlogPostView from './BlogPostView';

  export default {
    name: 'BlogpostContainer',
    props: {
      post: Object,
      isOwner: Boolean
    },
    data: function () {
      return {
        currentComponent: 'BlogPostView',
        MyEditor: 'BlogPostEdit',

      };
    },
    components: {
      BlogPostEdit,
      BlogPostView
    },
    methods: {
      swap() {
        if (this.isOwner) {
          console.log(this.isOwner);
          this.currentComponent = 'BlogPostEdit';
        }
      }
    },
  };
</script>
<style>


  :root {
    --ck-image-style-spacing: 1.5em;
  }


  .image-style-side {
    max-width: 50%;
  }


  .image-style-side {
    float: right;
    margin-left: var(--ck-image-style-spacing);
  }

  .image-style-side > img {
    max-width: 100%;
    height: auto;
    margin: 0 auto;
  }


  .image-style-align-left {
    float: left;
    margin-right: var(--ck-image-style-spacing);
  }


  .image-style-align-center {
    margin-left: auto;
    margin-right: auto;
  }


  .image-style-align-right {
    float: right;
    margin-left: var(--ck-image-style-spacing);
  }


  #blogpost-container {
    position: relative;
  }

  .ck {
    text-align: center;
  }

</style>
