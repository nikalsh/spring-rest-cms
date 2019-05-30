<template>
  <div id="CKtest">

    <h2>Editor</h2>
    <ckeditor 
      :editor="editor"
      v-model="editorData"
      :config="editorConfig"
      style="border: lightgrey 1px solid;"
      @ready="onEditorReady"/>
    <button @click="emptyEditor()">Empty the editor</button>
    <button @click="readOnly()">set read only</button>
    <button @click="SubmitPost()">Submit</button>
    <br>
    <h2>Raw data</h2>
    <code>{{ editorData }}</code>
    <h2>blogpost from server</h2>
    <div 
      class="ck ck-content ck-editor__editable ck-rounded-corners ck-editor__editable_inline ck-blurred"
      style="border: lightgrey 1px solid;"
      v-html="returnData">
      {{ returnData }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import UploadAdapter from '../scripts/UploadAdapter';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import InlineEditor from '@ckeditor/ckeditor5-build-inline';
// import Autosave from '@ckeditor/ckeditor5-autosave/src/autosave'

export default {
    name: 'CKtest',
    data: function () {
        return {
            instance: '',
            returnData: '',
            postId: '',
            editor: InlineEditor,
            editorData: '<p>Content of the editor.</p>',

            editorConfig: {
                extraPlugins: [
                    this.MyCustomUploadAdapterPlugin

                ]
            }
        };
    },
    methods: {
        onEditorReady(editor) {
            this.instance = editor;
        },
        MyCustomUploadAdapterPlugin(editor) {
            editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                return new UploadAdapter(loader);
            };
        },
        readOnly() {
            this.instance.isReadOnly = !this.instance.isReadOnly;

        }, emptyEditor() {
            this.editorData = '';
        },
        SubmitPost() {
            let data = new FormData();
            let url = 'http://localhost:8080/uploadPost';
            data.append('file', this.editorData);
            data.append('id', this.postId);
            axios.post(url, data, {
                headers: {
                    'Content-Type': 'text/html'
                }
            })
                .then(response => {
                    this.postId = response.data.url;
                    axios.get('http://localhost:8080/downloadPost/' + response.data.url).then(resp => {

                        this.returnData = resp.data;
                    });
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
<style>


  :root {
    --ck-image-style-spacing: 1.5em;
  }


  .image-style-side {
    max-width: 50%;
  }


  .image-style-side {
    float: right;
    margin-left: var(--ck-image-style-spacing);
  }

  .image-style-side > img {
    max-width: 100%;
    height: auto;
    margin: 0 auto;
  }


  .image-style-align-left {
    float: left;
    margin-right: var(--ck-image-style-spacing);
  }


  .image-style-align-center {
    margin-left: auto;
    margin-right: auto;
  }


  .image-style-align-right {
    float: right;
    margin-left: var(--ck-image-style-spacing);
  }


</style>
