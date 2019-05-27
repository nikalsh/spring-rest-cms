import axios from 'axios';

export default class UploadAdapter {
    constructor(loader) {
        this.loader = loader;
    }

    upload() {
        return this.loader.file
            .then(uploadedFile => {
                return new Promise((resolve, reject) => {
                    let image = new FormData();
                    let url = 'http://localhost:8080/uploadImage';
                    // eslint-disable-next-line no-console
                    console.log(uploadedFile);
                    image.append('file', uploadedFile);
                    axios.post(url, image, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    })
                        .then(response => {
                            // eslint-disable-next-line no-console
                            console.log(response);
                            if (response.data.uploaded) {
                                resolve({
                                    default: response.data.url,
                                });
                            } else {
                                reject(response.data.error.message);
                            }
                        }).catch(error => {
                            // eslint-disable-next-line no-console
                            console.log(error);
                        });
                });
            });

    }
}
