export default {
    actions:{
        getDataODA(ctx){
            var data;
            ctx.commit("addDataODA", data);
        },
        downloadDataODA(){

        }
    },
    mutations:{
        addDataODA(state, data){
            state.dataODA = data;
        }
    },
    state:{
        dataODA:{}
    },

    getters:{
        dataODA(state){
            return state.dataODA;
        }
    }
}
