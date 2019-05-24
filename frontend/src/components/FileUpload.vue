<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input 
          id="file" 
          ref="file" 
          type="file" 
          @change="handleFileUpload()">
      </label>
      <button @click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
    data () {
        return {
            file: ''
        };
    },

    methods: {
        submitFile () {
            let formData = new FormData();
            formData.append('file', this.file);

            // eslint-disable-next-line no-console
            console.log(this.file);

            // let xhr = new XMLHttpRequest()
            //   xhr.open('POST', '/uploadFile')
            //
            //   xhr.onload = function () {
            //       console.log(xhr.responseText)
            // var response = JSON.parse(xhr.responseText)
            // if (xhr.status == 200) {
            //     singleFileUploadError.style.display = 'none'
            //     singleFileUploadSuccess.innerHTML = '<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href=\'' + response.fileDownloadUri + '\' target=\'_blank\'>' + response.fileDownloadUri + '</a></p>'
            //     singleFileUploadSuccess.style.display = 'block'
            // } else {
            //     singleFileUploadSuccess.style.display = 'none'
            //     singleFileUploadError.innerHTML = (response && response.message) || 'Some Error Occurred'
            // }
            // }
            //
            // xhr.send(formData)

            /*
          Make the request to the POST /single-file URL
        */
            axios.post('http://localhost:8080/uploadFile',
                formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
            ).then(function () {

                // eslint-disable-next-line no-console
                console.log('SUCCESS!!');
            })
                .catch(function () {

                    // eslint-disable-next-line no-console
                    console.log('FAILURE!!');
                });
        },

        /*
        Handles a change on the file upload
      */
        handleFileUpload () {
            this.file = this.$refs.file.files[0];
        }
    }
};
</script>
