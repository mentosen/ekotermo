import {saveCompany, updateCompany} from '@/api/company';

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
            saveCompany(data).then(response => {
                ctx.commit("saveDataOQ", response.data);
            })
        },
        updateDataOQ(ctx, data){
            updateCompany(data).then(response => {
                ctx.commit("updateDataOQ", response.data);
            })
        }
    },
    mutations:{
        getCompany(state, data){
            state.dataOQ = data;
        },
        addRegionsOQ(state,data){
            state.regionsOQ = data;
        },
        addCitiesTypeOQ(state,data){
            state.citiesTypeOQ = data;
        },
        addStreetTypeOQ(state,data){
            state.streetTypeOQ = data;
        },
        saveDataOQ(state,data){
            state.dataOQ = data;
        },
        updateDataOQ(state,data){
            state.dataOQ = data;
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
