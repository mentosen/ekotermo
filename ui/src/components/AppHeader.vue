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
                <span v-if="isNameExists()" class="text-logo tile__row--margin-left">{{name}}</span>
                <span v-if="isNameExists()" class="text-logo tile__row--margin-left">{{name}}</span>
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

    export default {
        name: "AppHeader",
        components:{ButtonComponent, LanguageSelector},
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
                debugger
                that.name = that.$store.getters.getUserInfo.name
            });
        },
        methods:{
            isNameExists(){
                let that = this;
                debugger
                that.$store.dispatch("GetUserInfo").then(() => {
                    debugger
                    that.name = that.$store.getters.getUserInfo.name
                });

                return this.name !== '';
            },
            login(){
                this.$router.push('/login')
            },
            register(){
                this.$router.push('/register')
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
