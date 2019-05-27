<template>
  <div>
    <div class="container">
      <div class="large-12 medium-12 small-12 cell">
        <label>File
          <input
            id="file"
            ref="file"
            type="file"
            @change="handleFileUpload()">
        </label>
        <button @click="submitNewAccountData()">Register</button>
      </div>
    </div>
    <p>
      <label for="name">Name</label>
      <input
        id="name"
        v-model="name"
        type="text"
        name="name"
      >
    </p>

    <p>
      <label for="email">Email</label>
      <input
        id="email"
        v-model="email"
        type="text"
        name="email"
        min="0"
      >
    </p>
    <p>
      <label for="password">Password</label>
      <input
        id="password"
        v-model="password"
        type="text"
        name="password"
        min="0"
      >
    </p>
    <p>
      <input
        type="submit"
        value="Submit"
      >
    </p>
  </div>
</template>

<script>
import FileUpload from './FileUpload';
import axios from 'axios';

function submitFile() {
    let formData = new FormData();
    formData.append('file', this.file);

    // eslint-disable-next-line no-console
    console.log(this.file);

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
}

export default {
    components: {
        FileUpload
    },
    data () {
        return {
            password: '',
            name: '',
            email: '',
            file: '',
            handleFileUpload(){
            }
        };
    },
    methods: {
        submitFile () {

        },
        submitNewAccountData() {
            submitFile();
        }

    },
};
</script>
