<template>
  <b-container id="blog">
    <button @click="onClick">New post</button>
    <div ref="container">
      <BlogpostContainer
        v-for="(post, index) in posts" 
        :post="post" 
        :key="index">
      </BlogpostContainer>
    </div>
  </b-container>
</template>

<script>
import BlogpostContainer from './BlogpostContainer';
import Vue from 'vue';
import axios from 'axios';

export default {
    name: 'Blog',
    components: {
        BlogpostContainer: BlogpostContainer
    },
    data: function () {
        return {
            posts: this.getPosts()
        };
    },
    methods: {

        onClick() {
            var ComponentClass = Vue.extend(BlogpostContainer);
            var instance = new ComponentClass({
                propsData: {post:{}}
            });
            instance.$mount();
            this.$refs.container.insertBefore(instance.$el, this.$refs.container.firstChild);
        },
        getPosts() {
            this.now=Date.now();
            axios.get('http://localhost:8080/downloadPostIds/' + '4').then(resp => {
                console.log(resp.data);
                this.posts = resp.data;
            });
        }
    }
};
</script>

<style scoped>

</style>
