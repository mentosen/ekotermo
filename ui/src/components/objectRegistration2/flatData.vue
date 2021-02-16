<template>
  <div class="wrapper" >
    <div class=" mainTitle" v-bind:class="{mainTitle2: visibilityForm}">
      <div class="flex">
        <div class="title">{{flatData.title}}</div>
        <input type="checkbox" class="switch" @click="visibilityForm=!visibilityForm">
      </div>
    </div>

    <div class="data flex" v-if="visibilityForm">
      <div class="leftPart">
        <div class="flatType leftPartData">Тип № <span class="typeCount">{{typeCount}}</span></div>
        <div class="flex leftPartData">
          <div>Общая площадь</div>
          <div>
            <input type="text" v-model="totalArea" @keydown="validate($event)"
                   @keyup="validateKeyUp" class="totalArea"
                   v-bind:class="flatType+'Total'" placeholder="00000,000">
            <span>м2</span>
          </div>
        </div>
        <div class="flex leftPartData">
          <div>Отапливаемая площадь</div>
          <div>
            <input type="text" v-model="heatingArea" @keydown="validate($event)" @keyup="validateKeyUp"
                   class="heatingArea"
                   v-bind:class="flatType+'Heating'" placeholder="00000,000">
            <span>м2</span>
          </div>
        </div>
        <div class="flex leftPartData">
          <div>Скан-копия планировки помещения</div>
          <div class="inputFile">
            <input type="file" accept="application/pdf,image/*" v-bind:id="flatType+'Scan'">
          </div>
        </div>
        <div class="buttonPart">
          <button class="yellowBtn" @click="saveType">Сохранить тип</button>
          <button class="greenBtn" @click="changeType">Добавить тип</button>
          <button class="greyBtn" @click="edit" v-show="!isEdit">Редактировать</button>
          <button class="blueBtn" @click="saveChanges" v-show="isEdit">Сохранить изменения</button>
        </div>
      </div>

      <div class="rightPart">
        <div class="tableTitle">{{flatData.tableTitle}}</div>
        <table v-bind:id="flatType+'Table'">
          <thead>
          <tr>
            <th>№ п/п</th>
            <th>Общая площадь м2</th>
            <th>Отпапливаемая площадь м2</th>
            <th class="editInputTd">Документ</th>
            <th class="deleteTh" v-if="isEdit">
              <button class="redBtn" @click="deleteScan">Удалить</button>
            </th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="value in data">
            <td>Тип №{{value.typeShort}}</td>
            <td class="editInputTd">

              <input type="text" v-if="isEdit"
                     v-bind:value=value.totalArea
                     class="editInput"
                     @keydown="validate($event, value.typeFull)"
                     @keyup="validateKeyUp"
                     @change="changeTotalValue"
                     placeholder="00000,000">

              <span v-else>{{value.totalArea}}</span>
            </td>
            <td class="editInputTd">

              <input type="text" v-if="isEdit"
                     v-bind:value=value.heatingArea
                     class="editInput"
                     @keydown="validate($event)"
                     @keyup="validateKeyUp"
                     @change="changeHeatingValue"
                     placeholder="00000,000">

              <span v-else>{{value.heatingArea}}</span>
            </td>
            <td class="editInputTd" width="200">{{value.scan.name}}</td>
            <td v-if="isEdit">
              <input type="checkbox" class="switch">
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import {mapMutations, mapGetters, mapActions} from 'vuex'
  export default{
    name:"FlatData",
    props: ["flatData"],
    data(){
      return{
        flatType: this.flatData.dataTitle,
        visibilityForm: false,
        typeCount: 1,
        totalArea: null,
        heatingArea: null,
        scan: null,
        data: null,
        isEdit: false,
        editedData:{},
        greyTd: []
      }
    },

    computed: mapGetters(['getFlatsData','getIsSaved']),

    mounted() {
      this.data = this.getFlatsData[this.flatData.dataTitle];
    },

    methods:{
      ...mapMutations(["addFlatData", "editFlatData","changeIsSaved"]),
      ...mapActions(["saveData"]),

      changeType(){
        this.typeCount++;
        if(this.typeCount > 5) this.typeCount = 1;
      },

      saveType(){
        var total = this.flatType + "Total";
        var heating = this.flatType + "Heating";
        var totalArea = document.querySelector("."+total);
        var heatingArea = document.querySelector("."+heating);

        if(!this.totalArea || !this.heatingArea){

          if(!this.totalArea)totalArea.style.border = "1px solid red";
          if(!this.heatingArea) heatingArea.style.border = "1px solid red";
          this.removeRedBorder(totalArea);
          this.removeRedBorder(heatingArea);

        } else{
          var regEx = /^\d{5},\d{3}$/;
          var total = this.totalArea.match(regEx);
          var heating = this.heatingArea.match(regEx);

          if(!total || ! heating){
            if(!total)totalArea.style.border = "1px solid red";
            if(!heating) heatingArea.style.border = "1px solid red";
            this.removeRedBorder(totalArea);
            this.removeRedBorder(heatingArea);
          }else{
            var id = this.flatType + "Scan";
            var inpFile = document.querySelector("#"+id);
            var scan;
            inpFile.files[0]?scan = inpFile.files[0]:scan="";
            this.scan = scan;

            var data = {
              flatType: this.flatType,
              typeFull: "type"+this.typeCount,
              typeShort: this.typeCount,
              totalArea: this.totalArea,
              heatingArea: this.heatingArea,
              scan: this.scan
            };

            this.addFlatData(data);
            this.totalArea = this.heatingArea = "";
            inpFile.value = "";
            this.changeType();
            this.data = this.getFlatsData[this.flatData.dataTitle];
            this.changeIsSaved(true);
            this.saveData();
          }
        }
      },
      removeRedBorder(el){
        setTimeout(function (){
          el.style.border = "";
        },1000)
      },

      edit(){
        this.isEdit = true;
      },

      deleteScan(){
        var id = this.flatType + "Table";
        var table = document.querySelector("#"+id);
        var trs= table.querySelectorAll("tbody>tr");
        var checked = [];
        var typeNumber;
        var typeFull;

        for(var j = 0; j < trs.length; j++){
          if(trs[j].children[(trs[j].children.length-1)].querySelector("input[type=checkbox]").checked){
              checked.push(trs[j]);
          }
        }

        for(var k = 0; k < checked.length; k++){
          typeNumber = checked[k].children[0].innerText.split("")[5];
          typeFull = "type"+typeNumber;
          checked[k].children[(checked[k].children.length-2)].style.color = "#b3e5fc";

          if(!this.editedData[typeFull]) this.editedData[typeFull] = {};
          this.editedData[typeFull].scan = "";
          this.editedData[typeFull].flatType = this.flatData.dataTitle;
          this.editedData[typeFull].typeFull = typeFull;
          this.editedData[typeFull].typeShort = typeNumber;
        }
      },

      saveChanges(){
        var regEx = /^\d{5},\d{3}$/;
        var id = this.flatType + "Table";
        var table = document.querySelector("#"+id);
        var trs = table.querySelectorAll("tbody>tr");
        var inputs = [];
        var result = true;

        for(var k = 0; k < trs.length;k++){
          inputs.push(trs[k].querySelectorAll("input[type=text]"));
          trs[k].children[3].style.color = "";
        }
        for(var j = 0; j < inputs.length;j++){
          for(var i = 0; i < inputs[j].length; i++){
            if(!inputs[j][i].value.match(regEx)){
              inputs[j][i].style.border = "1px solid red";
              this.removeRedBorder(inputs[j][i]);
              result = false;
            }
          }
        }

        if(result){
          this.editFlatData(this.editedData);
          this.isEdit = false;
          this.editedData = this.getFlatsData[this.flatData.dataTitle];
        }
      },

      validate(e){
        if(e.key == "Backspace"|| e.key === ","){
          return
        } else if(isNaN(e.key) || e.key == " " ||e.target.value.length > 8){
          e.preventDefault();
        }
      },
      validateKeyUp(e){
        if(e.target.value.length === 5 ) e.target.value += ",";
      },
      changeTotalValue(e){
        var typeNumber = (e.target.parentElement.parentElement.children[0].innerText).split("")[5];
        var typeFull = "type"+typeNumber;
        if(!this.editedData[typeFull]) this.editedData[typeFull] = {};
        this.editedData[typeFull].totalArea = e.target.value;
        this.editedData[typeFull].typeFull = typeFull;
        this.editedData[typeFull].typeShort = typeNumber;
        this.editedData[typeFull].flatType = this.flatData.dataTitle;
      },

      changeHeatingValue(e, type){
        var typeNumber = (e.target.parentElement.parentElement.children[0].innerText).split("")[5];
        var typeFull = "type"+typeNumber;
        if(!this.editedData[typeFull]) this.editedData[typeFull]= {};
        this.editedData[typeFull].heatingArea = e.target.value;
        this.editedData[typeFull].typeFull = typeFull;
        this.editedData[typeFull].typeShort = typeNumber;
        this.editedData[typeFull].flatType = this.flatData.dataTitle;
      }
    }
  }
</script>

<style scoped>

.wrapper{
  width: 1200px;
  margin-top: 25px;
  display: flex;
  flex-direction: column;
  font-size: 17px;
}
table{
  width: 600px;
  table-layout: fixed;
}

table,td,th{
  border: 1px solid #ccc;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
}

th{
  font-size: 15px;
}
th:last-child{
  padding: 0;
}
td,th{
  height: 30px;
  overflow: hidden;
}

input[type=text]{
  width: 85px;
  margin: 0;
  padding: 2px;
  padding-left: 10px;
  font-size: 16px;
  outline: none;
  border-radius: 2px;
  border: 1px solid #ccc;
}

input[type=file]{
  width: 105px;
  border: 1px solid #ccc;
  outline: none;
}

.inputFile{

}

input[type=checkbox]{
  border: 1px solid #ccc;
}
.editInput{
  width: 30px;
  height: auto;
}
.editInputTd{
  width: 25%;
}

.yellowBtn,.greenBtn,.greyBtn,.blueBtn{
  width: 130px;
  height: 36px;
  padding: 3px;
  border: 1px solid #f9ed17;
  border-radius: 3px;
  outline: none;
  text-decoration: none;
  background-color: yellow;
  cursor: pointer;
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
  border: 1px solid #757572;
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

.mainTitle{
  width: 900px;
  display: flex;
  justify-content: center;
}

.mainTitle2{
  width: 280px;
  display: flex;
  justify-content: flex-start;
}

.mainTitle>div{
  font-size: 21px;
  font-weight: 500;
}

.title{
  width: 300px;
  text-align: left;
}

.switch{
  width: 18px;
  height: 18px;
}
.data{
  border-radius: 2px;
  border: 1px solid #ccc;
  padding: 25px;
}
.leftPart{
  margin-right: 100px;
  font-size: 19px;
}

.flex{
  display: flex;
  justify-content: space-between;
  text-align: left;
}
.leftPartData{
  width: 400px;
  margin-top: 25px;
}

.typeCount{
  color: blue;
  font-size: 20px;
  font-weight: 500;
}

.buttonPart{
  margin-top: 30px;
  display: flex;
  justify-content: space-between;
}

.tableTitle{
  width: 600px;
  text-align: right;
  font-style: italic;
  font-weight: 500;
}
.deleteTh{
  width: 10%;
}
.greyText{
  color: #eaeaea;
}

</style>
