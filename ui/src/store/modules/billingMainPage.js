export default {
    actions:{
        getBillingData(ctx){
          //get data
            var data = [];
            ctx.commit("saveBillingData", data);
        },
        saveCounterValue(ctx, data){
            ///
            ctx.commit("updateCounterValue", data);
        },
        saveBillingObjects(ctx, data){
            ctx.commit("saveBillingObjects", data);
        }
    },
    mutations:{
        saveBillingData(state, data){
            state.billingData = data;
            ///
        },
        updateCounterValue(state, data){
            state.billingObjects[data.index] = data.data;
        },
        saveBillingObjects(state, data){
            state.billingObjects = data;
        }
    },
    state:{
        billingData: null,
        billingDataHead: null,
        billingObjects:[]
    },
    getters:{
        billingData(state){
            return state.billingData;
        },
        billingDataHead(state){
            return state.billingDataHead;
        },
        billingObjects(state){
            return state.billingObjects;
        }
    }
}
