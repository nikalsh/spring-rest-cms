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
import UploadAdapter from '../scripts/UploadAdapter';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default {
    name: 'CKtest',
    data: function () {
        return {
            editor: ClassicEditor,
            editorData: '<p>Content of the editor.</p>',
            editorConfig: {
                extraPlugins: [this.MyCustomUploadAdapterPlugin]
            }
        };
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

<<<<<<< HEAD
        let url = 'http://localhost:8080/uploadPost';
        let data2=this.editorData;
        //console.log(data2);
        data.append('file', data2);
        axios.blogPost(url, data, {
          headers: {
            'Content-Type': 'text/html'
          }
        })
           .then(response => {
=======
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
>>>>>>> 535d15005d8f32888694fdf9eac5bac6f50e3f97

                    // eslint-disable-next-line no-console
                    console.log(response);

                    //   if (response.data.uploaded) {
                    //     resolve({
                    //       default: response.data.url,
                    //     });
                    //   } else {
                    //     reject(response.data.error.message);
                    //   }
                }).catch(error => {

                    // eslint-disable-next-line no-console
                    console.log(error);
                });

        }
    }
};
</script>
