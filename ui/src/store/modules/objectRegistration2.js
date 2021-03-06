export default {
  actions:{
    saveFlatData(ctx, data){
      //send state.flatsData
      //update state
      ctx.commit("addFlatData", data);
    }
  },
  mutations:{
    addFlatData(state, data){
      var keys = Object.keys(state.flatsData);

      for(var k = 0; k < keys.length;k++){
        if(keys[k] === data.flatType){
          state.flatsData[keys[k]][data.typeFull] = {totalArea: data.totalArea,
                                                    heatingArea: data.heatingArea,
                                                    typeShort: data.typeShort,
                                                    scan: data.scan,
                                                    flatType:data.flatType,
                                                    typeFull:data.typeFull};
          break;
        }
      }

    },

    editFlatData(state, data){
      var keys = Object.keys(state.flatsData);
      var dataKeys = Object.keys(data);

      for(var k = 0; k < keys.length;k++){
        for(var j = 0; j < dataKeys.length;j++){
          if(keys[k] == data[dataKeys[j]].flatType){
            Object.assign(state.flatsData[keys[k]][data[dataKeys[j]].typeFull], data[dataKeys[j]]);
          }
        }
      }
    },
    changeIsSaved(state, bool){
      state.isSaved = bool;
    }
  },
  state:{
    flatsData:{
      oneFlatRoom:{},
      twoFlatRoom:{},
      threeFlatRoom:{},
      fourFlatRoom:{},
      fiveFlatRoom:{},
      nonLivePremises:{}
    },
    isSaved: false
  },
  getters:{
    getFlatsData(state){
      return state.flatsData;
    },
    getIsSaved(state){
      return state.isSaved;
    }
  }
}
