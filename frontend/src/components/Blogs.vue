<template>
  <div id="blogs">
    <h1 > {{ blogName }} </h1>

    <button @click="newPost" v-show="isOwner">New post</button>
    <div ref="container">
      <BlogpostContainer
        v-for="(post, index) in posts"
        :post="post"
        :isOwner="isOwner"
        :key="index"/>
    </div>
  </div>
</template>

<script>
  import BlogpostContainer from './BlogpostContainer';

  export default {
    name: 'Blogs',
    components: {
      BlogpostContainer: BlogpostContainer
    },
    props: ['blogName'],

    data: function () {
      return {
        posts: this.getPosts(this.blogName),
        isOwner:false
      }

    },

    methods: {
      newPost() {
        this.posts.unshift({});
      },
      getPosts:function(blogName) {

        this.now = Date.now();
        this.$http.get('http://localhost:8080/post/postsByBlogName/' + blogName,'')
          .then((response => {
          console.log(response);
          this.posts= response.data;
        })).catch((error => {
          console.log(error);
        }));
        this.$http.get('http://localhost:8080/blog/isOwner/'+blogName,'')
          .then((response => {
            // console.log(response.data);
            this.isOwner = response.data;
               console.log(this.isOwner);
          })).catch((error => {
          console.log(error);
        }));


      }
    },
    watch: {

      '$route.params.blogName' : function(blogName){
        this.posts = this.getPosts(this.blogName)
      }

  }




  }
</script>

<style scoped>

</style>
