export default {
  actions:{
    getData(ctx){
      //get request
      ctx.commit("addFlatsObj", 2);
      ctx.commit("addEntranceObj", 3);
      ctx.commit("addFlatTypeObj", 4);
      ctx.commit("addCofObj", 5);
      ctx.commit("addFloors", 6);
      ctx.commit("addRoomPurpose");
    },
    saveFlatData(ctx, data){
      ctx.commit("addDataFlat", data);
      // post request
    },
    removeImage(ctx, index){

    },
    saveHeatingCofs(ctx, data){
      ctx.commit("changeHeatingCofs", data);
    },
    saveAllFlatDataP2(ctx, data){

    }
  },
  mutations:{
    addDataFlat(state, data){
      state.dataFlat[data.flatNumber - 1] = data;
      state.dataFlat[data.flatNumber - 1].isEdit = false;
    },
    addFloors(state, num){
      var data = [];

      for(var k = 0; k < num; k++){
        var obj = {num: k+1};
        data.push(obj);
      }
      state.floors = data;
    },
    addFlatsObj(state, num){
      var data = [];

      for(var k = 0; k < num; k++){
        var obj = {num: k+1, objectPhoto: null};
        data.push(obj);
      }
      state.apartments = data;
    },
    addEntranceObj(state, num){
      var data = [];

      for(var k = 0; k < num; k++){
        var obj = {num: k+1};
        data.push(obj);
      }
      state.entrance = data;
    },
    addFlatTypeObj(state, num){
     var data = [];

      for(var k = 0; k < num; k++){
        var obj = {num: k+1};
        data.push(obj);
      }
      state.flatType = data;
    },
    addCofObj(state, num){
     var data = [];

      for(var k = 0; k < num; k++){
        var obj = {num: k+1};
        data.push(obj);
      }
      state.cof = data;
    },
    changeShowPopUp(state, value){
      state.showPopUp = value;
    },
    changeIsAllFilled1(state, value){
      state.isAllFilled1 = value;
    },
    changeIsAllFilled2(state, value){
      state.isAllFilled2 = value;
    },
    changeHeatingCofs(state, data){
      state.heatingCofs = data;
    },
    addRoomPurpose(state,data){
      state.roomPurpose = ["Кухня","Зал №1","Зал №1.1","Зал №2","Зал №2.2","Спальня №1","Спальня №1.1",
        "Спальня №2","Спальня №2.1","Ванная комната","Балкон №1",
        "Балкон №2","Лоджия","Коридор №1","Коридор №2"]
    }

  },
  state:{
    heatingCofs:{},
    dataFlat:[],
    apartments: [],
    entrance:[],
    floors:[],
    flatType:[],
    cof:[],
    isAllFilled1: false,
    isAllFilled2: false,
    showPopUp: false,
    roomPurpose:[]
  },

  getters:{
    getApartments(state){
      return state.apartments;
    },
    getEntrance(state){
      return state.entrance;
    },
    getFlatType(state){
      return state.flatType;
    },
    getCof(state){
      return state.cof;
    },
    getFloors(state){
      return state.floors;
    },
    getIsAllFilled1(state){
      return state.isAllFilled1;
    },
    getIsAllFilled2(state){
      return state.isAllFilled2;
    },
    getDataFlat(state){
      return state.dataFlat;
    },
    getShowPopUp(state){
      return state.showPopUp;
    },
    getHeatingCofs(state){
      return state.heatingCofs;
    },
    getRoomPurpose(state){
      return state.roomPurpose;
    }
  }
}
