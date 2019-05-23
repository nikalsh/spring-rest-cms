<template>
  <div id="CKtest">

    <ckeditor type="classic" v-model="editorData" :config="editorConfig" :uploadAdapter="UploadAdapter"></ckeditor>
    <button v-on:click="emptyEditor()">Empty the editor</button>

    <h2>Editor data</h2>
    <code>{{ editorData }}</code>

  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'CKtest',
    data: function () {
      return {
        editorData: '<p>Content of the editor.</p>',
        editorConfig: {
          extraPlugins: [this.MyCustomUploadAdapterPlugin]
        },
        UploadAdapter: function (loader) {
          this.loader = loader

          this.upload = () => {
            var body = new FormData();
            body.append('file', this.loader.file);
            body.append('typeOption', 'upload_image');
            //console.log(body);
            console.log(this.loader.file);
            body.forEach(function(value, key){
              console.log(value);
              console.log(key);
            });
            return new Promise((resolve, reject) => {
              axios({
                url: 'http://localhost:8080/uploadImage',
                method: 'post',
                body,
                headers: {
                  'Content-Type': 'multipart/form-data;',
                  'Access-Control-Allow-Origin': '*'
                },
                withCredentials: false
              }).then(res => {
                var resData = res.data;
                resData.default = resData.url;
                resolve(resData);
              }).catch(error => {
                reject(error)
              });
            });

            // const data = new FormData();
            // data.append('typeOption', 'upload_image');
            // data.append('file', this.loader.file);

            // return axios({
            //   url: `${API}forums`,
            //   method: 'post',
            //   data,
            //   headers: {
            //     'Authorization': tokenCopyPaste()
            //   },
            //   withCredentials: true
            // }).then(res => {
            //   console.log(res)
            //   var resData = res.data;
            //   resData.default = resData.url;
            //   return resData;
            // }).catch(error => {
            //   console.log(error)
            //   return Promise.reject(error)
            // });

          }
          this.abort = () => {
            console.log('Abort upload.')
            // server.abortUpload();
          }
        }
      }
    },
    methods: {
      MyCustomUploadAdapterPlugin(editor) {
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
          // Configure the URL to the upload script in your back-end here!
          return new UploadAdapter(loader);
        };
      },
      emptyEditor() {
        this.editorData = '';
      }
    }
  }
</script>
