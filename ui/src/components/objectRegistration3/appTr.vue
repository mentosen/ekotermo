<template>
  <tr class="appTr">
    <td>{{flatNumber}}</td>
    <td class="selectTd">
      <select class="tableSelect" v-if="isEdit">
        <option v-for="(num, key) in getEntrance" v-bind:value="num.num" v-bind:selected="data.entrance == num.num">{{key+1}}</option>
      </select>
      <div v-if="!isEdit">{{data.entrance}}</div>
    </td>
    <td>
      <select class="tableSelect"  v-if="isEdit">
        <option v-for="(num, key) in getFloors" v-bind:value="num.num" v-bind:selected="data.floor == num.num">{{key+1}}</option>
      </select>
      <div v-if="!isEdit">{{data.floor}}</div>
    </td>
    <td class="selectTd">
      <select class="tableSelect" v-if="isEdit">
        <option v-for="(num, key) in getFlatType" v-bind:value="num.num" v-bind:selected="data.flatType == num.num">Тип №{{key+1}}</option>
      </select>
      <div v-if="!isEdit">Тип №{{data.flatType}}</div>
    </td>
    <td class="selectTd">
      <select class="tableSelect" v-if="isEdit">
        <option v-for="(num, key) in getCof" v-bind:value="num.num" v-bind:selected="data.cof == num.num">{{key+1}}</option>
      </select>
      <div v-if="!isEdit">{{data.cof}}</div>
    </td>
    <td>
      <input type="text" class="tableInpText" @keydown="validateCounter" placeholder="12-значное число"
             v-if="isEdit" v-bind:value="data.counterNum" @change="saveValue" name="counterNum">
      <div v-if="!isEdit">{{data.counterNum}}</div>
    </td>
    <td>
      <input type="text" class="tableInpText" @keydown="validateStartCounterVal" @keyup="validateStartCounterKeyUp"
      placeholder="123456,78" v-if="isEdit" v-bind:value="data.counterStartVal" @change="saveValue" name="counterStartVal">
      <div v-if="!isEdit">{{data.counterStartVal}}</div>
    </td>
    <td>
      <div class="btnPart">
        <label>
          <div class="greenBtn btnDiv" v-bind:class="{disabled:!isEdit}">Добавить +</div>
          <input type="file" class="tableInpFile" @change="addPhoto" accept="application/pdf,image/*" multiple v-bind:disabled="!isEdit">
        </label>
        <button class="blueBtn" v-bind:disabled="!isPhotos" @click="showAllPhotos">Просмотреть</button>
      </div>
    </td>
    <td>
      <div class="btnPart">
        <button class="yellowBtn" @click="save" v-bind:disabled="!isEdit">Сохранить</button>
        <button class="greyBtn" @click="edit" v-bind:disabled="isEdit">Редактировать</button>
      </div>
    </td>
    <slider v-if="showPhotos" v-bind:photos="urls" @closeSlider="closeSlider" @isPhoto="isPhotos = false"></slider>
  </tr>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import slider from "./slider.vue"

export default {
  name: 'appHead',
  props:['flatNumber'],
  computed: mapGetters(['getEntrance','getFlatType','getCof','getApartments', 'getFloors','getDataFlat']),
  mounted() {
    if(this.getDataFlat[this.flatNumber-1]){
      this.data = this.getDataFlat[this.flatNumber-1];
      this.isEdit = this.getDataFlat[this.flatNumber-1].isEdit;
      this.objectPhoto = this.getDataFlat[this.flatNumber-1].objectPhoto;
      if(this.objectPhoto.length > 0) this.isPhotos = true;
    }
  },
  data(){
    return{
      isPhotos: false,
      isEdit: true,
      data: {},
      objectPhoto:[],
      floorLimit: 50,
      floorLengthLimit: 1,
      counterLengthLimit: 11,
      startCounterValLimit: 8,
      showPhotos: false,
      urls:["https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Felis_silvestris_lybica_1.jpg/275px-Felis_silvestris_lybica_1.jpg",
        "https://icdn.lenta.ru/images/2020/07/13/16/20200713161106967/square_320_4dd6db2859b29f4b1ba2d684155a5cc6.png",
      "https://sib.fm/storage/article/February2020/Z0tp5pg7QDhkIZ06GKhM.jpg"]
    }
  },
  components:{slider:slider},
  methods:{
    ...mapActions(['saveFlatData']),
    ...mapMutations(["changeIsAllFilled1"]),
    addPhoto(e){
      var files = e.target.files;

      for(var k = 0; k < files.length; k ++){
        this.objectPhoto.push(files[k]);
      }
      this.isPhotos = true;
    },
    save(e){
      var parent = e.target.closest("tr");
      var inputs = parent.querySelectorAll("input[type=text]");
      var regEx1 = /^\d{12}$/;
      var regEx2 = /^\d{6},\d{2}$/;

      if(!inputs[0].value.match(regEx1) || !inputs[1].value.match(regEx2)){
        if(!inputs[0].value.match(regEx1)) inputs[0].style.border = "1px solid red";
        if(!inputs[1].value.match(regEx2)) inputs[1].style.border = "1px solid red";

        setTimeout(function (){
          inputs[0].style.border = "";
          inputs[1].style.border = "";
        },1000)
      }else{
        var obj = {
          flatNumber: parent.children[0].innerText,
          entrance: parent.children[1].children[0].value,
          floor: parent.children[2].children[0].value,
          flatType: parent.children[3].children[0].value,
          cof: parent.children[4].children[0].value,
          counterNum: parent.children[5].children[0].value,
          counterStartVal: parent.children[6].children[0].value,
          objectPhoto: this.objectPhoto
        }
        this.data = Object.assign(this.data, obj);
        this.saveFlatData(this.data);
        this.isEdit = false;
        if(this.getApartments.length === this.getDataFlat.length) this.changeIsAllFilled1(true);
      }
    },
    edit(){
      this.isEdit = true;
      this.changeIsAllFilled1(false);
    },
    validateCounter(e){
      if(e.key == "Backspace"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.counterLengthLimit){
        e.preventDefault();
      }
    },
    validateFloor(e){
      if(e.key == "Backspace"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.floorLengthLimit){
        e.preventDefault();
      }
    },
    validateFloorKeyUp(e){
      if(parseInt(e.target.value) > this.floorLimit){
        var val = e.target.value.split("");
        val.splice(val.length-1,1);
        e.target.value = val.join("");
      }
    },
    validateStartCounterVal(e){
      if(e.key == "Backspace" || e.key == ","|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.startCounterValLimit){
        e.preventDefault();
      }
    },
    validateStartCounterKeyUp(e){
      if(e.target.value.length == 6){
        if(e.key === "Backspace") return
        e.target.value += ",";
      }
    },
    saveValue(e){
      this.data[e.target.name] = e.target.value;
    },
    showAllPhotos(){
      this.showPhotos = true;
    },
    closeSlider(){
      this.showPhotos = false;
    }
  }
}
</script>

<style scoped>
*{
  font-size: 18px;
}
.appTr{

}

table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}

th{
  font-size: 15px;
}
td:nth-child(8){
  padding: 0;
}
td:nth-child(9){
  padding: 0;
}
td,th{
  height: 30px;
  overflow: hidden;
}
td{
  padding: 0;
}
.selectTd{
}

table input[type=text]{
  width: 130px;
  margin: 0;
  padding: 2px;
  padding-left: 5px;
  font-size: 16px;
  outline: none;
  border-radius: 2px;
  border: 1px solid #3c3a3a;
}

table input[type=file]{
  width: 103px;
  border: 1px solid #3c3a3a;
  outline: none;
}
/*buttons*/
button{
  border: none;
  outline: none;
  text-decoration: none;
  background: none;
  cursor: pointer;
}
.yellowBtn,.greenBtn,.greyBtn,.blueBtn{
  width: 100%;
  height: 25px;
  padding: 3px;
  border: 1px solid #f9ed17;
  background-color: yellow;
  font-size: 15px;
}
.redBtn{
  width: 100%;
  height: 100%;
  border: none;
  background-color: #ea4c4c;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  outline: none;
}

.greenBtn{
  background-color: #13f113;
  border: 1px solid #1bd400;
}

.greyBtn{
  background-color: #d4d4cb;
  border: 1px solid #b1b1ab;
}

.blueBtn{
  background-color: #01beff;
  border: 1px solid #1183c1;
}

.redBtn:hover{
  background-color: #fb6f6f;
}

.yellowBtn:hover{
  background-color: #ffffa1;
}
.greenBtn:hover{
  background-color: #a7fda7;
}
.greyBtn:hover{
  background-color: #e6e6de;
}
.blueBtn:hover{
  background-color: #83c4f7;
}
.btnDiv{
  width: 100%;
  height: 17px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 15px;
  text-align: center;
  border-radius: 0px;
}

/*inputs*/

.tableInpText{

}

.tableSelect{
  display: block;
  outline: none;
  border: 1px solid white;
  width: 100%;
  height: 100%;
  font-size: 16px;
  text-align: center;
  cursor: pointer;
  text-align-last:center;
}

.tableInpFile{
  display: none;
}
.disabled{
  color: #616161;
  background-color: #86f776;
}
</style>
