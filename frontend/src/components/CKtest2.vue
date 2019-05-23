<template>
  <div id="CKtest2">

    <ckeditor type="classic" v-model="details" :config="editorConfig" :editor="editor"></ckeditor>

    <h2>Editor data</h2>
    <code>{{ details }}</code>
  </div>
</template>

<script>

  import axios from "axios";

  class UploadAdapter {
    constructor(loader) {
      this.loader = loader;
      console.log('Upload Adapter Constructor', this.loader);
    }

    upload() {

      return new Promise((resolve, reject) => {
        const data = new FormData();
        data.append('file', this.loader.file);

        axios({
          url: 'http://localhost:8080/uploadImage',
          method: 'post',
          data,
          headers: {
            'Content-Type': 'multipart/form-data;'
          },
          withCredentials: false
        }).then(response => {
          if (response.data.result == 'success') {
            resolve({
              default: response.data.url
            });
          } else {
            reject(response.data.message);
          }
        }).catch(response => {
          reject('Upload failed');
        });

      });

    }

    abort() {

    }
  }

  import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
  export default {
    name: 'CKtest2',
    data: function () {
      return {
        details: '',
        editor :ClassicEditor,
        editorConfig:
          {
            extraPlugins: [this.MyCustomUploadAdapterPlugin]
          }
      }
    },
    methods:
      {
        MyCustomUploadAdapterPlugin(editor) {
          editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
            console.log("hej.log  ");
            return new UploadAdapter(loader);
          };
        }

      }
  }


</script>

<style scoped>

</style>




