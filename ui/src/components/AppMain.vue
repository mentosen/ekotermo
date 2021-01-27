<template>
<!--    <div class="main-page-wrapper">-->
<!--        <app-header/>-->
<!--        <app-footer/>-->
<!--    </div>-->
    <div id="main-page-wrapper">
        <app-header
                :isNameExists="isNameExists"
        />

<!--        <app-breadcrumb></app-breadcrumb>-->
        <main class="main wrapper  wrapper--main">
<!--            <sidebar></sidebar>-->
            <div class="wrapper wrapper--content">
                <router-view
                        @user-login="loginUser"
                ></router-view>
            </div>
        </main>
        <!--<app-footer></app-footer>-->
        <app-footer/>
    </div>
</template>

<script>
    import AppHeader from "@/components/AppHeader";
    import AppFooter from "@/components/AppFooter";

    export default {
        name: "AppMain.vue",
        components: {AppHeader, AppFooter},
        data(){
            return {
                isNameExists: false
            }
        },
        methods:{
            loginUser(){
                let that = this;
                that.isNameExists = true;
            }
        }
    }
</script>

<style scoped>

</style>