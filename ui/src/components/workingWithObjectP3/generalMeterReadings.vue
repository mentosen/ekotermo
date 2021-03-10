<template>
  <div class="generalMeterReadings">
    <table>
      <caption>{{ $t('workingWithObjectP2.generalMeterReadings')}}</caption>
      <tbody>
      <tr>
        <td rowspan="2">{{ $t('workingWithObjectP2.dateReadings')}}</td>
        <td rowspan="2"><input type="text" name="dateReadings" pattern="^\d{2}.\d{2}.\d{4}$" placeholder="dd.mm.yyyy"
        @keydown="dateValidate" class="tableInput" autocomplete @keyup="dateValidateKeyUp"></td>
        <td>{{ $t('workingWithObjectP2.straightVolume')}}</td>
        <td><input type="text" name="straightVolume" pattern="^\d{6},\d{2}$" placeholder="000000,00" class="tableInput"
        @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.m3')}}</td>
        <td>{{ $t('workingWithObjectP2.heatEnergy')}}</td>
        <td><input type="text" name="heatEnergy" placeholder="000000,00" pattern="^\d{6},\d{2}$" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.Gcal')}}</td>
        <td rowspan="2">
          <div style="margin-left: 10px">
            <div class="flex">
              <div>{{ $t('workingWithObjectP2.rate1')}}</div>
              <input type="text" class="tdInput tableInput" name="rate1">
            </div>
            <div class="flex">
              <div>{{ $t('workingWithObjectP2.period')}}</div>
              <div class="tdDiv">
                <div>{{ $t('common.from')}} <input type="text" class="tdInput tableInput" name="rate1From" pattern="^\d{2}.\d{2}.\d{4}$"
                placeholder="dd.mm.yyyy" @keydown="dateValidate" @keyup="dateValidateKeyUp"></div>
                <div>{{ $t('common.to')}} <input type="text" class="tdInput tableInput" name="rate1To" pattern="^\d{2}.\d{2}.\d{4}$"
                placeholder="dd.mm.yyyy" @keydown="dateValidate" @keyup="dateValidateKeyUp"></div>
              </div>
            </div>
          </div>
        </td>
      </tr>
      <tr>
        <td>{{ $t('workingWithObjectP2.backVolume')}}</td>
        <td><input type="text" name="backVolume" pattern="^\d{6},\d{2}$" placeholder="000000,00" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.m3')}}</td>
        <td>{{ $t('workingWithObjectP2.meterTime')}}</td>
        <td><input type="text" name="counterTime" class="tableInput" @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.hour')}}</td>
      </tr>

      <tr>
        <td rowspan="3">{{ $t('workingWithObjectP2.timeReadings')}}</td>
        <td rowspan="3"><input type="text" name="timeReadings" pattern="^\d{2}:\d{2}$" placeholder="hh:mm"
        @keydown="timeValidate" class="tableInput" @keyup="timeValidateKeyUp"></td>
        <td>{{ $t('workingWithObjectP2.coolantVolume')}}</td>
        <td><input type="text" name="coolantVolume" pattern="^\d{6},\d{2}$" placeholder="000000,00" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.m3')}}</td>
        <td class="empty"></td>
        <td class="empty"></td>
        <td class="empty"></td>
        <td rowspan="3">
          <div style="margin-left: 10px">
            <div class="flex">
              <div class="flex">
                <div>{{ $t('workingWithObjectP2.rate2')}}</div>
                <input type="checkbox" class="tableChb" @click="chbClick">
              </div>
              <input type="text" class="tdInput" name="rate2" v-bind:disabled="!rate2Disabled"
              v-bind:class="{tableInput: rate2Disabled}">
            </div>
            <div class="flex">
              <div>{{ $t('workingWithObjectP2.period')}}</div>
              <div class="tdDiv">
                <div>{{ $t('common.from')}} <input type="text" class="tdInput" name="rate2From" pattern="^\d{2}.\d{2}.\d{4}$"
                placeholder="dd.mm.yyyy" @keydown="dateValidate" v-bind:disabled="!rate2Disabled"
                v-bind:class="{tableInput: rate2Disabled}" @keyup="dateValidateKeyUp"></div>

                <div>{{ $t('common.to')}} <input type="text" class="tdInput" name="rate2To" pattern="^\d{2}.\d{2}.\d{4}$"
                placeholder="dd.mm.yyyy" @keydown="dateValidate" v-bind:disabled="!rate2Disabled"
                v-bind:class="{tableInput: rate2Disabled}" @keyup="dateValidateKeyUp"></div>
              </div>
            </div>
          </div>
        </td>
      </tr>
      <tr>
        <td>{{ $t('workingWithObjectP2.duct')}}</td>
        <td><input type="text" name="duct" placeholder="00000,00" pattern="^\d{5},\d{2}$" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.m3Hour')}}</td>
        <td>{{ $t('workingWithObjectP2.power')}}</td>
        <td><input type="text" name="power" pattern="^\d{5},\d{2}$" placeholder="00000,00" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.GcalHour')}}</td>
      </tr>
      <tr>
        <td>{{ $t('workingWithObjectP2.t1')}}</td>
        <td><input type="text" name="t1Straight" pattern="^\d{2},\d{2}$" placeholder="00,00" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.c')}}</td>
        <td>{{ $t('workingWithObjectP2.t2')}}</td>
        <td><input type="text" name="t2Back" pattern="^\d{2},\d{2}$" placeholder="00,00" class="tableInput"
                   @keydown="inputValidate" @keyup="inputValidateKeyUp"></td>
        <td>{{ $t('measurement.c')}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "generalMeterReadings",
  data(){
    return{
      rate2Disabled: false
    }
  },
  methods:{
    chbClick(){
      this.rate2Disabled = !this.rate2Disabled;
    },
    dateValidate(e){
      var limit = 10;
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key === "."){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >= limit){
        e.preventDefault();
      }
    },
    dateValidateKeyUp(e){
      var limit = 10;
      if(e.key == "Backspace") return
      if(e.target.value.length == (limit-5)) e.target.value+= ".";
      if(e.target.value.length == (limit-8)) e.target.value+= ".";
    },
    timeValidate(e){
      var limit = 5;
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key === ":"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >= limit){
        e.preventDefault();
      }
    },
    timeValidateKeyUp(e){
      var limit = 4;
      if(e.key == "Backspace") return
      if(e.target.value.length == (limit-2)) e.target.value+= ":";
    },
    inputValidate(e){
      var limit;

      if(e.target.name == "t1Straight"||e.target.name == "t2Back"){
        limit = 5;
      }else if(e.target.name == "straightVolume"||e.target.name == "coolantVolume"
      ||e.target.name == "backVolume"||e.target.name == "heatEnergy"){
        limit = 9;
      }else if(e.target.name == "duct"||e.target.name == "power"){
        limit = 8;
      }

      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key === ","){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >= limit){
        e.preventDefault();
      }
    },
    inputValidateKeyUp(e){
      var limit;

      if(e.target.name == "t1Straight"||e.target.name == "t2Back"){
        limit = 5;
      }else if(e.target.name == "straightVolume"||e.target.name == "coolantVolume"
          ||e.target.name == "backVolume"||e.target.name == "heatEnergy"){
        limit = 9;
      }else if(e.target.name == "duct"||e.target.name == "power"){
        limit = 8;
      }

      if(e.key == "Backspace") return
      if(e.target.value.length == (limit-3)) e.target.value+= ",";
    }
  }
}
</script>


<style scoped>
*{
  font-size: 14px;
}
table{
  margin-top: 20px;
}
table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}
td.empty{
  border:none;
}
table>caption{
  font-size: 16px;
  margin-bottom: 10px;
}
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
td input[type=checkbox]{
  width: 20px;
  cursor: pointer;
}
.generalMeterReadings{

}
.flex{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
/*.flex>input{*/
/*  margin-right: 5px;*/
/*  width: 50px;*/
/*}*/
.tdDiv{
  width: 145px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  margin-left: 10px;
}
.tdDiv>div{
  width: 145px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.flex input{
  margin-top: 5px;
}
.smallText{
  font-size: 12px;
  width: 20px;
}

</style>