export default {
  actions:{
    saveData(ctx, data){
      //send data
      //update state
      ctx.commit("saveData", data);
    },
    getAllRegions(ctx){
      //
      ctx.commit("addRegions", 1);
      ctx.commit("addCities", 2);
    }
  },
  mutations:{
    saveData(state, data){
      state.flatInfo = data;
    },
    changeIsDel(state, value){
      state.isDel = value;
    },
    addRegions(state, num){
      var data = [];

      for(var k = 0; k < num; k++){
        var obj = {title:"some", value:"some"};
        data.push(obj);
      }
      state.regions = data;
    },
    addCities(state, num){
      var data = [];

      for(var k = 0; k < num; k++){
        var obj = {title: "Kiev"+k, value: "Kiev"+k};
        data.push(obj);
      }
      state.cities = data;
    }
  },
  state:{
    regions:[],
    cities:[],
    flatInfo:{},
    accountState: false,
    isDel: false
  },
  getters:{
    getRegions(state){
      return state.regions;
    },
    getCities(state){
      return state.cities;
    },
    getAccountState(state){
      return state.accountState;
    },
    getIsDel(state){
      return state.isDel;
    },
    getFlatInfo(state){
      return state.flatInfo;
    }
  }
}
