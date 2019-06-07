<template>
  <div id="blogs">
    <SideMenu :user="blogUser" v-if="blogUser"/>
    <div id="blog-header" style="position: relative;">

      <div v-show="isOwner">

        <b-form-file
          style="position: absolute; width: 200px;top: 0;right: 40px;"
          id="input-header"
          v-model="file"
          accept="image/*"
          drop-placeholder="Drop file here..."
          required
          v-show="selectImage"
          @input="submitImage"
          plain
        ></b-form-file>
        <b-button id="submit-btn"
                  class="transparent-button"
                  style="z-index:10"
                  v-show="isOwner"
                  @click="selectImage=!selectImage"
        >
          <font-awesome-icon id="save"
                             icon="file-export">
          </font-awesome-icon>

        </b-button>

      </div>
      <b-img fluid-grow :src="url" v-show="url"></b-img>
    </div>
    <b-container>

      <div></div>
      <h1 style="text-align: center"> {{ blogName }} </h1>

      <button @click="newPost" v-show="isOwner">New post</button>
      <div ref="container">
        <BlogpostContainer
          v-for="(post, index) in posts"
          :post="post"
          :isOwner="isOwner"
          :key="index"/>
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
      SideMenu:SideMenu,
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
        blogUser:null,
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
        this.$http.put('http://localhost:8080/blog/saveImage', formData)
          .then(resp => {
            console.log(resp);
          })
          .catch(err => {
            console.log(err);
          })
      },
      newPost() {
        this.posts.unshift({});
      },
      getBlog: function (blogName) {
        console.log(blogName+123);

        this.$http.get('http://localhost:8080/post/postsByBlogName/' + blogName)
          .then(response => {
            console.log(response);
            this.posts = response.data;
          }).catch(error => {
          console.log(error);
        });
        this.$http.get('http://localhost:8080/blog/isOwner/' + blogName)
          .then(response => {
            this.isOwner = response.data;
          }).catch(error => {
          console.log(error);
        });
        this.$http.get('http://localhost:8080/blog/getBlog/' + blogName)
          .then(response => {
            console.log(response);
            this.blog = response.data;
            if (response.data.headerImage !== null) {
              this.url = 'data:;base64,' + response.data.headerImage
            }else{
              this.url=''
            }
          })
          .catch(error => {
            console.log(error);
          });
        this.$http.get('http://localhost:8080/user/getUser/' + blogName)
          .then(response => {
            console.log(response.data);
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

</style>
