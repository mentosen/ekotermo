import Vue from 'vue';
import * as VeeValidate from 'vee-validate'

const config = {
    events: 'input|blur|change',
    fieldsBagName: 'formFields',
};

Vue.use(VeeValidate, config);
