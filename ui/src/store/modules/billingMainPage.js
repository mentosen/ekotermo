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
        }
    },
    mutations:{
        saveBillingData(state, data){
            state.billingData = data;
            ///
        },
        updateCounterValue(state, data){
            state.billingObjects[data.index] = data.data;
        }
    },
    state:{
        billingData: null,
        billingDataHead: null,
        billingObjects:[{
            number:1,
            region:"Київська",
            city:"Київ",
            street:"Богдана Хмельницького",
            buildingNumber:"5",
            status: "onAccount",
            isSaved:false,
            isEdit: true,
            counterValue: null
        }, {
            number:2,
            region:"Миколаївська",
            city:"Миколаїв",
            street:"Соборна",
            buildingNumber:"14",
            status: "onDeleted",
            isSaved:false,
            isEdit: true,
            counterValue: null
        }, {
            number:3,
            region:"Херсонська",
            city:"Херсон",
            street:"Мазепи",
            buildingNumber:"29",
            status: "onRegister",
            isSaved:false,
            isEdit: true,
            counterValue: null
            }
        ]
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
