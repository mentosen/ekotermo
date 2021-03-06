export default {
    actions:{
        getRegionsOQ(ctx){
            ctx.commit("addRegionsOQ", ["Львівська","Харківська","Київська"]);
        },
        getCitiesTypeOQ(ctx){
            ctx.commit("addCitiesTypeOQ", ["Місто","СМТ","Село"]);
        },
        getStreetTypeOQ(ctx){
            ctx.commit("addStreetTypeOQ", ["вулиця","переулок","проспект","тупік"]);
        },
        saveDataOQ(ctx, data){
            ///post
            ctx.commit("updateDataOQ", data);
        }
    },
    mutations:{
        addRegionsOQ(state,data){
            state.regionsOQ = data;
        },
        addCitiesTypeOQ(state,data){
            state.citiesTypeOQ = data;
        },
        addStreetTypeOQ(state,data){
            state.streetTypeOQ = data;
        },
        updateDataOQ(state,data){
            state.dataOQ = data;
            console.log(state.dataOQ);
        }
    },
    state:{
        regionsOQ:[],
        citiesTypeOQ:[],
        streetTypeOQ:[],
        dataOQ:{}
    },

    getters:{
        regionsOQ(state){
            return state.regionsOQ;
        },
        citiesTypeOQ(state){
            return state.citiesTypeOQ;
        },
        streetTypeOQ(state){
            return state.streetTypeOQ;
        },
        dataOQ(state){
            return state.dataOQ;
        }
    }
}
