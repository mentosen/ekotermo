import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import VueI18n from 'vue-i18n'

import i18n from "@/utils/i18n";
import router from "@/router/router";

import '@/assets/styles/styles.scss';

import FloatLabel from "@/components/common/Float-input";
import modal from '@/components/common/modalWindow.vue';
import SvgIcon from 'vue-svgicon';

import store from "./store";

import {MDCTextField} from '@material/textfield';

import VeeValidate, { Validator } from 'vee-validate';
import ru from 'vee-validate/dist/locale/ru';
import uk from 'vee-validate/dist/locale/uk';
import en from 'vee-validate/dist/locale/en';

import Notifications from 'vue-notification'

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

const EventBus = new Vue();

let locale = {
  ru: ru,
  uk: uk,
  en: en,
};

Vue.prototype.$bus = EventBus;

Vue.use(VeeValidate);

Vue.use(VueRouter);
Vue.use(VueI18n);
Vue.use(SvgIcon, {tagName: 'svg-icon'});

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app');

Vue.component('float-label', FloatLabel);
Vue.component('modal', modal);
Vue.component('mwc-input', MDCTextField);
Validator.localize(store.getters.currentLanguage, locale[store.getters.currentLanguage]);
Vue.use(Notifications);

Vue.use(Element, {
  // size: Cookies.get('size') || 'medium', // set element-ui default size
  // i18n: (key, value) => i18n.t(key, value)
});
