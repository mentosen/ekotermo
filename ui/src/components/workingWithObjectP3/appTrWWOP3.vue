<template>
  <tr>
    <td class="apartmentNumber">{{data.apartmentNumber}}</td>
    <td class="generalArea">{{data.generalArea}}</td>
    <td class="heatedArea">{{data.heatedArea}}</td>
<!--    <td>{{data.previousReading}}</td>-->
<!--    <td>-->
<!--      <div v-if="!isManualInputWWOP2 || !data.isEdit">{{data.currentReading}}</div>-->
<!--      <input type="text" v-bind:value="data.currentReading" v-if="isManualInputWWOP2&&data.isEdit"-->
<!--      @keydown="inputValidate" pattern="^\d{6},\d{2}$" name="currentReading">-->
<!--    </td>-->
    <td class="currentConsumption">{{data.currentConsumption}}</td>
    <td class="premisesConsumption">{{data.premisesConsumption}}</td>
    <td class="sumConsumption">{{data.sumConsumption}}</td>
    <td class="sumMoney">{{data.sumMoney}}</td>
    <td class="errors">
      <div v-for="error in data.errors" style="color: #ea4c4c">{{error}}</div>
    </td>
    <td width="200px">
      <div class="btnPart">
        <button class="blueBtn">{{ $t('buttons.detailedCalculation')}}</button>
        <button class="greenBtn">{{ $t('buttons.downloadCalculation')}}</button>
      </div>
    </td >
<!--    <td width="140px" v-if="isManualInputWWOP2">-->
<!--      <div class="btnPart">-->
<!--        <button class="yellowBtn" v-bind:disabled="!data.isEdit" @click="save">{{ $t('buttons.save')}}</button>-->
<!--        <button class="greyBtn" v-bind:disabled="data.isEdit" @click="edit">{{ $t('buttons.edit')}}</button>-->
<!--      </div>-->
<!--    </td>-->
  </tr>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  props:["tableData"],
  name: "appTrWWO",
  mounted() {
    this.data = Object.assign(this.tableData);
  },
  data(){
    return{
      data:{

      }
    }
  },
  methods:{
    ...mapActions(["saveDataWWOP2"]),
    save(e){
      var inputs = e.target.closest("tr").querySelectorAll("input[type=text]");
      var result = true;

      for(var k = 0; k < inputs.length;k++){
        this.data[inputs[k].name] = inputs[k].value;
        if(inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)|| !inputs[k].value){
          inputs[k].style.border = "1px solid red";
          this.removeRedBorder(inputs[k]);
          result = false;
        }
      }
      if(result){
        this.saveDataWWOP2(Object.assign({},this.data));
        this.data.isEdit = false;
      }
    },
    edit(){
      this.data.isEdit = true;
    },
    inputValidate(e){
      var limit = 9;
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key === ","){
        if( e.target.value.length >= limit && e.key === ",") e.preventDefault();
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >= limit){
        e.preventDefault();
      }
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    }
  }
}
</script>


<style scoped>
*{
  font-size: 15px;
}
table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}
td{
  height: 36px;
}
td:nth-child(9){
  padding: 0;
}
/*td:nth-child(12){*/
/*  padding: 0;*/
/*  border-left:none;*/
/*}*/
td input{
  width: 93px;
  border: 1px solid #3c3a3a;
  padding: 2px;
  font-size: 17px;
  border-radius: 2px;
  padding-left: 5px;
  height: 20px;
  margin-left: 10px;
  margin-right: 5px;
  outline: none;
}

/*////*/
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
</style>