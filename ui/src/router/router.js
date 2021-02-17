import Vue from 'vue';
import Router from 'vue-router';
import AppMain from '@/components/AppMain.vue';
// import i18n from '@/utils/i18n';

Vue.use(Router);

export default new Router({
    mode: 'history',
    scrollBehavior: (to, from, savedPosition) => {
        if (savedPosition) {
            return savedPosition;
        } else {
            return {x: 0, y: 0};
        }
    },
    routes: [
        // {path: '/login', component: () => import(/* webpackChunkName: "login" */ '@/views/Login.vue')},
        // {path: '/404', component: () => import(/* webpackChunkName: "404" */ '@/views/404.vue')},
        {
            path: '/',
            component: AppMain,
            name: 'main',
            // meta: {
            //     title: i18n.t('routes.Organizations'),
            // },
            children: [
                {
                    path: '/register',
                    name: 'Registration',
                    component: () => import('../components/registration/RegistrationContainer.vue')
                },
                {
                    path: '/login',
                    name: 'Login',
                    component: () => import('../components/Login.vue')
                },
                {
                    path: '',
                    name: 'Home page',
                    component: () => import('@/components/HomePage.vue'),
                    // meta: {
                    //     title: i18n.t('routes.Statistic'),
                    // },
                },
                {
                    path: '/about',
                    name: 'About us',
                    component: () => import('@/components/mainBtnPages/About.vue'),
                },
                {
                    path: '/contacts',
                    name: 'Contacts',
                    component: () => import('@/components/mainBtnPages/Contacts.vue'),
                },
                {
                    path: '/instalation',
                    name: 'Design and Instalation',
                    component: () => import('@/components/mainBtnPages/DesignAndInstalation.vue'),
                },
                {
                    path: '/economy',
                    name: 'Economy results',
                    component: () => import('@/components/mainBtnPages/EconomyResults.vue'),
                },
                {
                    path: '/mettering',
                    name: 'Heat mettering',
                    component: () => import('@/components/mainBtnPages/HeatMettering.vue'),
                },
                {
                    path: '/point',
                    name: 'Heat point',
                    component: () => import('@/components/mainBtnPages/HeatPoint.vue'),
                },
                {
                    path: '/objectRegistration1',
                    name: 'Object registration1',
                    component: () => import('@/components/objectRegistration1/ObjectRegistration1.vue'),
                },
                {
                    path: '/objectRegistration2',
                    name: 'Object registration2',
                    component: () => import('@/components/objectRegistration2/ObjectRegistration2.vue'),
                },
                {
                    path: '/objectRegistration3',
                    name: 'Object registration3',
                    component: () => import('@/components/objectRegistration3/ObjectRegistration3.vue'),
                },
                {
                    path: '/billingMainPage',
                    name: 'Billing Main Page',
                    component: () => import('@/components/billingMainPage/billingMainPage'),
                }
            ],
        },
        {path: '*', redirect: '/404'},
    ],
});