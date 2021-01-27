import Cookies from 'js-cookie'

const app = {
    state: {
        sidebar: {
            opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
            withoutAnimation: false
        },
        isMobile: false,
        isPartner: false,
        application: false,
        // language: Cookies.get('language') || 'uk', //possible languages: ru, uk, en
        language: Cookies.get('lng') || 'uk', //possible languages: ru, uk, en
        size: Cookies.get('size') || 'medium'
    },
    mutations: {
        TOGGLE_SIDEBAR: state => {
            state.sidebar.opened = !state.sidebar.opened
            state.sidebar.withoutAnimation = false
            if (state.sidebar.opened) {
                Cookies.set('sidebarStatus', 1)
            } else {
                Cookies.set('sidebarStatus', 0)
            }
        },
        CLOSE_SIDEBAR: (state, withoutAnimation) => {
            Cookies.set('sidebarStatus', 0)
            state.sidebar.opened = false
            state.sidebar.withoutAnimation = withoutAnimation
        },
        TOGGLE_DEVICE: (state) => {
            state.isMobile = !state.isMobile
        },
        PARTNER_MODE: (state) => {
            state.isPartner = !state.isPartner
        },
        SET_PARTNER_MODE: (state, isPartner) => {
            state.isPartner = isPartner;
        },
        SET_LANGUAGE: (state, language) => {
            state.language = language
            Cookies.set('lng', language, {secure: true, path: '', domain: '.eventssion.com'} )
        },
        SET_SIZE: (state, size) => {
            state.size = size
            Cookies.set('size', size)
        },
        SET_VIEW_TYPE: (state) => {
            state.application = true
        }
    },
    actions: {
        toggleSideBar({ commit }) {
            commit('TOGGLE_SIDEBAR')
        },
        closeSideBar({ commit }, { withoutAnimation }) {
            commit('CLOSE_SIDEBAR', withoutAnimation)
        },
        toggleDevice({ commit }) {
            commit('TOGGLE_DEVICE')
        },
        partnerMode({ commit }) {
            commit('PARTNER_MODE')
        },
        setLanguage({ commit }, language) {
            commit('SET_LANGUAGE', language)
        },
        setSize({ commit }, size) {
            commit('SET_SIZE', size)
        },
        setApp({ commit }) {
            commit('SET_VIEW_TYPE')
        }
    }
}

export default app
