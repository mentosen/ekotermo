import { loginByUsername, logout, getUserInfo } from "@/api/user";
import { getToken, setToken, removeToken } from "@/utils/auth";
// import {sendAnalyticsLogIn} from "@/utils/sessionAnalityc"

const user = {
  state: {
    token: getToken(),
    userInfo: {
      name:'',
      id:'',
      email:'',
      phone:'',
      time_zone:'',
      avatar:'',
      isAuth: false
    },
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code;
    },
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction;
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting;
    },
    SET_STATUS: (state, status) => {
      state.status = status;
    },
    SET_NAME: (state, name) => {
      state.userInfo.name = name;
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar;
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles;
    },

    SET_USER_INFO: (state, user) => {
      state.userInfo = user;
    }

  },

  actions: {
    LoginByUsername({ commit }, userInfo) {
      const username = userInfo.username.trim();
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password)
          .then(response => {
            const data = response.data;
            commit("SET_TOKEN", data.token);
            setToken(response.data.token);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        if(state.token){
          if (window.dataLayer !== undefined) {
            window.dataLayer.push({
              'event': "event-to-ga",
              'eventCategory': 'auth',
              'eventAction': 'try-login',
              'eventLabel': 'token'
            });
          }
        }
        state.token && getUserInfo()
          .then(response => {
            if (!response.data) {
              reject("Verification failed, please login again.");
            }
            const data = response.data;
            let avatar =  data.avatar_path && data.avatar_path.indexOf('.jpg')!== -1 ? data.avatar_path.replace('.jpg', '_tiny.jpg') : null
            const user = {
              ...data,
              avatar,
              isAuth: true
            }
            commit("SET_USER_INFO", user);
            if (window.dataLayer !== undefined) {
              window.dataLayer.push({
                'event': "event-to-ga",
                'eventCategory': 'auth',
                'eventAction': 'login',
                'eventLabel': 'token'
              });
            }
            resolve(response);
          })
          .catch(error => {
            if (window.dataLayer !== undefined) {
              window.dataLayer.push({
                'event': "event-to-ga",
                'eventCategory': 'auth',
                'eventAction': 'error-login',
                'eventLabel': 'token'
              });
            }
            reject(error);
          });
      });
    },

    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token)
          .then(() => {
            commit("SET_TOKEN", "");
            commit("SET_ROLES", []);
            removeToken();
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit("SET_TOKEN", "");
        removeToken();
        resolve();
      });
    },

    ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit("SET_TOKEN", role);
        setToken(role);
        getUserInfo(role).then(response => {
          const data = response.data;
          commit("SET_ROLES", data.roles);
          commit("SET_NAME", data.name);
          // commit("SET_AVATAR", data.avatar);
          commit("SET_INTRODUCTION", data.introduction);
          dispatch("GenerateRoutes", data); // 动态修改权限后 重绘侧边菜单
          resolve();
        });
      });
    }
  },
  getters: {
    getUserInfo: state => {
      debugger
      return state.userInfo;
    },
    getUserToken: state => {
      debugger
      return state.token;
    },
  }
};

export default user;
