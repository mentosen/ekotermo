import Vue from 'vue';
import VueI18n from 'vue-i18n';
import messages from '@/locales/messages';
// import store from '@/store';

Vue.use(VueI18n);
export default new VueI18n({
    // locale: store.getters.currentLanguage || 'uk',
    locale: 'uk',
    fallbackLocale: 'ua',
    silentTranslationWarn: false,
    messages,
});