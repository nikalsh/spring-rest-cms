<template>
  <div id="CKtest">

    <ckeditor
      :editor="editor"
      v-model="editorData"
      :config="editorConfig"/>
    <button @click="emptyEditor()">Empty the editor</button>

    <h2>Editor data</h2>
    <code>{{ editorData }}</code>
    <button @click="SubmitPost()">Submit</button>
  </div>
</template>

<script>
  import axios from 'axios';
  import UploadAdapter from "../scripts/UploadAdapter";
  import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
  import autosave from '@ckeditor/ckeditor5-autosave';

export default {
    name: 'CKtest',
    data: function () {
      return {
        editor: ClassicEditor,
        editorData: '<p>Content of the editor.</p>',
        editorConfig: {
          extraPlugins: [this.MyCustomUploadAdapterPlugin,
          autosave]
        }
      }
    },
    methods: {
        MyCustomUploadAdapterPlugin(editor) {
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                return new UploadAdapter(loader);
            };
        },
        emptyEditor() {
            this.editorData = '';
        },
        SubmitPost() {
            let data = new FormData();

        let url = 'http://localhost:8080/uploadPost';
        let data2=this.editorData;
        //console.log(data2);
        data.append('file', data2);
        axios.post(url, data, {
          headers: {
            'Content-Type': 'text/html'
          }
        })
           .then(response => {

            console.log(response);
          //   if (response.data.uploaded) {
          //     resolve({
          //       default: response.data.url,
          //     });
          //   } else {
          //     reject(response.data.error.message);
          //   }
          }).catch(error => {
          console.log(error);
        });

        }
    }
};
</script>
