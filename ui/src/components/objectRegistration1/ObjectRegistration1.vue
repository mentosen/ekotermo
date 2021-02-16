<template>
  <div id="registrationForm1">
    <div class="headHeading">Регестрация объекта<br>раздел №1</div>
    <div class="address">
      <div class="addressTitle">Адрес объекта</div>
      <div class="addressInfo">
        <div class="addressData">
          <span>Область:</span>
          <select name="region" class="required" v-if="isEdit">
            <option v-bind:value="data.value" v-for="data in getRegions" v-bind:selected="getFlatInfo.region == data.value">{{data.title}}</option>
          </select>
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.region}}</div>
        </div>
        <div class="addressData">
          <span>Район:</span>
          <input type="text" @keydown="textValidate" name="area"  v-if="isEdit" v-bind:value="getFlatInfo.area">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.area}}</div>
        </div>
        <div class="addressData">
          <span>Город:</span>
          <select name="cities" class="required" v-if="isEdit">
            <option v-bind:value="data.value" v-for="data in getCities" v-bind:selected="getFlatInfo.cities == data.value">{{data.title}}</option>
          </select>
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.cities}}</div>
        </div>
        <div class="addressData">
          <span>Улица:</span>
          <input type="text" class="required" @keydown="textValidate" name="street"  v-if="isEdit" v-bind:value="getFlatInfo.street">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.street}}</div>
        </div>
        <div class="addressData">
          <span>№ дома:</span>
          <input type="text" class="required" @keydown="validate" name="buildingNumber" v-if="isEdit" v-bind:value="getFlatInfo.buildingNumber">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.buildingNumber}}</div>
        </div>
      </div>
    </div>
<!--    //-->
    <div class="parameters">
      <div class="parametersTitle">Характеристика объекта</div>
      <div class="parametersInfo">

        <div class="leftSide">
          <div class="categoryTitle">Категория дома:</div>
          <div class="category">
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 2</div>
                <div>В здании помещения всех потребителей в здании оборудованы  узлами распределительного учета (счетчиками тепла)"</div>
              </div>
              <input type="checkbox" value="2" @change="changeChbChecked"  name="buildingCategory">
            </div>
            <div>
              <div><div class="categoryItemTitle">Раздел II пункт 3</div>
                <div>В здании помещения не оборудованы или частично оборудованы узлами раздельного учета тепловой энергии/ приборами
                  распределителями тепловой энергии/ узлами распределительного учета расхода теплоносителя
                  ( в случае учета тепловой энергии в гарячей воде)</div>
              </div>
              <input type="checkbox" value="3" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 3 подпункт 1</div>
                <div>Для дома в котором отсутствуют  помещения с индивидуальным отоплением и/или неотапливаемые помещения</div>
              </div>
              <input type="checkbox" value="3.1" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 3 подпункт 2</div>
                <div>Для дома в котором часть помещений с индивидуальным отоплением и/или неотапливаемые помещения</div>
              </div>
              <input type="checkbox" value="3.2" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 4</div>
                <div>В здании часть помещений   оборудованы узлами раздельного учета тепловой энергии,
                  а остальные помещения не оборудованы узлами  раздельного учета тепловой энергии/приборами
                  распределителями тепловой энергии/ узлами распределительного учета расхода теплоносителя
                  ( в случае учета тепловой энергии в гарячей воде) и присутствуют помещения с индивидуальным отоплением и/или неотапливаемые помещения</div>
              </div>
              <input type="checkbox" value="4" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 5</div>
                <div>В здании 100% самостоятельных объектов недвижемого имущества оборудованыузлами
                  распределительного учета расхода теплоносителя (приборами учета гарячей воды)</div>
              </div>
              <input type="checkbox" value="5" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div>
              <div>
                <div class="categoryItemTitle">Раздел II пункт 6</div>
                <div> В здании отопительные приборы которого оборудованы приборами распределителями тепловой энергии</div>
              </div>
              <input type="checkbox" value="6" @change="changeChbChecked" name="buildingCategory">
            </div>
          </div>
        </div>

        <div class="rightSide">
          <div class="buildingTitle">Информация о доме:</div>
          <div class="buildingInfo">
            <div>
              <span>Количество подъездов:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="entranceCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.entranceCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.entranceCount}}</div>
            </div>
            <div>
              <span>Количество квартир:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="flatsCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.flatsCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.flatsCount}}</div>
            </div>
            <div>
              <span>Количество этажей:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="floorsCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.floorsCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.floorsCount}}</div>
            </div>
            <div>
              <div>Нумерация квартир:</div>
              <span>с</span>
              <input type="text" class="required flatNum" @keydown="validate"
                     name="numerableFlatsFrom" v-if="isEdit" v-bind:value="getFlatInfo.numerableFlatsFrom">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.numerableFlatsFrom}}</div>
              <span>по</span>
              <input type="text" class="required flatNum" @keydown="validate"
                     name="numerableFlatsTo" v-if="isEdit" v-bind:value="getFlatInfo.numerableFlatsTo">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.numerableFlatsTo}}</div>
            </div>
            <div>
              <span>Показания общедомового прибора учета тепла:</span>
              <input type="text" class="required"
                     @keydown="validateGeneralCounterValue"
                     @keyup="validateGeneralCounterValueKeyUp"
                     @focus="counterFocus"
                     name="generalCounterValue"
                     value=","
                     placeholder="000000,00" v-if="isEdit"
                     v-bind:value="getFlatInfo.generalCounterValue">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.generalCounterValue}}</div>
            </div>
          </div>

          <div class="person">
            <div class="personTitle">Данные лица ответственного за регестрацию объекта:</div>
            <div class="personInfo">
              <div>
                <span>Фамилия:</span>
                <input type="text" class="required" @keydown="textValidate" name="lastName"
                       v-if="isEdit" v-bind:value="getFlatInfo.lastName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.lastName}}</div>
              </div>
              <div>
                <span>Имя:</span>
                <input type="text" class="required" @keydown="textValidate"
                       name="firstName" v-if="isEdit" v-bind:value="getFlatInfo.firstName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.firstName}}</div>
              </div>
              <div>
                <span>Отчество:</span>
                <input type="text" class="required" @keydown="textValidate"
                       name="thirdName" v-if="isEdit" v-bind:value="getFlatInfo.thirdName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.thirdName}}</div>
              </div>
              <div>
                <span>Должность:</span>
                <input type="text" @keydown="textValidate"  name="personPosition"
                       v-if="isEdit" v-bind:value="getFlatInfo.personPosition">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPosition}}</div>
              </div>
              <div>
                <span>Контактный № тел.:</span>
                <div class="phoneNum">
                  <input type="text"
                         value="+380"
                         class="phoneNumber phone1"
                         placeholder="+380456789123"
                         @keyup="validatePhoneNumberKeyUp"
                         @keydown="validatePhoneNumber"
                         name="personPhoneNum1" v-if="isEdit"
                         v-bind:value="getFlatInfo.personPhoneNum1">
                  <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPhoneNum1}}</div>
                  <input type="text"
                         value="+380"
                         class="phoneNumber phone2"
                         placeholder="+380456789123"
                         @keyup="validatePhoneNumberKeyUp"
                         @keydown="validatePhoneNumber"
                         name="personPhoneNum2" v-if="isEdit"
                         v-bind:value="getFlatInfo.personPhoneNum2">
                  <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPhoneNum2}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
<!--    //-->
    <div class="btnPart">
      <button class="yellowBtn" @click="save" v-bind:disabled="isSaved">Сохранить</button>
      <button class="greyBtn"  @click="edit" v-bind:disabled="!isSaved">Редактировать</button>
      <button class="redBtn" @click="removeBuilding" v-bind:disabled="isEdit">Удалить</button>
      <router-link to="/objectRegistration2">
        <button class="greenBtn" @click="toRegForm2" v-bind:disabled="!isSaved">ПЕРЕЙТИ К РАЗДЕЛУ №2</button>
      </router-link>
    </div>
    <delPopUp v-if="getIsDel"></delPopUp>
  </div>
</template>

<script>
import {mapGetters, mapActions,mapMutations} from 'vuex'
import delPopUp from './deletePopUp.vue'
export default {
  name: 'objectRegistration1',
  mounted() {
    this.getAllRegions();
    this.data = this.getFlatInfo;
    if(this.data.personPhoneNum1 || this.data.personPhoneNum2){
      if(this.data.personPhoneNum1) this.phoneNumbers.phone1 = this.data.personPhoneNum1;
      if(this.data.personPhoneNum2) this.phoneNumbers.phone2 = this.data.personPhoneNum2;
    }
    if(this.getFlatInfo.isEdit === false){
      this.isEdit = this.getFlatInfo.isEdit;
    }
    if(this.getFlatInfo.isSaved){
      this.isSaved = this.getFlatInfo.isSaved;
    }
    this.checkChbs();
  },
  data(){
    return{
      isSaved: false,
      isEdit: true,
      data:{},
      phoneNumbers:{
        phone1:null,
        phone2: null
      },
      chbs:[],
      entranceCount:20,
      entranceLimit:1,
      flatCount:4000,
      flatLimit:3,
      floorsCount:50,
      floorsLimit:1
    }
  },
  computed: mapGetters(["getRegions","getCities","getIsDel","getFlatInfo"]),
  components:{delPopUp: delPopUp},
  methods:{
    ...mapActions(["saveData"]),
    ...mapMutations(["changeIsDel"]),
    ...mapActions(["getAllRegions"]),
    save(){
    var inputs = document.querySelectorAll("input[type=text],input[type=checkbox],select");
    var result1 = this.checkValue(inputs);
    var result2 = this.checkPhoneNumbers();

     if(result1 && result2){
       for(var k = 0; k < inputs.length;k++){
         if(inputs[k].type === "checkbox"){
            if(inputs[k].checked){
              this.data[inputs[k].name] = inputs[k].value;
              inputs[k].style.display = "none";
            }else{
              inputs[k].closest("div").style.display= "none";
            }
         }else{
           this.data[inputs[k].name] = inputs[k].value;
           if(inputs[k].value === "+380")this.data[inputs[k].name] = null;
         }
       }
       this.data.isEdit = false;
       this.data.isSaved = true;
       this.saveData(this.data);
       this.isSaved = true;
       this.isEdit = false;
       window.scrollTo(0,0);
     }
    },
    edit(){
      this.isSaved = false;
      this.isEdit = true;
      var chbs = document.querySelectorAll("input[type=checkbox]");

      for(var k = 0; k < chbs.length;k++){
          chbs[k].closest("div").style.display= "";
          chbs[k].style.display= "";
      }
    },
    checkValue(arr){
      var chb = Array.from(arr).filter(el=>el.type==="checkbox");
      var result = true;
      var checked = false;
      var countChb = 0;

      for(var k = 0; k < arr.length;k++){
        if(arr[k].name === "generalCounterValue"){
          var regEx = /^\d{6},\d{2}$/;
          if(!arr[k].value.match(regEx)){
            result = false;
            arr[k].style.border = "1px solid red";
            this.removeBorder(arr[k]);
            window.scrollTo(0,100);
          }
        }else if(arr[k].className === "required" && !arr[k].value){
          arr[k].style.border = "1px solid red";
          window.scrollTo(0,100);
          result = false;
          this.removeBorder(arr[k]);
        } else if(arr[k].type == "checkbox"){
          if(checked === true) continue;
          if(arr[k].checked === true){
            checked = true;
          }else{
            countChb++;
          }
          if(countChb === 7&&!checked){
            this.addBorderChb(chb);
            window.scrollTo(0,100);
            result = false;
          }
        }else if(arr[k].className == "required flatNum" && !arr[k].value){
          arr[k].style.border = "1px solid red";
          result = false;
          window.scrollTo(0,100);
          this.removeBorder(arr[k]);
        }
      }

      return result
    },
    checkChbs(){
      if(this.data.buildingCategory){
        var chbs = document.querySelectorAll("input[type=checkbox]");
        for(var k = 0; k < chbs.length; k++){
          if(this.data.buildingCategory == chbs[k].value){
            chbs[k].checked = true;
          }
          if(chbs[k].checked){
            chbs[k].style.display = "none";
          }else{
            chbs[k].closest("div").style.display= "none";
          }
        }
      }
    },
    checkPhoneNumbers(){
      var phoneInputs = document.querySelectorAll(".phoneNumber");
      var result = false;
      if(this.phoneNumbers.phone1 || this.phoneNumbers.phone2){
        result = true;
      }else{
        phoneInputs[0].style.border = "1px solid red";
        phoneInputs[1].style.border = "1px solid red";
        this.removeBorder(phoneInputs[0]);
        this.removeBorder(phoneInputs[1]);
      }
      return result
    },
    addBorderChb(arr){
      for(var k = 0; k < arr.length;k++){
        arr[k].closest("div").style.border = "1px solid red";
        this.removeBorder(arr[k].closest("div"));
      }
    },
    removeBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    validate(e){
      if(e.key == "Backspace"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > 3){
        e.preventDefault();
      }
    },
    validateGeneralCounterValue(e){
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key == ","){
        return
      } else if(isNaN(e.key) || e.key == " " ||e.target.value.length === 9){
        e.preventDefault();
        return
      }
    },
    validateGeneralCounterValueKeyUp(e){
      if(e.target.value.length == 6) e.target.value += ",";
    },
    counterFocus(e){
      e.target.selectionStart = 0;
      e.target.selectionEnd = 0;
    },
    validateCount(e){
      var limit;
      if(e.target.name == "entranceCount")limit = this.entranceLimit;
      if(e.target.name == "flatsCount")limit = this.flatLimit;
      if(e.target.name == "floorsCount")limit = this.floorsLimit;

      if(e.key == "Backspace"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > limit){
        e.preventDefault();
      }
    },
    validateCountKeyUp(e){
      var maxCount;
      if(e.target.name == "entranceCount")maxCount = this.entranceCount;
      if(e.target.name == "flatsCount")maxCount = this.flatCount;
      if(e.target.name == "floorsCount")maxCount = this.floorsCount;

      if(parseInt(e.target.value) > maxCount){
        var val = e.target.value.split("");
        val.splice(val.length-1,1);
        e.target.value = val.join("");
      }
    },
    validatePhoneNumber(e){
      if(e.key == "Backspace" || e.key === "+"){
        return
      } else if(isNaN(e.key) || e.key == " "|| e.target.value.length > 12){
        e.preventDefault();
      }
    },
    validatePhoneNumberKeyUp(e){
      var regEx = /^\+\d{12}$/;
      if(e.target.value.match(regEx)){
        if(e.target.classList[1] === "phone1"){
          this.phoneNumbers.phone1 = e.target.value.match(regEx)[0];
        }else{
          this.phoneNumbers.phone2 = e.target.value.match(regEx)[0];
        }
      }else{
        if(e.target.classList[1] === "phone1"){
          this.phoneNumbers.phone1 = null;
        }else{
          this.phoneNumbers.phone2 = null;
        }
      }
    },
    textValidate(e){
      if(e.target.name === "personPosition" && e.key === " ") return
      if(e.target.name === "firstName" && e.key == "-") return
      if(e.key == "Backspace"){
        return
      } else if(!isNaN(e.key) || e.key == " "||e.key =="+"||e.key =="-"||e.key =="?"||e.key =="."||e.key ==","||e.key =="_"
        ||e.key =="/"||e.key ==">"||e.key =="<"||e.key =="%"||e.key =="&"||e.key =="*"||e.key =="#"||e.key =="@"||e.key =="!"
        ||e.key =="№"||e.key ==";"||e.key ==":"||e.key ==")"||e.key =="("||e.key =="₴"||e.key =="~"||e.key =="`"||e.key =="'"
        ||e.key =="'"||e.key =='"'){
        e.preventDefault();
      }
    },
    changeChbChecked(e){
      var chbs = e.target.closest(".category").querySelectorAll("input[type=checkbox]");
      for(var k = 0; k < chbs.length;k++){
        if(chbs[k] === e.target) continue;
        chbs[k].checked = false;
      }
    },
    removeBuilding(){
      this.changeIsDel(true);
    },
    toRegForm2(){

    }
  }
}
</script>

<style scoped>

 #registrationForm1{
   display: flex;
   flex-direction: column;
   align-items: center;
 }
 .btnPart{
   margin-top: 70px;
   margin-bottom: 50px;
 }
 .yellowBtn,.greenBtn,.greyBtn,.redBtn{
   width: 200px;
   height: 36px;
   padding: 5px;
   border: 1px solid #f9ed17;
   border-radius: 3px;
   outline: none;
   text-decoration: none;
   background-color: yellow;
   cursor: pointer;
   margin-right: 20px;
 }
 .redBtn{
   border: 1px solid #f12929;
   background-color: #ea4c4c;
 }
 .greenBtn{
   background-color: #13f113;
   border: 1px solid #1bd400;
 }
 .greyBtn{
   background-color: #d4d4cb;
   border: 1px solid #757572;
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

 /*//*/

 .headHeading{
   text-align: center;
   font-size: 22px;
   margin-top: 20px;
 }
 .address{
   margin-top: 20px;
   padding-bottom: 20px;
   border-bottom: 1px solid #a7a4a4;
 }
 .addressTitle,.parametersTitle{
   text-align: center;
   font-size: 19px;
 }
 .addressInfo{
   display: flex;
   margin-top: 10px;
 }

.addressData{
  width: 260px;
  font-size: 18px;
  display: flex;
  align-items: center;
}
 .addressData input,.addressData select{
   border: 1px solid #ccc;
   padding: 2px;
   font-size: 17px;
   border-radius: 2px;
   padding-left: 5px;
   width: 160px;
   margin-left: 10px;
   outline: none;
 }
 .addressData select{
   cursor:pointer;
 }
 /*//*/
.parameters{
  margin-top: 50px;
}
.parametersInfo{
  display: flex;
  justify-content: space-between;
}
.leftSide{
  width: 600px;
}
.categoryTitle,.buildingTitle{
  text-align: center;
  font-size: 18px;
  margin-top: 5px;
}
.categoryItemTitle{
  text-align: center;
  font-size: 17px;
  margin-bottom: 5px;
  font-weight: 550;
}
.category>div{
  border: 1px solid #a7a4a4;
  margin-top: 20px;
  padding: 10px;
  border-radius: 3px;
  display: flex;
  align-items: center;
}
 .category>div>div{
   width: 570px;
 }
input[type=checkbox]{
   width: 20px;
   height: 20px;
  border: 1px solid #ccc;
 }


.rightSide{
  width: 550px;
  display: flex;
  flex-direction: column;
  align-items:flex-end;
}
 .buildingTitle{
   margin-bottom: 20px;
   text-align: center;
   margin-left: 50px;
   width: 500px;
 }
.buildingInfo,.personInfo{
  border: 1px solid #a7a4a4;
  padding: 10px;
  border-radius: 3px;
  width: 450px;
}
 .buildingInfo>div,.personInfo>div{
   display: flex;
   justify-content: space-between;
   align-items: center;
   margin-top: 10px;
   font-size: 17px;
 }
 .buildingInfo>div:last-child{
   margin-bottom: 10px;
 }

.buildingInfo input,.personInfo input{
  border: 1px solid #ccc;
  padding: 2px;
  font-size: 17px;
  border-radius: 2px;
  padding-left: 5px;
  width: 160px;
  margin-left: 10px;
  outline: none;
}
 .buildingInfo input.flatNum{
  width: 75px;
}


 .person{
   width: 450px;
 }
 .personTitle{
   text-align: center;
   margin-top: 50px;
   font-size: 18px;
 }
 .personInfo{
   align-items: center;
   margin-top: 20px;
   margin-left: -20px;
 }
 .personInfo>div{
   display: flex;
   justify-content: space-between;
 }
 .personInfo>div>span{
   width: 150px;
   margin-right: 20px;
 }
 .phoneNum{
   display: flex;
   flex-direction: column;
   height: 60px;
 }
 .phoneNum input:first-child{
   margin-bottom: 10px;
 }
 .inputDiv{
   margin-left: 15px;
   font-size: 19px;
 }
</style>
