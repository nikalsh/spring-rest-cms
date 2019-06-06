<template>
  <div style="min-height: 4em"
       ref="contents"
       class="ck ck-content ck-editor__editable ck-rounded-corners ck-editor__editable_inline ck-blurred"
       v-html="post.postData">
    {{ post.postData }}
  </div>
</template>

<script>
  export default {
    name: "BlogPostView",
    props: {
      post: Object
    },
    data: function () {
      return {
        editorData: '',
      };
    },
    methods: {},
    created: function () {
      this.editorData = this.post.postData;
    }
  }
</script>

<style scoped>

</style>
