<template>
  <div class="workingWithObject">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="headBtnPart">
      <button class="greenBtn headBtns">{{ $t('buttons.objectDistributionarchive')}}</button>
      <button class="yellowBtn headBtns" @click="showFeedback">{{ $t('buttons.feedback')}}</button>

      <table class="generalInfoTable">
        <thead>
        <th colspan="2">{{ $t('workingWithObjectP2.generalInfoTableTh')}}</th>
        </thead>
        <tbody>
        <tr>
          <td>{{ $t('workingWithObjectP2.generalInfoTableTd1')}}</td>
          <td>0,0000</td>
        </tr>
        <tr>
          <td>{{ $t('workingWithObjectP2.generalInfoTableTd2')}}</td>
          <td>0,0000</td>
        </tr>
        <tr>
          <td>{{ $t('workingWithObjectP2.generalInfoTableTd3')}}</td>
          <td>0,0000</td>
        </tr>
        </tbody>
      </table>

      <button class="orangeBtn headBtns" @click="showDownloadPopUp">{{ $t('workingWithObjectP2.downloadReportDistributed')}}</button>
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
      <th width="80px">{{ $t('workingWithObjectP2.apartmentNumber')}}</th>
      <th width="90px">{{ $t('workingWithObjectP2.generalArea')}}</th>
      <th width="90px">{{ $t('workingWithObjectP2.heatedArea')}}</th>
      <th width="200">{{ $t('objectRegistration3.roomPurpose')}}</th>
      <th width="115px">{{ $t('workingWithObjectP2.previousDistributorValue')}}</th>
      <th width="115px">{{ $t('workingWithObjectP2.currentDistributorValue')}}</th>
      <th width="115px">{{ $t('workingWithObjectP2.apartmentNeedsConsumption')}}</th>
      <th width="100px">{{ $t('workingWithObjectP2.premisesNeedsConsumption')}}</th>
      <th width="100px">{{ $t('workingWithObjectP2.sumConsumptionGcal')}}</th>
      <th width="100px">{{ $t('workingWithObjectP2.sumConsumptionMoney')}}</th>
      <th width="90px">{{ $t('workingWithObjectP2.errors')}}</th>
      <th colspan="2">{{ $t('workingWithObjectP2.action')}}</th>
      </thead>
      <tbody>
        <appTrWWOP6 v-for="item in tableData" v-bind:tableData="item"></appTrWWOP6>
      </tbody>
    </table>

    <button @click="save" class="greenBtn greenBtn1">{{ $t('buttons.saveDistribution')}}</button>
    <downloadPopUp v-if="showDownloadPopUpWWO"></downloadPopUp>
  </div>
</template>

<script>
import {mapGetters,mapMutations,mapActions} from 'vuex'
import AppHead from './head';
import generalMeterReadings from './generalMeterReadings';
import appTrWWOP6 from './appTrWWOP6'
import downloadPopUp from "@/components/workingWithObjectP6/downloadPopUp";

export default {
  /// вместо tdableData передавать flatsDataWWOP2
  name: "WorkingWithObjectP6",
  computed: mapGetters(["getFileWWOP2","isManualInputWWOP2","isAllFilledWWOP2","dataWWOP2","isShowFeedback","flatsDataWWOP2","showDownloadPopUpWWO"]),
  mounted() {
    this.setHeadBtnPos();
  },
  data(){
    return{
      btnX:null,
      btnY:null,
      data:{
        generalMeterReadings:{}
      },
      isFile:false,
      isShowInpFile: false,
      tableData:[{
        apartmentNumber:1,
        generalArea:40,
        heatedArea:35,
        roomPurpose:[
          {room:"Кухня",
            previousReading:"123456,78",
            currentReading:"123456,78",
            currentConsumption:"123456,78",
          },
          {room:"Зал №1",
            previousReading:"123456,78",
            currentReading:"123456,78",
            currentConsumption:"123456,78",
          }
        ],
        premisesConsumption:123456.78,
        sumConsumption:123456.78,
        sumMoney:1000,
        errors:["error1","error2"]
      },
        {
          apartmentNumber:2,
          generalArea:50,
          heatedArea:35,
          roomPurpose:[
            {room:"Кухня",
              previousReading:"123456,78",
              currentReading:"123456,78",
              currentConsumption:"123456,78",
            }
          ],
          premisesConsumption:123456.78,
          sumConsumption:123456.78,
          sumMoney:1000,
          errors:["error1"]
        },
      ]
    }
  },
  components:{AppHead:AppHead,generalMeterReadings:generalMeterReadings,appTrWWOP6:appTrWWOP6,downloadPopUp:downloadPopUp},
  methods:{
    ...mapActions(["sendFileWWOP2","saveAllFlatDataWWOP2"]),
    ...mapMutations(["changeIsManualInputWWOP2","changeIsAllFilledWWOP2","addMeterReadingsWWOP2","changeIsShowFeedback","setShowDownloadPopUpWWO"]),
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
      this.btnX = related.getBoundingClientRect().left + related.offsetWidth;
      this.btnY = related.getBoundingClientRect().top + (related.offsetHeight/2);

      btn.style.position = "absolute";
      btn.style.left = (this.btnX+115)  + "px";
      btn.style.top = (this.btnY-347) + "px";
    },
    showFeedback(){
      this.changeIsShowFeedback(true);
    },
    showDownloadPopUp(){
      this.setShowDownloadPopUpWWO(true);
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
th:nth-child(4){
  border-bottom: none;
}
th:nth-child(5){
  border-bottom: none;
}
th:nth-child(6){
  border-bottom: none;
}
th:nth-child(7){
  border-bottom: none;
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
  width: 190px;
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
table.distributedTable{
  width: 1622px;
  margin-top: 30px;
  font-size: 14px;
}
table.distributedTable th{
  font-weight: 450;
  font-size: 14px;
}
table.distributedTable th:last-child{
  width: auto;
}
/*////*/
.yellowBtn,.greenBtn,.greenBtn1,.greyBtn,.blueBtn,.redBtn,.orangeBtn{
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
.orangeBtn{
  background-color: #fdbb43;
  border: 1px solid #ffaa0f;
  font-size: 12px;
  margin-top: 20px;
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
.orangeBtn:hover{
  background-color: #ffcd73;
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
.generalInfoTable{
  width: 245px;
  font-size: 14px;
  margin-top: 98px;
}
.generalInfoTable td,th{
  font-size: 13px;
}
</style>