<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    /*
      Defines the data used by the component
    */
    data () {
        return {
            file: ''
        }
    },

    methods: {
        /*
        Submits the file to the server
      */
        submitFile () {
        /*
                Initialize the form data
            */
            let formData = new FormData()

            /*
            Add the form data we need to submit
        */
            formData.append('file', this.file)
            let xhr = new XMLHttpRequest()
            xhr.open('POST', '/uploadFile')

            xhr.onload = function () {
                console.log(xhr.responseText)
                // var response = JSON.parse(xhr.responseText)
                // if (xhr.status == 200) {
                //     singleFileUploadError.style.display = 'none'
                //     singleFileUploadSuccess.innerHTML = '<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href=\'' + response.fileDownloadUri + '\' target=\'_blank\'>' + response.fileDownloadUri + '</a></p>'
                //     singleFileUploadSuccess.style.display = 'block'
                // } else {
                //     singleFileUploadSuccess.style.display = 'none'
                //     singleFileUploadError.innerHTML = (response && response.message) || 'Some Error Occurred'
                // }
            }

            xhr.send(formData)

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
                console.log('SUCCESS!!')
            })
                .catch(function () {
                    console.log('FAILURE!!')
                })
        },

        /*
        Handles a change on the file upload
      */
        handleFileUpload () {
            this.file = this.$refs.file.files[0]
        }
    }
}
</script>
