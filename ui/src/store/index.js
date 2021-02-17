import Vue from 'vue';
import Vuex from 'vuex';
import getters from './modules/getters'
import app from './modules/app'
import user from './modules/user'
import objectRegistration1 from "./modules/objectRegistration1";
import objectRegistration2 from "./modules/objectRegistration2";
import objectRegistration3 from "./modules/objectRegistration3";
import billingMainPage from "./modules/billingMainPage";

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        app,
        user,
        objectRegistration1: objectRegistration1,
        objectRegistration2: objectRegistration2,
        objectRegistration3: objectRegistration3,
        billingMainPage: billingMainPage
    },
    getters
});

export default store