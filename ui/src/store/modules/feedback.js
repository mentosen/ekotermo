export default {
    actions:{
        sendFeedBack(ctx, data){
            ///send
        }
    },
    mutations:{
        changeIsShowFeedback(state,value){
            state.isShowFeedback = value;
        }
    },
    state:{
        isShowFeedback: false
    },
    getters:{
        isShowFeedback(state){
            return state.isShowFeedback;
        }
    }
}
