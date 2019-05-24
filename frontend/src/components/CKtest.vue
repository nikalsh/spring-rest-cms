<template>
  <div id="CKtest">

    <ckeditor :editor="editor" v-model="editorData" :config="editorConfig" ></ckeditor>
    <button v-on:click="emptyEditor()">Empty the editor</button>

    <h2>Editor data</h2>
    <code>{{ editorData }}</code>

  </div>
</template>

<script>

  import UploadAdapter from "../scripts/UploadAdapter";
  import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

  export default {
    name: 'CKtest',
    data: function () {
      return {
        editor:ClassicEditor,
        editorData: '<p>Content of the editor.</p>',
        editorConfig: {
          extraPlugins: [this.MyCustomUploadAdapterPlugin]
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
      }
    }
  }
</script>
