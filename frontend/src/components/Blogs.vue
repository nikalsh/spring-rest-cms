<template>
  <div id="blogs">
    <h1> {{ blogName }} </h1>

    <button @click="onClick">New post</button>
    <div ref="container">
      <BlogpostContainer
        v-for="(post, index) in posts"
        :post="post"
        :key="index"/>
    </div>
  </div>
</template>

<script>
  import BlogpostContainer from './BlogpostContainer';
  import Vue from 'vue';

  export default {
    name: 'Blogs',
    components: {
      BlogpostContainer: BlogpostContainer
    },
    props: ['blogName'],

    data: function () {
      return {
        posts: this.getPosts(this.blogName)
      }

    },
    methods: {

      onClick() {
        let ComponentClass = Vue.extend(BlogpostContainer);
        let instance = new ComponentClass({
          propsData: {post: {}}
        });
        instance.$mount();
        this.$refs.container.insertBefore(instance.$el, this.$refs.container.firstChild);
      },
      getPosts(blogName) {

        this.now = Date.now();
        this.$http.get('http://localhost:8080/post/postsByBlogName/' + blogName, {}
        ).then((response => {
          this.posts = response.data;
          console.log(response);
        })).catch((error => {
          console.log(error);
        }));
      }
    },



  }
</script>

<style scoped>

</style>
