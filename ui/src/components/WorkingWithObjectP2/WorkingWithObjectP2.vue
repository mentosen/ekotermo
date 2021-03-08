<template>
  <div class="workingWithObject">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="headBtnPart">
      <button class="greenBtn headBtns">{{ $t('buttons.objectDistributionarchive')}}</button>
      <button class="yellowBtn headBtns" @click="showFeedback">{{ $t('buttons.feedback')}}</button>
    </div>

    <AppHead></AppHead>
    <generalMeterReadings></generalMeterReadings>

    <div class="generalPart">
      <div class="generalTitle">{{ $t('workingWithObjectP2.generalIndicators')}}</div>
      <table>
        <tbody>
        <tr>
          <td>{{ $t('workingWithObjectP2.daysOfConsumption')}}</td>
          <td width="82px">140</td>
          <td>{{ $t('workingWithObjectP2.consumptionGcalM2')}}</td>
          <td width="82px">123456,78</td>
          <td width="250px">{{ $t('workingWithObjectP2.generalConsumption')}}</td>
          <td width="82px">123456,78</td>
          <td width="300px">{{ $t('workingWithObjectP2.volumeConsumedEnergy')}}</td>
          <td width="82px">123456,78</td>
        </tr>
        </tbody>
      </table>

<!--      <div class="generalReadings">-->
<!--        <div class="generalInfo">Колличество дней потребления</div>-->
<!--        <div></div>-->
<!--        <div class="generalInfo">Потребление Гкал/м2</div>-->
<!--        <div></div>-->
<!--        <div class="generalInfo">Общее потребление дома за отчетный период Гкал</div>-->
<!--        <div></div>-->
<!--        <div class="generalInfo" style="width: 300px">Объем потребленной тепловой энергии местами общего пользования</div>-->
<!--        <div></div>-->
<!--      </div>-->
    </div>

    <div class="btnPart">
      <label>
        <div class="greenBtn greenBtnLabel"  v-bind:class="{disabled:isFile||isManualInputWWOP2}">{{ $t('buttons.loadData')}}</div>
        <input type="file" v-show="isShowInpFile" accept=".xls,.xlsx" @change="addFile" v-bind:disabled="isFile || isManualInputWWOP2" class="addFileWWO">
      </label>
      <button class="yellowBtn" v-bind:disabled="!isFile || isManualInputWWOP2">{{ $t('buttons.loadedData')}}</button>
      <button class="redBtn" @click="clearFile">{{ $t('buttons.clearLoadedData')}}</button>
      <div class="btnPartDiv">
        <span>{{ $t('buttons.manualInput')}}</span>
        <button class=" redBtn redBtn1" @click="manualInput" v-bind:class="{greenBtn:isManualInputWWOP2}">OFF</button>
      </div>
    </div>

    <table class="distributedTable">
      <caption>{{ $t('workingWithObjectP2.consumptionDistribution')}}</caption>
      <thead>
      <th>{{ $t('workingWithObjectP2.apartmentNumber')}}</th>
      <th>{{ $t('workingWithObjectP2.generalArea')}}</th>
      <th>{{ $t('workingWithObjectP2.heatedArea')}}</th>
      <th>{{ $t('workingWithObjectP2.previousMeterValue')}}</th>
      <th>{{ $t('workingWithObjectP2.currentMeterValue')}}</th>
      <th>{{ $t('workingWithObjectP2.apartmentNeedsConsumption')}}</th>
      <th>{{ $t('workingWithObjectP2.premisesNeedsConsumption')}}</th>
      <th>{{ $t('workingWithObjectP2.sumConsumptionGcal')}}</th>
      <th>{{ $t('workingWithObjectP2.sumConsumptionMoney')}}</th>
      <th>{{ $t('workingWithObjectP2.errors')}}</th>
      <th colspan="2">{{ $t('workingWithObjectP2.action')}}</th>
      </thead>
      <tbody>
        <appTrWWOP2 v-for="item in tableData" v-bind:tableData="item"></appTrWWOP2>
      </tbody>
    </table>

    <button @click="save" class="greenBtn greenBtn1">{{ $t('buttons.saveDistribution')}}</button>
  </div>
</template>

<script>
import {mapGetters,mapMutations,mapActions} from 'vuex'
import AppHead from './head';
import generalMeterReadings from './generalMeterReadings';
import appTrWWOP2 from './appTrWWOP2'
import feedback from "@/components/feedback/feedback";
export default {
  name: "WorkingWithObjectP2",
  computed: mapGetters(["getFileWWOP2","isManualInputWWOP2","isAllFilledWWOP2","dataWWOP2","isShowFeedback"]),
  mounted() {
    this.setHeadBtnPos();
  },
  data(){
    return{
      inpX:null,
      inpY:null,
      data:{
        generalMeterReadings:{}
      },
      isFile:false,
      isShowInpFile: false,
      tableData:[{
        apartmentNumber:1,
        generalArea:100,
        heatedArea:90,
        previousReading:123456.78,
        currentReading:"123456,78",
        currentConsumption:123456.78,
        premisesConsumption:123456.78,
        sumConsumption:123456789,
        sumMoney: 1000,
        errors:["someError1", "someError2"]
      },
        {
          apartmentNumber:2,
          generalArea:100,
          heatedArea:90,
          previousReading:123456.78,
          currentReading:"123456,78",
          currentConsumption:123456.78,
          premisesConsumption:123456.78,
          sumConsumption:123456789,
          sumMoney: 1000,
          errors:["someError1"]
        },
        {
          apartmentNumber:2,
          generalArea:100,
          heatedArea:90,
          previousReading:123456.78,
          currentReading:"123456,78",
          currentConsumption:123456.78,
          premisesConsumption:123456.78,
          sumConsumption:123456789,
          sumMoney: 1000,
          errors:["someError1"]
        }]
    }
  },
  components:{AppHead:AppHead,generalMeterReadings:generalMeterReadings,appTrWWOP2:appTrWWOP2,feedback:feedback},
  methods:{
    ...mapActions(["sendFileWWOP2","saveAllFlatDataWWOP2"]),
    ...mapMutations(["changeIsManualInputWWOP2","changeIsAllFilledWWOP2","addMeterReadingsWWOP2","changeIsShowFeedback"]),
    save(){
      var inputs1 = document.querySelector(".generalMeterReadings").querySelectorAll(".tableInput");
      var inputs2 = document.querySelector(".distributedTable").querySelectorAll("input[type=text]");
      var el = null;
      var result1 = true;
      var result2 = true;

      for(var k = 0; k < inputs1.length; k ++){
        this.data.generalMeterReadings[inputs1[k].name] = inputs1[k].value;
        if(inputs1[k].pattern && !inputs1[k].value.match(inputs1[k].pattern)|| !inputs1[k].value){
          inputs1[k].style.border = "1px solid red";
          if(!el) el = inputs1[k];
          this.removeRedBorder(inputs1[k]);
          result1 = false
        }
      }

      for(var k = 0; k < inputs2.length;k++){
        if(inputs2[k].pattern && !inputs2[k].value.match(inputs2[k].pattern)|| !inputs2[k].value){
          inputs2[k].style.border = "1px solid red";
          if(!el) el = inputs2[k];
          this.removeRedBorder(inputs2[k]);
          result2 = false;
        }
      }

      if(!result1 || ! result2){
        el.scrollIntoView({behavior: "smooth", block: "center", inline: "center"});
      }
      if(result1 && result2){
        this.addMeterReadingsWWOP2(Object.assign({},this.data.generalMeterReadings));
        this.saveAllFlatDataWWOP2(this.dataWWOP2);
      }
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    addFile(e){
      this.sendFileWWOP2(e.target.files[0]);
      this.isFile = true;
    },
    clearFile(){
      var inputFile = document.querySelector(".addFileWWO");
      if(inputFile.value){
        inputFile.value = "";
        this.isFile = false;
      }
    },
    manualInput(e){
      this.changeIsManualInputWWOP2(!this.isManualInputWWOP2);
      if(this.isManualInputWWOP2){
        e.target.innerText = "ON"
      }else{
        e.target.innerText = "OFF"
      }
    },
    setHeadBtnPos(){
      var btn = document.querySelector(".headBtnPart");
      var related = document.querySelector(".appHeadWWOP2");
      this.inpX = related.getBoundingClientRect().left + related.offsetWidth;
      this.inpY = related.getBoundingClientRect().top + (related.offsetHeight/2);

      btn.style.position = "absolute";
      btn.style.left = (this.inpX+75)  + "px";
      btn.style.top = (this.inpY-140) + "px";
    },
    showFeedback(){
      this.changeIsShowFeedback(true);
    }
  }
}
</script>


<style scoped>
*{
  font-size: 15px;
}
.home{
  width: 40px;
  height: 40px;
  background-color: #13f113;
  border: 1px solid #3c3a3a;
  background-image: url("../../assets/icons/home.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: 100% 100%;
  border-radius: 4px;
  cursor: pointer;
  position: fixed;
  top: 90px;
  left: 30px;
  outline: none;
}
.home:hover{
  background-color: #6ace6a;
}
.headBtnPart{
  /*position: relative;*/
  width: 200px;
  /*top: 150px;*/
  /*left: 600px;*/
}
.headBtnPart > button:first-child{
  margin-bottom: 20px;
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
  font-weight: 450;
}
table caption{
  margin-bottom: 10px;
  font-size: 17px;
}
.workingWithObject{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.generalPart{
  margin-top: 20px;
  margin-bottom: 10px;
}
.generalTitle{
  width: 100%;
  text-align: center;
  margin-bottom: 10px;
  font-size: 16px;
}
.generalReadings{
  display: flex;
}
.generalInfo{
  width: 250px;
  margin-right: 20px;
}
.btnPart{
  display: flex;
  height: 30px;
  width: 950px;
  justify-content: space-between;
  margin-top: 30px;
}
.btnPartDiv{
  display: flex;
  align-items: center;
}
.btnPartDiv>span{
  margin-right: 10px;
}
.greenBtn.greenBtnLabel{
  width: 192px;
  height: 22px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.disabled{
  opacity: 0.5;
}
.distributedTable{
  margin-top: 30px;
  font-size: 14px;
}
table.distributedTable th{
  font-weight: 450;
  font-size: 14px;
}
/*////*/
.yellowBtn,.greenBtn,.greenBtn1,.greyBtn,.blueBtn,.redBtn{
   width: 200px;
   height: 30px;
   padding: 3px;
   border: 1px solid #f9ed17;
   background-color: yellow;
   font-size: 15px;
   cursor: pointer;
   outline: none;
   margin-right: 10px;
   border-radius: 3px;
 }
.greenBtn1{
  margin-top: 50px;
  margin-bottom: 20px;
  width: 300px;
}
.redBtn,.redBtn1{
  width: 350px;
  border: 1px solid red;
  background-color: #ea4c4c;
}
.redBtn1{
  width: 50px;
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
.headBtns{
  width: 245px;
}
</style>