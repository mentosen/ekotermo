export default {
    actions:{
        getBillingData(ctx){
          //get data
            var data = [];
            ctx.commit("saveBillingData", data);
        }
    },
    mutations:{
        saveBillingData(state, data){
            state.billingData = data;
            ///
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
            status: "onAccount"
        }, {
            number:2,
            region:"Миколаївська",
            city:"Миколаїв",
            street:"Соборна",
            buildingNumber:"14",
            status: "onDeleted"
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
