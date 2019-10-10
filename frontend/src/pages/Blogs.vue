<template>
  <div id="blogs">
    <SideMenu :user="blogUser" v-if="blogUser"/>
    <div id="blog-header" style="position: relative;">

      <div v-if="isOwner">

        <b-form-file
          style="position: absolute; width: 200px;top: 0;right: 40px;"
          id="input-header"
          v-model="file"
          accept="image/*"
          drop-placeholder="Drop file here..."
          required
          v-if="selectImage"
          @input="submitImage"
          plain
        ></b-form-file>
        <b-button id="img-btn"
                  class="blog-btn icon-btn"
                  v-show="isOwner"
                  @click="selectImage=!selectImage">
          <font-awesome-icon icon="edit"/>
        </b-button>
        <b-tooltip triggers="hover" target="img-btn" title="edit image" placement="left"></b-tooltip>
      </div>
      <b-img fluid-grow :src="url" v-show="url">

      </b-img>
      <h1 class="title"> {{ blogName }} </h1>
    </div>
    <b-container ref="container" style="text-align:right">

      <b-button @click="newPost" v-if="isOwner">New post</b-button>

      <div v-for="(post, index) in posts" >

        <BlogpostContainer
          :post="post"
          :index="index"
          :isOwner="isOwner"
          :key="post.dateCreated">
        </BlogpostContainer>
      </div>


    </b-container>
  </div>
</template>

<script>
  import BlogpostContainer from '../components/blogview/BlogpostContainer';
  import SideMenu from '../components/SideMenu';

  export default {
    name: 'Blogs',
    components: {
      BlogpostContainer: BlogpostContainer,
      SideMenu: SideMenu,
    },
    props: ['blogName'],

    data: function () {
      return {
        posts: '',
        isOwner: false,
        file: '',
        url: '',
        selectImage: false,
        blog: '',
        headerImage: '',
        blogUser: null,
      }
    },
    created() {
      this.getBlog(this.blogName);

    },

    methods: {
      submitImage(input) {
        console.log(input);
        console.log(this.file);
        this.url = URL.createObjectURL(this.file);
        let formData = new FormData();
        formData.append('file', input);
        this.$http.put('api/blog/saveImage', formData)
          .then(resp => {
            console.log(resp);
          })
          .catch(err => {
            console.log(err);
          })
      },
      newPost() {
        this.posts.unshift({dateCreated:Date.now()});
        // this.posts.sort();
      },
      getBlog: function (blogName) {

        this.$http.get('api/post/postsByBlogName/' + blogName)
          .then(response => {
            // console.log(response);
            this.posts = response.data;
          }).catch(error => {
          console.log(error);
        });
        this.$http.get('api/blog/isOwner/' + blogName)
          .then(response => {
            this.isOwner = response.data;
          }).catch(error => {
          console.log(error);
        });
        this.$http.get('api/blog/getBlog/' + blogName)
          .then(response => {
            // console.log(response);
            this.blog = response.data;
            if (response.data.headerImage !== null) {
              this.url = 'data:;base64,' + response.data.headerImage
            } else {
              this.url = ''
            }
          })
          .catch(error => {
            console.log(error);
          });
        this.$http.get('api/user/getUser/' + blogName)
          .then(response => {
            // console.log(response.data);
            this.blogUser = response.data;
          })
          .catch(error => {
            console.log(error);
          });
      }
    },
    watch: {
      '$route.params.blogName': function () {
        this.getBlog(this.blogName)
      }

    }


  }
</script>

<style scoped>
  .title {
    text-align: center;
    text-shadow: 2px 2px 3px #333;
    color: white;
    position: absolute;
    right: 0;
    bottom: 20px;
    left: 0;
    z-index: 10;
  }
</style>
