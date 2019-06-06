<template>
  <div id="blogpost-container" class="ck">
    <div @click.once="swap">
      <div  ref="contents" :is="currentComponent" :post="post"></div>
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
<style scoped>
  #blogpost-container {
    margin: 20px 0;
    position: relative;
  }
</style>
