<template>
    <header class="header">
        <div class="header__wrap">
            <div class="tile__row tile__row--flex-start">
                <a class="logo" href="/">
                    <img
                            style="width: auto"
                            src="../assets/icons/logo.png"
                            alt="homepage"
                            class="page-logo"
                    />
                </a>
                <span class="text-logo tile__row--margin-left">Eko-termo(Beta version)</span>
            </div>
            <div class="tile__row tile__row--flex-end">
                <language-selector/>
                <div v-if="isNameExists()"
                     class="header__column  header__column--row tile__row--margin-right tile__row--align-center">
                    <span class="text-logo tile__row--margin-left">{{name}}</span>
                    <span class="text-logo tile__row--margin-left">{{name}}</span>

                    <dropdown icon="arrow" class="">
                        <dropdown-item
                                :name="$t('common.logout')"
                                @start-action="logout()"
                        >
                        </dropdown-item>
                    </dropdown>
                </div>
                <button-component
                        v-if="name === ''"
                        :name="$t('common.login')"
                        @click="login"
                />
                <button-component
                        v-if="name === ''"
                        :name="$t('common.register')"
                        @click="register"
                />
            </div>
        </div>
    </header>
</template>

<script>
    import ButtonComponent from "@/components/common/ButtonComponent";
    import LanguageSelector from "./LanguageSelector";
    import Dropdown from "@/components/common/Dropdown";
    import DropdownItem from "@/components/common/DropDownItem";

    import { removeToken } from "@/utils/auth";

    export default {
        name: "AppHeader",
        components:{ButtonComponent, LanguageSelector, Dropdown, DropdownItem},
        props:{
        },
        data(){
            return{
                name: ''
            }
        },
        mounted() {
            let that = this;

            that.$bus.$on("setUser", function() {
                that.name = that.$store.getters.getUserInfo.name
            });
        },
        methods:{
            isNameExists(){
                let that = this;
                that.$store.dispatch("GetUserInfo").then(() => {
                    that.name = that.$store.getters.getUserInfo.name
                });

                return this.name !== '';
            },
            login(){
                this.$router.push('/login')
            },
            register(){
                this.$router.push('/register')
            },
            logout(){
                let that = this;
                that.name = '';
                removeToken();
                that.$store.dispatch("FedLogOut").then(() => {
                    if(this.$router.currentRoute.path === '/'){
                        location.reload();
                    } else {
                        this.$router.push('/')
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .page-logo {
        height: 60px;
    }

    .text-logo {
        font-size: 24px;
    }
</style>
