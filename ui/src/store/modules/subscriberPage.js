export default {
    actions:{
        getSubscriberPageData(ctx){
            var data = [{
                paymentNumber:1,
                paymentDate: "21.01.2020",
                paymentFrom: "21.12.2019",
                paymentTo:"21.01.2020",
                currentConsumptionGkal:"1,0000",
                currentConsumptionGrn:"00123,00"
            },
                {
                    paymentNumber:2,
                    paymentDate: "21.01.2020",
                    paymentFrom: "21.12.2019",
                    paymentTo:"21.01.2020",
                    currentConsumptionGkal:"1,0000",
                    currentConsumptionGrn:"00123,00"
                },
                {
                    paymentNumber:3,
                    paymentDate: "21.01.2020",
                    paymentFrom: "21.12.2019",
                    paymentTo:"21.01.2020",
                    currentConsumptionGkal:"1,0000",
                    currentConsumptionGrn:"00123,00"
                }]
            ctx.commit("addSubscriberPageData", data);
        }
    },
    mutations:{
        addSubscriberPageData(state, data){
            state.subscriberPageData = data;
        }
    },
    state:{
        subscriberPageData:{}
    },

    getters:{
        subscriberPageData(state){
            return state.subscriberPageData;
        }
    }
}
