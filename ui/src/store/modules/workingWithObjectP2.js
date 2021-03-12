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
            for(var k = 0; k < state.dataWWOP2.flatsData.length;k++){
                if(data.apartmentNumber == state.dataWWOP2.flatsData[k].apartmentNumber){
                    state.dataWWOP2.flatsData[k] = data;
                    break
                }
            }
        },
        addMeterReadingsWWOP2(state,data){
            state.dataWWOP2.generalMeterReadings = data;
        },
        changeIsAllFilledWWOP2(state, value){
            state.isAllFilledWWOP2 = value;
        },
        addFlatsDataWWOP3(state,data){
            state.flatsData = data;
        }
    },
    state:{
        dataWWOP2:{
            flatsData:[{
                apartmentNumber:1,
                generalArea:100,
                heatedArea:90,
                previousReading:123456.78,
                currentReading:"000000,00",
                currentConsumption:123456.78,
                premisesConsumption:123456.78,
                sumConsumption:123456789,
                sumMoney: 1000,
                errors:["someError1", "someError2"]
            },
                {
                    apartmentNumber:2,
                    generalArea:100,
                    heatedArea:90,
                    previousReading:123456.78,
                    currentReading:"000000,00",
                    currentConsumption:123456.78,
                    premisesConsumption:123456.78,
                    sumConsumption:123456789,
                    sumMoney: 1000,
                    errors:["someError1"]
                },
                {
                    apartmentNumber:3,
                    generalArea:100,
                    heatedArea:90,
                    previousReading:123456.78,
                    currentReading:"000000,00",
                    currentConsumption:123456.78,
                    premisesConsumption:123456.78,
                    sumConsumption:123456789,
                    sumMoney: 1000,
                    errors:["someError1"]
                }],
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
        },
        flatsDataWWOP2(state){
            return state.dataWWOP2.flatsData;
        }
    }
}
