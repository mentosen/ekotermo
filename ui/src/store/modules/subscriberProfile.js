export default {
    actions:{
        getRegionsSP(ctx){
            var data = ["Mиколаївська","Львівська","Харківська"];
            var name = "regionsSP";
            ctx.commit("addStateDataSP", {data:data, name:name});

        },
        getCityTypeSP(ctx){
            var data = ["Місто","СМТ","Село"];
            var name = "cityTypeSP";
            ctx.commit("addStateDataSP", {data:data, name:name});

        },
        getStreetTypeSP(ctx){
            var data = ["Вулиця","Переулок","Проспект","Тупік"];
            var name = "streetTypeSP";
            ctx.commit("addStateDataSP", {data:data, name:name});

        },
        sendDataSP(ctx, data){
            //send data
            console.log(data);
        }
    },
    mutations:{
        addStateDataSP(state, data){
            state[data.name] = data.data;
        }
    },
    state:{
        regionsSP:[],
        cityTypeSP:[],
        streetTypeSP:[],
    },

    getters:{
        regionsSP(state){
            return state.regionsSP;
        },
        cityTypeSP(state){
            return state.cityTypeSP;
        },
        streetTypeSP(state){
            return state.streetTypeSP;
        },
    }
}
