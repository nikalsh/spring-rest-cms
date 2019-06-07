<template>
  <div ref="contents">
    <ckeditor ref="contents"
              @ready="onEditorReady"
              style="min-height: 4em; text-align: center;"
              :editor="editor"
              :config="editorConfig">

    </ckeditor>
    <b-button id="submit-btn"
              style="z-index:10"
              class="transparent-button"
              @click="submitPost">
      <font-awesome-icon id="save"
                         icon="file-export">
      </font-awesome-icon>
    </b-button>
  </div>
</template>

<script>
  import UploadAdapter from '../../scripts/UploadAdapter';
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
        timeoutId:'',
        editor: InlineEditor,
        editorConfig: {

          extraPlugins:
            [this.myCustomUploadAdapterPlugin],
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

    methods: {
      onEditorReady(editor) {
        this.postId = this.post.id;
        this.editorData = this.post.postData;
        editor.setData(this.post.postData || '');
        editor.model.document.on('change:data', () => {
          this.editorData = editor.getData();
          this.submitPost(editor.getData())
        });
        this.$refs.contents.firstChild.focus();
      },
      myCustomUploadAdapterPlugin(editor) {
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
          return new UploadAdapter(loader);
        };
      },
      submitPost(data) {
        if (this.timeoutId) {
          clearTimeout(this.timeoutId);
        }

        this.timeoutId = setTimeout( ()=> {
          let formData = new FormData();
          console.log(data);
          formData.append('file', data);
          formData.append('id', this.postId);
          this.$http.post('http://localhost:8080/post/uploadPost', formData)
            .then(response => {
              console.log(response);
              this.postId = response.data;
            }).catch(error => {
            console.log(error);
          });
        }, 1000);


      },
    }
  }
</script>

<style scoped>


</style>
