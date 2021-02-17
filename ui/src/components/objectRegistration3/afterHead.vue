<template>
  <div class="content">
    <div>
      <span>Не учитывать</span>
      <button  @click="accountedBtnClick" v-bind:class="{redBtn: isAccounted, greenBtn:!isAccounted}">ON/OFF</button>
    </div>
    <div class="content-div">
      <div>Объем потребленного тепла для мест общего пользования и вспомогательных помещений:</div>
      <div class="content-div-flex">
        <select name="cofSelect" id=""  @focus="selectFocus" @blur="selectBlur" @click="selectClick" v-show="!isSavedCof1">
          <option v-bind:value="item.value" v-for="item in changedOption" v-bind:selected="data.cof1 == item.value">{{item.title}}</option>
        </select>
        <div v-if="isSavedCof1" class="cofDiv">{{data.cof1}}</div>
        <button class="yellowBtn" @click="saveCof1" v-bind:disabled="isSavedCof1 || isAccounted">Сохранить</button>
        <button class="greyBtn" @click="editCof1"  v-bind:disabled="isEditCof1 || isAccounted">Редактировать</button>
      </div>
    </div>
    <div class="content-div">
      <div>Поправочный коэффициент(понижающий или повышающий) принятый жильцами дома:</div>
      <div class="content-div-flex">
        <input type="text" name="cofInput" @keydown="validate" @keyup="validateKeyUp" v-show="!isSavedCof2"
               v-bind:value="data.cof2" placeholder="0,00">
        <div v-if="isSavedCof2" class="cofDiv">{{data.cof2}}</div>
        <button class="yellowBtn" @click="saveCof2" v-bind:disabled="isSavedCof2 || isAccounted">Сохранить</button>
        <button class="greyBtn" @click="editCof2" v-bind:disabled="isEditCof2 || isAccounted">Редактировать</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from 'vuex'
export default {
  name:"afterHead",
  mounted() {
    var cofInput = document.querySelector("input[name=cofInput]");
    this.data = this.getHeatingCofs();

    if(this.data.cof1){
      this.isAccounted = this.data.isAccounted;
      this.isSavedCof1 = this.data.isSavedCof1;
      this.isEditCof1 = this.data.isEditCof1;
      this.isSavedCof2 = this.data.isSavedCof2;
      this.isEditCof2 = this.data.isEditCof2;
    }
    cofInput.value = "1,00";
  },
  data(){
    return{
      isAccounted: false,
      isSavedCof1:false,
      isSavedCof2: false,
      isEditCof1: true,
      isEditCof2: true,
      data:{},
      originalOption:["одноэтажное здание 20% (0,20)","двухэтажное здание 18% (0,18)","трехэтажное здание 16% (0,16)",
        "четырехэтажное здание 14% (0,14)","пятиэтажное здание 12% (0,12)","шестиэтажное здание и более 10% (0,10)"],
      changedOption:[{value:0.20, title: "0,20"},{value:0.18, title: "0,18"},
        {value:0.16, title: "0,16"},{value:0.14, title: "0,14"},
        {value:0.12, title: "0,12"},{value:0.10, title: "0,10"}],
      selectedIndex:null,
      selectClicked:0,
      startCofValue:{
        cof1:"0.00",
        cof2: "1.00",
        isAccounted: true,
        isSavedCof1: true,
        isEditCof1: false,
        isSavedCof2: true,
        isEditCof2: false,
      }
    }
  },
  methods:{
    ...mapActions(["saveHeatingCofs"]),
    ...mapGetters(["getHeatingCofs"]),
    ...mapMutations(["changeIsAllFilled2"]),
    accountedBtnClick(e){
      this.isAccounted = !this.isAccounted;
      this.saveHeatingCofs(Object.assign({},this.startCofValue));
      this.data = this.getHeatingCofs();

      if(this.isAccounted){
        this.isSavedCof1 = true;
        this.isEditCof1 = false;
        this.isSavedCof2 = true;
        this.isEditCof2 = false;
        this.changeIsAllFilled2(true);
      }else{
        this.isSavedCof1 = false;
        this.isEditCof1 = true;
        this.isSavedCof2 = false;
        this.isEditCof2 = true;
        this.changeIsAllFilled2(false);
      }
    },
    saveCof1(e){
      var cof1 = document.querySelector("select[name=cofSelect]").value;
      var cof2 = document.querySelector("input[name=cofInput]").value;
      var value = cof2.replace(",",".");
      var obj = {
        cof1: cof1,
        cof2: value,
        isAccounted: false,
        isSavedCof1: true,
        isEditCof1: false,
        isSavedCof2: true,
        isEditCof2: false,
      }
      this.data.cof1 = e.target.value;
      this.isSavedCof1 = true;
      this.isEditCof1 = false;
      this.saveHeatingCofs(obj);
      this.data = obj;
      if(this.isSavedCof1 && this.isSavedCof2) this.changeIsAllFilled2(true);
    },
    saveCof2(e){
      var cof1 = document.querySelector("select[name=cofSelect]").value;
      var cof2 = document.querySelector("input[name=cofInput]");
      var value = cof2.value.replace(",",".");
      if(value > 2 || value < 0.20 || !cof2.value){
        cof2.style.border = "1px solid red";
        this.removeRedBorder(cof2);
        return
      }
      var obj = {
        cof1: cof1,
        cof2: value,
        isAccounted: false,
        isSavedCof1: true,
        isEditCof1: false,
        isSavedCof2: true,
        isEditCof2: false,
      }
      this.data.cof2 = e.target.value;
      this.isSavedCof2 = true;
      this.isEditCof2 = false;
      this.saveHeatingCofs(obj);
      this.data = obj;
      if(this.isSavedCof1 && this.isSavedCof2) this.changeIsAllFilled2(true);
    },
    editCof1(){
      this.isSavedCof1 = false;
      this.isEditCof1 = true;
      this.changeIsAllFilled2(false);
    },
    editCof2(){
      this.isSavedCof2 = false;
      this.isEditCof2 = true;
      this.changeIsAllFilled2(false);
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    selectFocus(e){
      this.selectedIndex = e.target.selectedIndex;

      var options = e.target.options;
      for(var k = 0; k < options.length; k++){
        options[k].style.width = "290px";
        options[k].innerText = this.originalOption[k];
      }
    },
    selectBlur(e){
      var options = e.target.options;
      for(var k = 0; k < options.length; k++){
        options[k].style.width = "290px";
        options[k].innerText = this.changedOption[k].title;
      }
      this.selectClicked = 0;
    },
    selectClick(e){
      this.selectClicked +=1;
      if(this.selectedIndex !=e.target.selectedIndex){
        this.selectedIndex = e.target.selectedIndex;
        e.target.blur();
      }
      if(this.selectClicked == 2){
        e.target.blur();
        this.selectClicked = 0;
      }
    },
    validate(e){
      if(e.key == "Backspace"|| e.key === "," || e.key == "ArrowLeft" || e.key == "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " ||e.target.value.length > 3){
        e.preventDefault();
      }
    },
    validateKeyUp(e){
      if(e.target.value.length == 1){
        if(e.key == "Backspace") return
        e.target.value+=",";
      }
    }
  }
}
</script>


<style scoped>

.content{
  padding-top: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #969393;
  width: 1100px;
}

.content-div{
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.content-div-flex{
  display: flex;
  justify-content: space-between;
}

input, select{
  margin-right: 10px;
  border: none;
  outline: none;
  text-align: center;
  border:1px solid #ccc;
  padding-left: 5px;
  width: 150px;
  border-radius: 3px;
  text-align-last:center;
}
select>option{
  width: 290px;
}
input{
  width: 141px;
}
.cofDiv{
  margin-right: 70px;
}
.greenBtn,.redBtn,.greyBtn,.yellowBtn{
  width: 100px;
  padding: 5px;
  border: 1px solid #f9ed17;
  border-radius: 3px;
  outline: none;
  text-decoration: none;
  background-color: yellow;
  cursor: pointer;
}
.yellowBtn{
  margin-right: 5px;
}
.redBtn{
  border: 1px solid #f12929;
  background-color: #ea4c4c;
  margin-left: 10px;
}
.greenBtn{
  background-color: #13f113;
  border: 1px solid #1bd400;
  margin-left: 10px;
}
.greyBtn{
  background-color: #d4d4cb;
  border: 1px solid #757572;
}

.redBtn:hover{
  background-color: #fb6f6f;
}

.greenBtn:hover{
  background-color: #a7fda7;
}
.yellowBtn:hover{
  background-color: #ffffa1;
}
.greyBtn:hover{
  background-color: #e6e6de;
}

</style>