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
    <!--  <p>-->
    <!--    <input-->
    <!--      type="submit"-->
    <!--      value="Submit"-->
    <!--    >-->
    <!--  </p>-->
  </div>
</template>

<!--org.h2.jdbc.JdbcSQLException: Referential integrity constraint violation: "FKA5CO4Q37J6P04SWIBHECBWLFA: PUBLIC.FILES FOREIGN KEY(BLOGADMIN_ID) REFERENCES PUBLIC.BLOGADMIN(BLOGADMIN_ID) (1)"; SQL statement:-->
<!--insert into files (blogadmin_id, data, file_name, file_type, id) values (?, ?, ?, ?, ?) [23506-193]-->

<script>
import axios from 'axios';

export default {
    data() {
        return {
            password: '',
            name: '',
            email: '',
            file: '',
        };
    },
    methods: {
        submitFile() {
            let formData = new FormData();
            formData.append('file', this.file);
            formData.append('password', this.password);
            formData.append('name', this.name);
            formData.append('email', this.email);
            // eslint-disable-next-line no-console
            console.log(this.file);

            axios.post('http://localhost:8080/registerUser',

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
*/
        handleFileUpload() {
            this.file = this.$refs.file.files[0];
        }
    }
};
</script>
