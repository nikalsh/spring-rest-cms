import axios from "axios";

export default class UploadAdapter {
  constructor(loader) {
    this.loader = loader;
  }

  upload() {
    return this.loader.file
      .then(uploadedFile => {
        return new Promise((resolve, reject) => {
          let formData = new FormData();
          let url = 'http://localhost:8080/uploadImage';
          formData.append('file', uploadedFile);
          console.log(formData);
          axios.post(url, formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
            .then(response => {
              //console.log(response);
               // if (true) {
                resolve({
                  default: response.data,
                });
              // } else {
              //   reject(response.data.error.message);
              // }
            }).catch(error => {
            console.log(error);
          });
        });
      });

  }
}
