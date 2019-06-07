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
          let url = 'http://localhost:8080/image/uploadImage';
          formData.append('file', uploadedFile);
          console.log(formData);
          axios.post(url, formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
            .then(response => {
                resolve({
                  default: response.data,
                });
            }).catch(error => {
            console.log(error);
          });
        });
      });

  }
}
