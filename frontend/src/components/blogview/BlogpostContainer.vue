<template>
  <div>
    <div id="blogpost-container" >
      <b-button :id="'edit-btn'+index" v-if="isOwner&&!isOpen" class=" icon-btn blog-btn" @click.once="swap">
        <font-awesome-icon icon="edit"/>
      </b-button>
      <b-tooltip triggers="hover" v-if="isOwner&&!isOpen" :target="'edit-btn'+index" title="edit post" placement="left"></b-tooltip>
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
      index:'',
      post: Object,
      isOwner: Boolean
    },
    data: function () {
      return {
        isOpen:false,
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
          this.isOpen=true;
          this.currentComponent = 'BlogPostEdit';
        }
      }
    },
  };
</script>
<style>
  #blogpost-container {
    margin: 40px 0;
    position: relative;
  }
</style>
