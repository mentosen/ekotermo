import { createBuilding, findBuilding, editBuilding } from "@/api/building";
import { getRegions } from "@/api/region";

export default {
  actions:{
    saveData(ctx, data){
      //send data
      if(data.id){
        editBuilding(data)
      } else {
        createBuilding(data);
      }
      //update state
      ctx.commit("saveData", data);
    },
    getAllRegions({commit}){
      getRegions().then(response => {
        response.data.sort((a, b) => a.name > b.name ? 1 : -1)

        commit("addRegions", response.data);
        commit("addCities", response.data[0].cities);
      })
    },
    findBuilding(ctx, data){
      findBuilding(data).then(response => {
        ctx.commit("saveData", response.data);
      })
    }
  },
  mutations:{
    changeSection3Url(state, url){
      state.section3Url = url;
    },
    saveData(state, data){
      state.flatInfo = data;
    },
    changeIsDel(state, value){
      state.isDel = value;
    },
    addRegions(state, regions){
      state.regions = regions;
    },
    addCities(state, cities){
      state.cities = cities;
    }
  },
  state:{
    regions:[{title: null, value:null }],
    cities:[{title: null, value:null }],
    flatInfo:{},
    accountState: false,
    isDel: false,
    section3Url: '/objectRegistration3P2',
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
    },
    section3Url(state){
      return state.section3Url;
    }
  }
}
