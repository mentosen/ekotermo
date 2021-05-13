<template>
  <div id="registrationForm1">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="headHeading">{{ $t('objectRegistration.objectRegistration')}}<br>{{ $t('objectRegistration.section')}} №1</div>

    <div class="address">
      <div class="addressTitle">{{ $t('objectRegistration.objectAddress')}}</div>

      <div class="addressInfo">
        <div class="addressData">
          <span>{{ $t('common.region')}}:</span>
          <select name="region" class="required region" v-if="isEdit" @change="onRegionChange($event)">
            <option v-bind:value="data.name" v-for="data in regions" v-bind:selected="getFlatInfo.region == data.name">{{data.name}}</option>
          </select>
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.region}}</div>
        </div>

        <div class="addressData">
          <span>{{ $t('common.area')}}:</span>
          <input type="text" @keydown="textValidate" name="area"  v-if="isEdit" v-bind:value="getFlatInfo.area">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.area}}</div>
        </div>

        <div class="addressData">
          <span>{{ $t('registration.city')}}:</span>
          <select name="city" class="required" v-if="isEdit">
            <option v-bind:value="data.name" v-for="data in getCities" v-bind:selected="getFlatInfo.city == data.value">{{data.name}}</option>
          </select>
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.city}}</div>
        </div>

        <div class="addressData">
          <span>{{ $t('registration.street')}}:</span>
          <input type="text" class="required" @keydown="textValidate" name="street"  v-if="isEdit" v-bind:value="getFlatInfo.street">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.street}}</div>
        </div>

        <div class="addressData">
          <span>{{ $t('registration.building')}}:</span>
          <input type="text" class="required" @keydown="validate" name="buildingNumber" v-if="isEdit" v-bind:value="getFlatInfo.buildingNumber">
          <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.buildingNumber}}</div>
        </div>
      </div>
    </div>
<!--    //-->
    <div class="parameters">
      <div class="parametersTitle">{{ $t('objectRegistration.objectInfo')}}</div>
      <div class="parametersInfo">

        <div class="leftSide">
          <div class="categoryTitle">{{ $t('objectRegistration.buildingCategory')}}:</div>
          <div class="category">
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointTwoTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointTwo')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_TWO" @change="changeChbChecked"  name="buildingCategory">
            </div>
            <div class="chbsWrapper">
              <div><div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointThreeTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointThree')}}</div>
              </div>
            </div>
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointThreeSubOneTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointThreeSubOne')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_THREE_SUB_ONE" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointThreeSubTwoTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointThreeSubTwo')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_THREE_SUB_TWO" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointFourTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointFour')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_FOUR" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointFiveTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointFive')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_FIVE" @change="changeChbChecked" name="buildingCategory">
            </div>
            <div class="chbsWrapper">
              <div>
                <div class="categoryItemTitle">{{ $t('objectRegistration.sectionPointSixTitle')}}</div>
                <div>{{ $t('objectRegistration.sectionPointSix')}}</div>
              </div>
              <input type="checkbox" value="SECTION_POINT_SIX" @change="changeChbChecked" name="buildingCategory">
            </div>
          </div>
        </div>

        <div class="rightSide">
          <div class="buildingTitle">{{ $t('objectRegistration.buildingInfoTitle')}}:</div>
          <div class="buildingInfo">
            <div>
              <span>{{ $t('objectRegistration.porchNumber')}}:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="entranceCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.entranceCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.entranceCount}}</div>
            </div>
            <div>
              <span>{{ $t('objectRegistration.flatNumber')}}:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="flatsCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.flatsCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.flatsCount}}</div>
            </div>
            <div>
              <span>{{ $t('objectRegistration.floorNumber')}}:</span>
              <input type="text" class="required" @keydown="validateCount"
                     @keyup="validateCountKeyUp" name="floorsCount"
                     v-if="isEdit" v-bind:value="getFlatInfo.floorsCount">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.floorsCount}}</div>
            </div>
            <div>
              <div>{{ $t('objectRegistration.flatNumeration')}}</div>
              <span>{{ $t('common.from')}}</span>
              <input type="text" class="required flatNum" @keydown="validate"
                     name="numerableFlatsFrom" v-if="isEdit" v-bind:value="getFlatInfo.numerableFlatsFrom">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.numerableFlatsFrom}}</div>
              <span>{{ $t('common.to')}}</span>
              <input type="text" class="required flatNum" @keydown="validate"
                     name="numerableFlatsTo" v-if="isEdit" v-bind:value="getFlatInfo.numerableFlatsTo">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.numerableFlatsTo}}</div>
            </div>
            <div>
              <span>{{ $t('objectRegistration.buildingInfo')}}:</span>
              <input type="text" class="required"
                     @keydown="validateGeneralCounterValue"
                     @keyup="validateGeneralCounterValueKeyUp"
                     name="generalCounterValue"
                     value=","
                     placeholder="000000.00" v-if="isEdit"
                     v-bind:value="getFlatInfo.generalCounterValue">
              <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.generalCounterValue}}</div>
            </div>
          </div>

          <div class="person">
            <div class="personTitle">{{ $t('objectRegistration.buildingOwnerInfo')}}:</div>
            <div class="personInfo">
              <div>
                <span>{{ $t('common.surname')}}:</span>
                <input type="text" class="required" @keydown="textValidate" name="lastName"
                       v-if="isEdit" v-bind:value="getFlatInfo.lastName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.lastName}}</div>
              </div>
              <div>
                <span>{{ $t('common.name')}}:</span>
                <input type="text" class="required" @keydown="textValidate"
                       name="firstName" v-if="isEdit" v-bind:value="getFlatInfo.firstName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.firstName}}</div>
              </div>
              <div>
                <span>{{ $t('common.patronymic')}}:</span>
                <input type="text" class="required" @keydown="textValidate"
                       name="thirdName" v-if="isEdit" v-bind:value="getFlatInfo.thirdName">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.thirdName}}</div>
              </div>
              <div>
                <span>{{ $t('common.position')}}:</span>
                <input type="text" @keydown="textValidate"  name="personPosition"
                       v-if="isEdit" v-bind:value="getFlatInfo.personPosition">
                <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPosition}}</div>
              </div>
              <div>
                <span>{{ $t('common.phoneNumber')}}:</span>
                <div class="phoneNum">
                  <input type="text"
                         value="+380"
                         class="phoneNumber phone1"
                         placeholder="+380456789123"
                         @keyup="validatePhoneNumberKeyUp"
                         @keydown="validatePhoneNumber"
                         name="personPhoneNumFirst" v-if="isEdit"
                         v-bind:value="getFlatInfo.personPhoneNumFirst">
                  <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPhoneNumFirst}}</div>
                  <input type="text"
                         value="+380"
                         class="phoneNumber phone2"
                         placeholder="+380456789123"
                         @keyup="validatePhoneNumberKeyUp"
                         @keydown="validatePhoneNumber"
                         name="personPhoneNumSecond" v-if="isEdit"
                         v-bind:value="getFlatInfo.personPhoneNumSecond">
                  <div v-if="!isEdit" class="inputDiv">{{getFlatInfo.personPhoneNumSecond}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
<!--    //-->
    <div class="btnPart">
      <button class="yellowBtn" @click="save" v-bind:disabled="isSaved">{{ $t('buttons.save')}}</button>
      <button class="greyBtn"  @click="edit" v-bind:disabled="!isSaved">{{ $t('buttons.edit')}}</button>
      <button class="redBtn" @click="removeBuilding" v-bind:disabled="isEdit">{{ $t('buttons.delete')}}</button>
      <router-link to="/objectRegistration2">
        <button class="greenBtn" @click="toRegForm2" v-bind:disabled="!isSaved">{{ $t('buttons.toSection2')}}</button>
      </router-link>
    </div>
    <delPopUp v-if="getIsDel"></delPopUp>
  </div>
</template>

<script>
import {mapActions,mapMutations} from 'vuex'
import delPopUp from './deletePopUp.vue'
export default {
  name: 'objectRegistration1',
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
  created() {
    this.getAllRegions();
  },
  mounted() {
    this.data = this.getFlatInfo;
    if(this.data.personPhoneNumFirst || this.data.personPhoneNumSecond){
      if(this.data.personPhoneNumFirst) this.phoneNumbers.phone1 = this.data.personPhoneNumFirst;
      if(this.data.personPhoneNumSecond) this.phoneNumbers.phone2 = this.data.personPhoneNumSecond;
    }
    if(this.getFlatInfo.isEdit === false){
      this.isEdit = this.getFlatInfo.isEdit;
    }
    if(this.getFlatInfo.isSaved){
      this.isSaved = this.getFlatInfo.isSaved;
    }
    this.checkChbs();
  },
  computed: {
    getCities() {
      return this.$store.getters.getCities
    },
    getIsDel() {
      return this.$store.getters.getIsDel
    },
    getFlatInfo() {
      return this.$store.getters.getFlatInfo
    },
    regions() {
      return this.$store.getters.getRegions
    },

  },
  components:{delPopUp: delPopUp},
  methods:{
    ...mapActions(["saveData"]),
    ...mapMutations(["changeIsDel","changeSection3Url"]),
    ...mapActions(["getAllRegions"]),

    save(){
    var inputs = document.querySelectorAll("input[type=text],input[type=checkbox],select");
    var chbsWrap = document.querySelectorAll(".chbsWrapper");
    var chb;
    var result1 = this.checkValue(inputs);
    var result2 = this.checkPhoneNumbers();

     if(result1 && result2){
       for(var j = 0; j < chbsWrap.length; j++){
         chb = chbsWrap[j].querySelector("input[type=checkbox]");

         if(chb){
           if(chb.checked){
             this.data[chb.name] = chb.value;
             chb.style.display = "none";
           }else{
             chbsWrap[j].style.display= "none";
           }
         }else{
           chbsWrap[j].style.display= "none";
         }
       }

       for(var k = 0; k < inputs.length;k++){
         if(inputs[k].type == "checkbox"){
           continue;
         }
         this.data[inputs[k].name] = inputs[k].value;
         if(inputs[k].value === "+380")this.data[inputs[k].name] = null;
       }
       this.data.isEdit = false;
       this.data.isSaved = true;
       this.saveData(Object.assign({},this.data));
       this.isSaved = true;
       this.isEdit = false;
       console.log(this.data);
     }
    },
    edit(){
      this.isSaved = false;
      this.isEdit = true;
      var chbsWrap = document.querySelectorAll(".chbsWrapper");

      for(var k = 0; k < chbsWrap.length;k++){
        if(chbsWrap[k].querySelector("input[type=checkbox]")){
          chbsWrap[k].querySelector("input[type=checkbox]").style.display= "";
        }
        chbsWrap[k].style.display= "";
      }
    },
    checkValue(arr){
      var chb = Array.from(arr).filter(el=>el.type==="checkbox");
      var result = true;
      var checked = false;
      var countChb = 0;

      for(var k = 0; k < arr.length;k++){
        if(arr[k].name === "generalCounterValue"){
          var regEx = /^\d{6}.\d{2}$/;
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
          if(countChb === 6&&!checked){
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
        var chbsWrap = document.querySelectorAll(".chbsWrapper");
        var chb;
        for(var k = 0; k < chbsWrap.length; k++){
          chb = chbsWrap[k].querySelector("input[type=checkbox]");
          if(chb){
            if(this.data.buildingCategory == chb.value){
              chb.checked = true;
              chb.style.display = "none";
            }else{
              chbsWrap[k].style.display= "none";
            }
          }else{
            chbsWrap[k].style.display= "none";
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
        arr[k].closest(".chbsWrapper").style.border = "1px solid red";
        this.removeBorder(arr[k].closest(".chbsWrapper"));
      }
    },
    removeBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    validate(e){
      if(e.key == "Backspace"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > 3){
        e.preventDefault();
      }
    },
    validateGeneralCounterValue(e){
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key == "."){
        return
      } else if(isNaN(e.key) || e.key == " " ||e.target.value.length === 9){
        e.preventDefault();
        return
      }
    },
    validateGeneralCounterValueKeyUp(e){
      if(e.target.value.length == 6){
        if(e.key === "Backspace") return
        e.target.value += ".";
      }
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

      if(e.key == "Backspace"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
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
      if(e.key == "Backspace" || e.key === "+"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
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
      if(e.target.name === "personPosition" ||e.target.name == "street" && e.key === " ") return
      if(e.target.name === "firstName" && e.key == "-") return
      if(e.key == "Backspace"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
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
        if(chbs[k] === e.target){
          if(e.target.value == 6){
            this.changeSection3Url('/objectRegistration3P6');
          }else if(e.target.value == 2){
            this.changeSection3Url('/objectRegistration3P2');
          }else if(e.target.value == 3.1||e.target.value == 3.2){
            this.changeSection3Url('/objectRegistration3P3');
          }
          continue;
        }
        chbs[k].checked = false;
      }
    },
    removeBuilding(){
      this.changeIsDel(true);
    },
    toRegForm2(){

    },


    onRegionChange(event){
      let that = this;
      debugger
      that.getFlatInfo.region = event.target.value;

      let cities = that.$store.getters.getRegions.find(region => region.name === that.getFlatInfo.region).cities;
      that.$store.commit('addCities', cities)

    }
  }
}
</script>

<style scoped>
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
 .greyBtn:hover{
   background-color: #e6e6de;
 }
 .greenBtn:hover{
   background-color: #a7fda7;
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
   border-bottom: 1px solid #3c3a3a;
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
  display: flex;
  align-items: center;
}

.addressData select.region{
  width: 175px;
}


 .addressData input,.addressData select{
   border: 1px solid #3c3a3a;
   padding: 2px;
   font-size: 17px;
   border-radius: 2px;
   padding-left: 5px;
   width: 160px;
   margin-left: 10px;
   outline: none;
 }
 .addressData select{
   background-color: #b3e5fc;
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
  border: 1px solid #3c3a3a;
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
  border: 1px solid #3c3a3a;
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
  border: 1px solid #3c3a3a;
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
  border: 1px solid #3c3a3a;
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
