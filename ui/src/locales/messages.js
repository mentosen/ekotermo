import common from "@/locales/common"
import homePage from "@/locales/homePage";
import about from "@/locales/about";
import registration from "@/locales/registration";
import langCodes from "@/locales/langCodes";
import contacts from "@/locales/contacts";
import heatPoint from "@/locales/heatPoint";
import heatMettering from "@/locales/heatMettering";
import designAndInstall from "@/locales/designAndInstall";
import objectRegistration from "./objectRegistration";
import objectRegistrationSecondPoint from "./objectRegistrationSecondPoint";
import errors from "./errors";
import objectRegistrationHead from './objectRegistrationHead';
import objectRegistration2 from './objectRegistration2';
import buttons from './buttons'

const locales = {
        common,
        homePage,
        about,
        registration,
        langCodes,
        contacts,
        heatPoint,
        heatMettering,
        designAndInstall,
        objectRegistration,
        objectRegistrationSecondPoint,
        errors,
        objectRegistrationHead,
        objectRegistration2,
        buttons
    },
    messages = {};

Object.keys(locales).forEach((prop) => {
    Object.keys(locales[prop]).forEach((lang) => {
        if (!messages[lang]) messages[lang] = {};
        if(prop === 'elementLocale'){
            messages[lang] = locales[prop][lang];
        }
        messages[lang][prop] = locales[prop][lang];
    });
});

export default messages;