export default {
    actions:{
        sendFileWWOP2(ctx,file){
            // send file
            ctx.commit("saveFileWWOP2", file);
        },
        saveDataWWOP2(ctx,data){
            ctx.commit("addDataWWOP2", data);
        },
        saveAllFlatDataWWOP2(ctx, data) {
            console.log(data);
            //
        }
    },
    mutations:{
        saveFileWWOP2(state,file){
            state.dataWWOP2.file = file;
        },
        changeIsManualInputWWOP2(state, value){
            state.isManualInputWWOP2 = value;
        },
        addDataWWOP2(state,data){
            var result = false;
            for(var k = 0; k < state.dataWWOP2.flatsData.length;k++){
                if(data.apartmentNumber == state.dataWWOP2.flatsData[k].apartmentNumber){
                    state.dataWWOP2.flatsData[k] = data;
                    result = true;
                    break
                }
            }
            if(!result || state.dataWWOP2.flatsData.length == 0){
                state.dataWWOP2.flatsData.push(data);
            }
        },
        addMeterReadingsWWOP2(state,data){
            state.dataWWOP2.generalMeterReadings = data;
        },
        changeIsAllFilledWWOP2(state, value){
            state.isAllFilledWWOP2 = value;
        }
    },
    state:{
        dataWWOP2:{
            flatsData:[],
            generalMeterReadings:{}
        },
        isManualInputWWOP2:false,
        isAllFilledWWOP2: false
    },

    getters:{
        getFileWWOP2(state){
            return state.file;
        },
        isManualInputWWOP2(state){
            return state.isManualInputWWOP2;
        },
        isAllFilledWWOP2(state){
            return state.isAllFilledWWOP2;
        },
        dataWWOP2(state){
            return state.dataWWOP2;
        }
    }
}
