<template>
  <div style="padding: 20px 0">

    <div id="blogpost-container">
      <div
        ref="contents"
        class="ck ck-content ck-editor__editable ck-rounded-corners ck-editor__editable_inline ck-blurred"
        style="border: lightgrey 1px solid; min-height: 3em"
        @click="setEditor"
        v-html="post.postData">
        {{ post.postData }}
      </div>
      <button
        class="submit-btn"
        @click="SubmitPost">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import UploadAdapter from '../scripts/UploadAdapter';
import InlineEditor from '@ckeditor/ckeditor5-build-inline';


export default {
    name: 'BlogpostContainer',
    props: {
        post: Object
    },
    data: function () {
        return {
            bool: true,
            postId: '',
            instance: '',
            editorData: '',
            editor: InlineEditor
        };
    },
    created: function () {
        if (this.post.id != null) {
            this.postId = this.post.id;
            this.editorData = this.post.postData;
        }
    },
    methods: {
        setEditor() {
            if (this.bool === true) {
                this.bool = false;
                InlineEditor.create(this.$refs.contents,
                    {
                        extraPlugins:
                [this.MyCustomUploadAdapterPlugin]
                    }
                ).then((editor) => {
                    this.$refs.contents.focus();
                    this.instance = editor;
                    editor.model.document.on('change:data', () => {
                        this.editorData = this.instance.getData();
                    });
                });
            }
        },
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
        axios.post(url, data, {

           withCredentials: true,
          auth: {
            username: 'lorem@lorem.lorem',
                    password: 'lorem',
                },

                headers: {
                    'Content-Type': 'text/html'
                }
            })
                .then(response => {
                    console.log(response);
            this.postId = response.data;
                }).catch(error => {
                    console.log(error);
                });

        },
        getPost(postId) {
            axios.get('http://localhost:8080/post/downloadPost/' + postId).then(resp => {
                this.editorData = resp.data;
            });
        }
    },
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

  .submit-btn {
    position: absolute;
    top: 0%;
    right: 0%;
    /*transform: translate(-50%, -50%);*/
    /*-ms-transform: translate(-50%, -50%);*/
    /*background-color: #555;*/
    /*color: white;*/
    /*font-size: 16px;*/
    /*padding: 12px 24px;*/
    /*border: none;*/
    /*cursor: pointer;*/
    /*border-radius: 5px;*/
  }

  #blogpost-container {
    position: relative;
  }
  .ck {
    text-align: center;
  }

</style>
