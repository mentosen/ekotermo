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
        <div class="flatType leftPartData">Тип № <span class="typeCount">{{typeNumber}}</span></div>
        <div class="flex leftPartData">
          <div>{{ $t('objectRegistration2.totalArea')}}</div>
          <div>
            <input type="text" v-model="totalArea" @keydown="validate($event)"
                   @keyup="validateKeyUp" class="totalArea"
                   v-bind:class="flatType+'Total'" placeholder="00000.000">
            <span>м2</span>
          </div>
        </div>
        <div class="flex leftPartData">
          <div>{{ $t('objectRegistration2.heatedArea')}}</div>
          <div>
            <input type="text" v-model="heatingArea" @keydown="validate($event)" @keyup="validateKeyUp"
                   class="heatingArea"
                   v-bind:class="flatType+'Heating'" placeholder="00000.000">
            <span>м2</span>
          </div>
        </div>
        <div class="flex leftPartData">
          <div>{{ $t('objectRegistration2.scanCopy')}}</div>
          <div class="inputFile">
            <input type="file" accept="application/pdf,image/*" v-bind:id="flatType+'Scan'">
          </div>
        </div>
        <div class="buttonPart">
          <button class="yellowBtn" @click="saveType">{{ $t('buttons.saveType')}}</button>
          <button class="greyBtn" @click="edit" v-show="!isEdit">{{ $t('buttons.edit')}}</button>
          <button class="blueBtn" @click="saveChanges" v-show="isEdit">{{ $t('buttons.saveChanges')}}</button>
        </div>
      </div>

      <div class="rightPart">
        <div class="tableTitle">{{flatData.tableTitle}}</div>
        <table v-bind:id="flatType+'Table'">
          <thead>
          <tr>
            <th>№ п/п</th>
            <th>{{ $t('objectRegistration2.totalArea')}} {{ $t('objectRegistration2.m2')}}</th>
            <th>{{ $t('objectRegistration2.heatedArea')}} {{ $t('objectRegistration2.m2')}}</th>
            <th class="editInputTd">{{ $t('objectRegistration2.document')}}</th>
            <th class="deleteTh" v-if="isEdit">
              <button class="redBtn" @click="deleteScan">{{ $t('buttons.delete')}}</button>
            </th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="value in flatTypes">
            <td>{{ $t('objectRegistration2.type')}} {{value.typeShort}}</td>
            <td class="editInputTd">

              <input type="text" v-if="isEdit"
                     v-model=value.totalArea
                     class="editInput"
                     @keydown="validate($event, value.typeFull)"
                     @keyup="validateKeyUp"
                     @change="changeValue(value)"
                     placeholder="00000.000">

              <span v-else>{{value.totalArea}}</span>
            </td>
            <td class="editInputTd">

              <input type="text" v-if="isEdit"
                     v-model=value.heatingArea
                     class="editInput"
                     @keydown="validate($event)"
                     @keyup="validateKeyUp"
                     @change="changeValue(value)"
                     placeholder="00000.000">

              <span v-else>{{value.heatingArea}}</span>
            </td>
            <td class="editInputTd" width="200">{{value.scan ? value.scan.name : ''}}</td>
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
    props: ["flatData", "types"],
    data(){
      return{
        flatType: this.flatData.dataTitle,
        visibilityForm: false,
        totalArea: null,
        heatingArea: null,
        scan: null,
        isEdit: false,
        editedData:[],
        greyTd: []
      }
    },

    computed: {
      ...mapGetters(['getIsSaved']),
      flatTypes(){
        if(this.$store.getters.getFlatsData[this.flatData.dataTitle.toLowerCase()].length > 0){
          this.changeIsSaved(true);
        }
        return this.$store.getters.getFlatsData[this.flatData.dataTitle.toLowerCase()]
      },
      typeNumber() {
        return this.flatTypes.length > 0 ? this.flatTypes.length + 1 : 1;
      }
    },

    mounted() {
    },

    methods:{
      ...mapMutations(["addFlatData", "editFlatData","changeIsSaved"]),
      ...mapActions(["saveFlatData"]),

      changeType(){
        this.typeNumber++;
        if(this.typeNumber > 5) this.typeNumber = 1;
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
          var regEx = /^\d{5}.\d{3}$/;
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
              typeFull: "type"+this.typeNumber,
              typeShort: this.typeNumber,
              totalArea: this.totalArea,
              heatingArea: this.heatingArea,
              scan: this.scan,
              buildingId: this.$route.params.id
            };


            this.totalArea = this.heatingArea = "";
            inpFile.value = "";
            this.changeType();
            this.changeIsSaved(true);

            this.saveFlatData(data);
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
        var regEx = /^\d{5}.\d{3}$/;
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
          this.editedData = this.flatTypes[this.flatData.dataTitle.toLowerCase()];
        }
      },

      validate(e){
        if(e.key == "Backspace"|| e.key === ","|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
          return
        } else if(isNaN(e.key) || e.key == " " ||e.target.value.length > 8){
          e.preventDefault();
        }
      },
      validateKeyUp(e){
        if(e.target.value.length == 5){
          if(e.key === "Backspace") return
          e.target.value += ".";
        }
      },
      changeValue(item){
        this.editedData.push(item)
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
  justify-content: center;
  align-items: center;
  font-size: 17px;
}
table{
  width: 600px;
  table-layout: fixed;
}

table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
}

th{
  font-weight: 400;
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
  border: 1px solid #3c3a3a;
  margin-right: 5px;
}

input[type=file]{
  width: 105px;
  border: 1px solid #3c3a3a;
  outline: none;
}

.inputFile{

}

input[type=checkbox]{
  border: 1px solid #3c3a3a;
}
.editInput{
  width: 30px;
  height: auto;
}
.editInputTd{
  width: 25%;
}

.yellowBtn,.greenBtn,.greyBtn,.blueBtn{
  margin-right: 5px;
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
  border: 1px solid #3c3a3a;
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
