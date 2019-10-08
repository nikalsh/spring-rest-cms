<template>
  <div ref="contents" class="ck-border">
    <ckeditor
              style="min-height: 8em;"
              class="ck"
              @ready="onEditorReady"
              :editor="editor"
              :config="editorConfig">
    </ckeditor>
    <b-spinner class="blog-btn"
               type="grow"
               :variant="spinnerVariant"
               v-if="spinnerOn"></b-spinner>

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
        timeoutId: '',
        spinnerOn: false,
        spinnerVariant: '',
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
          this.post.postData = editor.getData();
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

        this.timeoutId = setTimeout(() => {
          let formData = new FormData();
          formData.append('file', data);
          formData.append('id', this.postId);
          this.$http.post('http://localhost:9090/post/uploadPost', formData)
            .then(response => {
              // console.log(response);
              this.postId = response.data;
              this.spin('success')
            }).catch(error => {
            this.spin('danger');
            console.log(error);
          });
        }, 1000);

      },
      spin(type) {
        this.spinnerOn = true;
        this.spinnerVariant = type;
        setTimeout(() => {
          this.spinnerOn = false;
        }, 1500)
      }
    }
  }
</script>

<style scoped>


</style>
