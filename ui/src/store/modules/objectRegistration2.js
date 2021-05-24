import { createFlatType, editFlatType, findAllFlatTypes } from "@/api/flatType";

export default {
  actions:{
    saveFlatData(ctx, data){
      createFlatType(data);

      ctx.commit("saveFlatTypeData", data);
    },
    findAllFlatTypes(ctx, data){
      findAllFlatTypes(data).then(response => {
        debugger
        if(response.data.length){
          ctx.commit("changeIsSaved", true);
        }
        ctx.commit("addFlatData", response.data);
      })
    }
  },
  mutations:{
    addFlatData(state, data){
      var keys = Object.keys(state.flatsData);

      for(var k = 0; k < keys.length;k++){
        state.flatsData[keys[k]] = data.filter(function (item) {
          return item.flatType.toLowerCase() === keys[k];
        });
      }
    },

    saveFlatTypeData(state, data){
      state.flatsData[data.flatType.toLowerCase()].push(data);
    },

    editFlatData(state, data){
debugger
      editFlatType(data)
    },
    changeIsSaved(state, bool){
      debugger
      state.isSaved = bool;
    }
  },
  state:{
    flatsData:{
      single_room:{},
      double_room:{},
      triple_room:{},
      quadriple_room:{},
      quintiple_room:{},
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
