<template>
    <div class="header__column--row tile__row--margin-right">
        <dropdown icon="arrow" class="dropdown--lang">
            <span class="header__text" slot="label" @click="labelClicked">{{currentLanguage.toUpperCase()}}</span>
            <dropdown-item
                    name="ru"
                    @start-action="changeLang('ru')"
            >
            </dropdown-item>
            <dropdown-item
                    name="ua"
                    @start-action="changeLang('uk')"
            >
            </dropdown-item>
            <dropdown-item
                    name="en"
                    @start-action="changeLang('en')"
            >
            </dropdown-item>
        </dropdown>
    </div>
</template>

<script>
    import Dropdown from "@/components/common/Dropdown";
    import DropdownItem from "@/components/common/DropDownItem";
    export default {
        name: "LanguageSelector",
        components: { Dropdown, DropdownItem },
        data() {
            return {
                currentLanguage: this.$t('langCodes.' + 'uk'),
            };
        },
        methods: {
            labelClicked(){
                this.$children[0].closeHandler()
            },
            changeLang(locale){
                // Vue.ekotermo.config.lang = locale
                this.$store.dispatch('toggleLanguage', locale);
                // this.$i18n.locale = Vue.ekotermo.config.lang
                this.$i18n.locale = locale;
                return this.currentLanguage = this.$t('langCodes.'+locale)
            },
        },
    };
</script>

<style scoped>
</style>
