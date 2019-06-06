<template>
  <div ref="contents">
    <ckeditor

       style="min-height: 4em" :editor="editor" :config="editorConfig" v-model="editorData" ></ckeditor>
    <button
      style="background-color: transparent; border:none"
      class="submit-btn"
      @click="SubmitPost"
    >
      <font-awesome-icon id="save" icon="file-export"></font-awesome-icon>

    </button>
  </div>
</template>

<script>
  import UploadAdapter from '../scripts/UploadAdapter';
  import InlineEditor from '@ckeditor/ckeditor5-build-inline';

  export default {
    name: "MyEditor",
    props: {
      post: Object
    },
    data: function () {
      return {
        bool: true,
        postId: '',
        instance: '',
        editorData: '',
        editor: InlineEditor,
        editorConfig: {

          extraPlugins:
            [this.MyCustomUploadAdapterPlugin],
          image: {
            toolbar: ['imageTextAlternative', '|', 'imageStyle:alignLeft', 'imageStyle:full', 'imageStyle:alignRight'],

            styles: [
              'full',
              'alignLeft',
              'alignRight'
            ]
          }
        },
      };
    },
    created: function () {
      this.postId = this.post.id;
      this.editorData = this.post.postData||'';
      setTimeout(()=>{
        this.$refs.contents.firstChild.focus()
      }, 1000);

    },
    methods:{
      MyCustomUploadAdapterPlugin(editor) {
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
          return new UploadAdapter(loader);
        };
      },
      SubmitPost() {
        let data = new FormData();
        let url = 'http://localhost:8080/post/uploadPost';
        console.log(this.editorData);
        data.append('file', this.editorData);
        data.append('id', this.postId);
        this.$http.post(url, data, {

        })
          .then(response => {
            console.log(response);
            this.postId = response.data;
          }).catch(error => {
          console.log(error);
        });

      },
    }
  }
</script>

<style scoped>
  .submit-btn {
    position: absolute;
    top: 0%;
    right: 0%;
  }
</style>
