export default {
    actions:{
        getDataODA(ctx){
            var data =[{
                paymentNumber:1,
                paymentDate: "21.01.2020",
                paymentFrom: "21.12.2019",
                paymentTo:"21.01.2020"
            },
                {
                    paymentNumber:2,
                    paymentDate: "21.01.2020",
                    paymentFrom: "21.12.2019",
                    paymentTo:"21.01.2020"
                },
                {
                    paymentNumber:3,
                    paymentDate: "21.01.2020",
                    paymentFrom: "21.12.2019",
                    paymentTo:"21.01.2020"
                }];

            ctx.commit("addDataODA", data);
        },
        deleteDataODA(ctx, data){

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
