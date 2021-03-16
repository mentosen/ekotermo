<template>
  <tr>
    <td>{{data.accountingType}}</td>
    <td>{{data.apartmentNumber}}</td>
    <td>{{data.generalArea}}</td>
    <td>{{data.heatedArea}}</td>
    <td colspan="4" v-if="data.roomPurpose">
      <table class="tdTable">
        <tbody>
          <tr v-for="item in data.roomPurpose">
            <td width="200px">{{item.room}}</td>
            <td width="115px">{{item.previousReading}}</td>
            <td width="115px">
              <div v-if="!isManualInputWWOP2 || !data.isEdit">{{item.currentReading}}</div>
              <input type="text" v-bind:value="item.currentReading" v-if="isManualInputWWOP2&&data.isEdit"
                     @keydown="inputValidate" @keyup="inputValidateKeyUp" pattern="^\d{6},\d{2}$" name="currentReading">
            </td>
            <td width="115px">{{item.currentConsumption}}</td>
          </tr>
        </tbody>
      </table>
    </td>
    <td v-if="!data.roomPurpose" class="emptyTd"></td>
    <td v-if="!data.roomPurpose">{{data.previousReading}}</td>
    <td v-if="!data.roomPurpose">
      <div v-if="!isManualInputWWOP2 || !data.isEdit">{{data.currentReading}}</div>
      <input type="text" v-bind:value="data.currentReading" v-if="isManualInputWWOP2&&data.isEdit"
             @keydown="inputValidate" @keyup="inputValidateKeyUp" pattern="^\d{6},\d{2}$" name="currentReading">
    </td>
    <td v-if="!data.roomPurpose">{{data.currentConsumption}}</td>

<!--    <td>{{data.previousReading}}</td>-->
<!--    <td>-->
<!--      <div v-if="!isManualInputWWOP2 || !data.isEdit">{{data.currentReading}}</div>-->
<!--      <input type="text" v-bind:value="data.currentReading" v-if="isManualInputWWOP2&&data.isEdit"-->
<!--      @keydown="inputValidate" @keyup="inputValidateKeyUp" pattern="^\d{6},\d{2}$" name="currentReading">-->
<!--    </td>-->
<!--    <td>{{data.currentConsumption}}</td>-->
    <td>{{data.premisesConsumption}}</td>
    <td>{{data.sumConsumption}}</td>
    <td>{{data.sumMoney}}</td>
    <td>
      <div v-for="error in data.errors" style="color: #ea4c4c">{{error}}</div>
    </td>
    <td width="160px">
        <div class="btnPart">
          <button class="blueBtn">{{ $t('buttons.detailedCalculation')}}</button>
          <button class="greenBtn">{{ $t('buttons.downloadCalculation')}}</button>
        </div>
    </td>
    <td width="120px" v-if="isManualInputWWOP2">
      <div class="btnPart">
        <button class="yellowBtn" v-bind:disabled="!data.isEdit" @click="save">{{ $t('buttons.save')}}</button>
        <button class="greyBtn" v-bind:disabled="data.isEdit" @click="edit">{{ $t('buttons.edit')}}</button>
      </div>
    </td>
  </tr>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
export default {
  props:["tableData"],
  name: "appTrWWO",
  computed: mapGetters(["isManualInputWWOP2"]),
  mounted() {
    this.data = Object.assign({isEdit:true}, this.tableData);
    if(this.data.roomPurpose) this.isP6Mode = true;
  },
  data(){
    return{
      data:{

      },
      isP6Mode: false
    }
  },
  methods:{
    ...mapActions(["saveDataWWOP2"]),
    save(e){
      var inputs = e.target.closest("tr").querySelectorAll("input[type=text]");
      var result = true;

      if(this.isP6Mode){
        for(var k = 0; k < inputs.length;k++){
          this.data.roomPurpose[k][inputs[k].name] = inputs[k].value;
          if(inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)|| !inputs[k].value){
            inputs[k].style.border = "1px solid red";
            this.removeRedBorder(inputs[k]);
            result = false;
          }
        }
      }else{
        for(var k = 0; k < inputs.length;k++){
          this.data[inputs[k].name] = inputs[k].value;
          if(inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)|| !inputs[k].value){
            inputs[k].style.border = "1px solid red";
            this.removeRedBorder(inputs[k]);
            result = false;
          }
        }
      }

      if(result){
        this.data.isEdit = false;
        this.saveDataWWOP2(Object.assign({},this.data));
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
    inputValidateKeyUp(e){
      var limit = 6;
      if(e.target.value.length == limit){
        if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"){
          return
        }else{
          e.target.value += ",";
        }
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
  font-size: 13px;
}
table.tdTable{
  border:none;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
table.tdTable td{
  border:none;
  border-right:1px solid #3c3a3a;
  border-bottom:1px solid #3c3a3a;
}
table.tdTable td:last-child{
  border-right: none;
}
/*table tr table.tdTable:last-child tr:last-child td{*/
/*  border-bottom: none;*/
/*}*/
td{
  height: 36px;
}
td:nth-child(5){
  padding:0px;
  border: none;
}
.tdTable td{
  padding: 3px;
}
.tdTable tr:last-child td{
  border-bottom: none;
}
.tdTable tr:first-child td{
  border-top: 1px solid #3c3a3a;
}
td:nth-child(10){
  padding: 0;
}
td:nth-child(11){
  padding: 0;
  border-left:none;
}
td:nth-child(13){
  padding: 0;
}
td:nth-child(14){
  padding: 0;
  border-left:none;
}
td input{
  width: 90px;
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
td.emptyTd{
  background-image: url("../../assets/icons/emptyTd.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  border-top: 1px solid #3c3a3a;
}
td div{
  font-size: 13px;
}
/*.tdBtns{*/
/*  display: flex;*/
/*}*/
/*.btnPart:nth-child(1){*/
/*  width: 200px;*/
/*}*/
/*.btnPart:nth-child(2){*/
/*  width: 170px;*/
/*}*/

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
  font-size: 13px;
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