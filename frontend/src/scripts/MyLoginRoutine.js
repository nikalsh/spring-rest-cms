import axios from 'axios';

export const myLoginRoutine = user => new Promise((resolve, reject) => {
    console.log(user);

    var params=new URLSearchParams();
    params.append('username', user.username);
    params.append('password', user.password);
    params.append('grant_type', 'password');
    axios({
        method: 'post',
        url: 'http://localhost:8080/oauth/token',
        withCredentials: true,
        crossdomain: true,
        data: params,
        headers: {
            'Authorization': 'Basic YW5kcm9pZC1jbGllbnQ6YW5kcm9pZC1zZWNyZXQ=',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }
    )
        .then(resp => {
            console.log(resp.data.access_token);

            const token = resp.data.access_token;

            localStorage.setItem('access_token', token); // store the token in localstorage
            resolve(resp);
        })
        .catch(err => {
            localStorage.removeItem('access_token'); // if the request fails, remove any possible user token if possible
            reject(err);
        });
});
