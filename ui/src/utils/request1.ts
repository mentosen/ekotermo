import axios from 'axios';
import { Message, MessageBox } from 'element-ui';
import { getToken } from '';
import { UserModule } from '@/store/modules/user';
import router from "@/router";

const service = axios.create({
  baseURL: process.env.BASE_URL,
  // timeout: 1000,
});

// Request interceptors
service.interceptors.request.use(
  (config) => {
    // Add X-Token header to every request, you can add other custom headers here
    if (UserModule.token) {
      // config.headers['X-Token'] = getToken();
      config.headers.Authorization = 'Bearer ' + getToken();
    } else if (getToken()) {
        config.headers.Authorization = 'Bearer ' + getToken();
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  },
);

// Response interceptors
service.interceptors.response.use(
  (response) => {

    // const res = response.data;
    // if (res.code !== 200) {
    //   Message({
    //     message: res.message,
    //     type: 'error',
    //     duration: 5 * 1000,
    //   });
      // if (res.code === 401 || res.code === 403) {
      //   MessageBox.alert(
      //     'Нужна аттоиизация',
      //     'Авторизация',
      //     {
      //       confirmButtonText: 'Ок',
      //       type: 'warning',
      //     },
      //   ).then(() => {
      //     UserModule.FedLogOut().then(() => {
      //       location.reload();  // To prevent bugs from vue-router
      //     });
      //   });
      // }
      // return Promise.reject('error with code: ' + res.code);
    // } else {
      return response;
    // }
  },
  (error) => {

      // if (error.response && error.response.status === 401 || error.response.status === 403) {
      if (error.response && error.response.status === 401 ) {
          // MessageBox.alert(
          //     'Нужна авторизация ',
          //     'Авторизация',
          //     {
          //         confirmButtonText: 'Ок',
          //         type: 'warning',
          //     },
          // ).then(() => {
          //     UserModule.FedLogOut().then(() => {
          //         location.reload();  // To prevent bugs from vue-router
          //     });
          // });
          UserModule.FedLogOut().then(() => {
              location.href = location.origin + '?business=login';
          }).catch(() => {
              location.reload();
          });
      }

      if(error.response.data.code !== 1008 && error.response.data.code !== 1013) {
            Message({
              showClose: true,
              message: error.response.data.message,
              type: 'error',
              duration: 5 * 1000,
            });
      } else {
          router.push('/404');
      }
      return Promise.reject(error);
  },
);

export default service;

