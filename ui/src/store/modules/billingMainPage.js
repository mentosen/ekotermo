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
            region:"Івано-Франківська",
            city:"Івано-Франківськ",
            street:"Богдана Хмельницького",
            buildingNumber:"5",
            status: "onAccount",
            category:"Розділ ІІ пункт 3 підпункт 2",
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
            category:"Розділ ІІ пункт 3 підпункт 2",
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
            category:"Розділ ІІ пункт 3 підпункт 2",
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
