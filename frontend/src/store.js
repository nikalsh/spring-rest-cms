import axios from 'axios';
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    status: '',
    access_token: localStorage.getItem('access_token') || '',
    currentUser: {}
  },
  mutations: {
    auth_request(state) {
      state.status = 'loading';
    },
    auth_success(state, access_token) {
      state.status = 'success';
      state.access_token = access_token;
    },
    auth_error(state) {
      state.status = 'error';
    },
    logout(state) {
      state.status = '';
      state.acess_token = '';
    },
    userdata(state, currentUser){
      state.currentUser=currentUser;
    }

  },
  actions: {
    login({commit}, user) {
      return new Promise((resolve, reject) => {
        commit('auth_request');
        var params = new URLSearchParams();
        params.append("username", user.username);
        params.append("password", user.password);
        params.append("grant_type", "password");
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
            console.log(resp);
            const access_token = resp.data.access_token;
            localStorage.setItem('access_token', access_token);
            // Add the following line:
            axios.defaults.headers.common['Authorization'] = 'bearer ' + access_token;
            axios.get('http://localhost:8080/user/me')
              .then(  resp=>{
                commit('userdata',resp.data);
                console.log(resp.data);
              });
            commit('auth_success', access_token);
            resolve(resp)
          })
          .catch(err => {
            commit('auth_error');
            localStorage.removeItem('access_token');
            reject(err)
          })
      })
    },
    register({commit}, user) {
      return new Promise((resolve, reject) => {
        commit('auth_request');
        axios({url: 'http://localhost:3000/register', data: user, method: 'POST'})
          .then(resp => {
            const access_token = resp.data.token;
            const user = resp.data.user;
            localStorage.setItem('access_token', access_token);
            // Add the following line:
            axios.defaults.headers.common['Authorization'] = access_token;
            commit('auth_success', access_token, user);
            resolve(resp)
          })
          .catch(err => {
            commit('auth_error', err);
            localStorage.removeItem('access_token');
            reject(err)
          })
      })
    },
    logout({commit}) {
      return new Promise((resolve, reject) => {
        commit('logout')
        localStorage.removeItem('access_token')
        delete axios.defaults.headers.common['Authorization']
        resolve()
      })
    }
  },

  getters: {
    isLoggedIn: state => !!state.access_token,
    authStatus: state => state.status,
    getUser: state=> state.currentUser
  }


})
