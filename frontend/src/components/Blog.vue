<template>
  <div id="blog">
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
    name: 'Blog',
    components: {
        BlogpostContainer: BlogpostContainer
    },

    data: function () {
        return {
            blogName: "",
            posts: this.getPosts()
        }

    },
    methods: {

        onClick() {
            let ComponentClass = Vue.extend(BlogpostContainer);
            let instance = new ComponentClass({
                propsData: {post:{}}
            });
            instance.$mount();
            this.$refs.container.insertBefore(instance.$el, this.$refs.container.firstChild);
        },
        getPosts() {
            this.now=Date.now();
          this.$http.get('http://localhost:8080/post/myBlog', {

            }
            ).then((response => {
                this.posts=response.data;
                console.log(response);
            })).catch((error => {
                console.log(error);
            }));
        }
    }
};
</script>

<style scoped>

</style>
