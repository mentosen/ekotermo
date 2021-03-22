import Vue from 'vue';
import Vuex from 'vuex';
import getters from './modules/getters'
import app from './modules/app'
import user from './modules/user'
import objectRegistration1 from "./modules/objectRegistration1";
import objectRegistration2 from "./modules/objectRegistration2";
import objectRegistration3P2 from "./modules/objectRegistration3P2";
import billingMainPage from "./modules/billingMainPage";
import enterCounterValue from "./modules/enterCounterValue";
import objectRegistration3P6 from "./modules/objectRegistration3P6";
import organizationQuestionnaire from "./modules/organizationQuestionnaire";
import workingWithObjectP2 from "./modules/workingWithObjectP2"
import feedback from './modules/feedback'
import objectDistributionArchive from './modules/objectDistributionArchive'
import subscriberProfile from './modules/subscriberProfile'

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        app,
        user,
        objectRegistration1: objectRegistration1,
        objectRegistration2: objectRegistration2,
        objectRegistration3P2: objectRegistration3P2,
        billingMainPage: billingMainPage,
        enterCounterValue:enterCounterValue,
        objectRegistration3P6,
        organizationQuestionnaire,
        workingWithObjectP2,
        feedback,
        objectDistributionArchive,
        subscriberProfile
    },
    getters
});

export default store