<template>
  <div id="blog">
    <button @click="onClick">Click to insert</button>
    <div ref="container">
      <cktest v-for="(post, index) in posts" :post="post" :key="index">
      </cktest>
    </div>
  </div>
</template>

<script>
  import CKtest from './blogpost-container';
  import Vue from 'vue';
  import axios from 'axios';

  export default {
    name: "Blog",
    components: {
      cktest: CKtest
    },
    data: function () {
      return {
        posts: this.getPosts()
      }
    },
    methods: {

      onClick() {
        var ComponentClass = Vue.extend(CKtest)
        var instance = new ComponentClass({
          propsData: {post:{}}
        });
        instance.$mount();
        this.$refs.container.insertBefore(instance.$el, this.$refs.container.firstChild)
      },
       getPosts() {
        this.now=Date.now();
        axios.get('http://localhost:8080/downloadPostIds/' + '4').then(resp => {
          console.log(resp.data);
          this.posts = resp.data;
        });
      }
    }
  }
</script>

<style scoped>

</style>
