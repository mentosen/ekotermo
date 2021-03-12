<template>
  <div class="organizationQuestionnaire">
    <router-link to="/billingMainPage">
      <button class="home" v-bind:disabled="!data.isAccounted" v-bind:class="{disabled:!dataOQ.isAccounted}"></button>
    </router-link>

    <div class="title">{{$t('organizationQuestionnaire.mainTitle')}}</div>
    <div class="head">
      <div>
        <div>{{$t('organizationQuestionnaire.companyTitle')}}</div>
        <input type="text" name="companyTitle" v-if="data.isEdit" v-bind:value="data.companyTitle"
        @keydown="textValidate" @input="onInput">
        <div v-if="!data.isEdit">{{data.companyTitle}}</div>
      </div>
      <div>
        <div>{{$t('organizationQuestionnaire.companyCode')}}</div>
        <input type="text" name="companyCode" pattern="^\d{10}$" v-if="data.isEdit" v-bind:value="data.companyCode"
        @keydown="enterLimit" placeholder="1234567890" @input="onInput">
        <div v-if="!data.isEdit">{{data.companyCode}}</div>
      </div>
    </div>

      <div class="legalAddress" v-bind:class="{noBorder:data.isEdit}">
        <div class="legalLeft">
          <span>{{$t('organizationQuestionnaire.legalAddress')}}</span>
        </div>
        <div class="legalRight">
          <div>
            <span>{{$t('organizationQuestionnaire.region')}}</span>
            <select name="legalRegion" v-if="data.isEdit">
              <option v-bind:value="item" v-for="item in regionsOQ" v-bind:selected="data.legalRegion == item">{{item}}</option>
            </select>
            <span v-if="!data.isEdit">{{data.legalRegion}}</span>
          </div>
          <div>
            <span>{{$t('organizationQuestionnaire.cityType')}}</span>
            <select name="legalTypeSettlement" v-if="data.isEdit">
              <option v-bind:value="item" v-for="item in citiesTypeOQ" v-bind:selected="data.legalTypeSettlement == item">{{item}}</option>
            </select>
            <span v-if="!data.isEdit">{{data.legalTypeSettlement}}</span>
          </div>
          <div>
            <span>{{$t('organizationQuestionnaire.cityTitle')}}</span>
            <input type="text" name="legalCity" pattern="\w" v-if="data.isEdit" v-bind:value="data.legalCity"
            @keydown="textValidate"  @input="onInput">
            <span v-if="!data.isEdit">{{data.legalCity}}</span>
          </div>
          <div>
            <select name="legalTypeStreet" v-if="data.isEdit">
              <option v-bind:value="item" v-for="item in streetTypeOQ" v-bind:selected="data.legalTypeStreet == item">{{item}}</option>
            </select>
            <span v-if="!data.isEdit">{{data.legalTypeStreet}}</span>
            <input type="text" name="legalStreet"  pattern="\w" v-if="data.isEdit" v-bind:value="data.legalStreet"
                   @keydown="textValidate" @input="onInput">
            <span v-if="!data.isEdit">{{data.legalStreet}}</span>
          </div>
          <div>
            <span>{{$t('organizationQuestionnaire.buildingNumber')}}</span>
            <input type="text" name="legalBuildingNumber" pattern="^\d{1,4}$" v-if="data.isEdit" v-bind:value="data.legalBuildingNumber"
            @keydown="enterLimit" placeholder="1234" @input="onInput">
            <span v-if="!data.isEdit">{{data.legalBuildingNumber}}</span>
          </div>
          <div>
            <span>{{$t('organizationQuestionnaire.roomNumber')}}</span>
            <input type="text" name="legalRoomNumber" pattern="^\d{1,4}$" v-if="data.isEdit" v-bind:value="data.legalRoomNumber"
            @keydown="enterLimit" placeholder="1234" @input="onInput">
            <span v-if="!data.isEdit">{{data.legalRoomNumber}}</span>
          </div>
        </div>
      </div>

    <div class="addressesMatch" v-if="data.isEdit">
      <input type="checkbox" @click="chbClick" v-bind:checked="data.addressMatch">
      <span>{{$t('organizationQuestionnaire.match')}}</span>
    </div>

    <div class="legalAddress" v-if="!data.addressMatch">
      <div class="legalLeft">
        <span>{{$t('organizationQuestionnaire.postalAddress')}}</span>
      </div>
      <div class="legalRight">
        <div>
          <span>{{$t('organizationQuestionnaire.region')}}</span>
          <select name="postalRegion" v-if="data.isEdit">
            <option v-bind:value="item" v-for="item in regionsOQ" v-bind:selected="data.postalRegion == item">{{item}}</option>
          </select>
          <span v-if="!data.isEdit">{{data.postalRegion}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.cityType')}}</span>
          <select name="postalTypeSettlement" v-if="data.isEdit">
            <option v-bind:value="item" v-for="item in citiesTypeOQ" v-bind:selected="data.postalTypeSettlement == item">{{item}}</option>
          </select>
          <span v-if="!data.isEdit">{{data.postalTypeSettlement}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.cityTitle')}}</span>
          <input type="text" name="postalCity" pattern="\w" v-if="data.isEdit" v-bind:value="data.postalCity"
                 @keydown="textValidate" @input="onInput">
          <span v-if="!data.isEdit">{{data.postalCity}}</span>
        </div>
        <div>
          <select name="postalTypeStreet" v-if="data.isEdit">
            <option v-bind:value="item" v-for="item in streetTypeOQ"  v-bind:selected="data.postalTypeStreet == item">{{item}}</option>
          </select>
          <span v-if="!data.isEdit">{{data.postalTypeStreet}}</span>
          <input type="text" name="postalStreet" pattern="\w" v-if="data.isEdit" v-bind:value="data.postalStreet"
                 @keydown="textValidate" @input="onInput">
          <span v-if="!data.isEdit">{{data.postalStreet}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.buildingNumber')}}</span>
          <input type="text" name="postalBuildingNumber" pattern="^\d{1,4}$" v-if="data.isEdit" v-bind:value="data.postalBuildingNumber"
          @keydown="enterLimit"  placeholder="1234" @input="onInput">
          <span v-if="!data.isEdit">{{data.postalBuildingNumber}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.roomNumber')}}</span>
          <input type="text" name="postalRoomNumber" pattern="^\d{1,4}$" v-if="data.isEdit" v-bind:value="data.postalRoomNumber"
          @keydown="enterLimit"  placeholder="1234" @input="onInput">
          <span v-if="!data.isEdit">{{data.postalRoomNumber}}</span>
        </div>
      </div>
    </div>
    <div class="guidance">
      <div class="leftGuidance">
        <span>{{$t('organizationQuestionnaire.guide')}}</span>
      </div>
      <div class="rightGuidance">
        <div>
          <span>{{$t('organizationQuestionnaire.position')}}</span>
          <input type="text" name="position" pattern="\w" v-if="data.isEdit" v-bind:value="data.position" @keydown="textValidate"
                 @input="onInput">
          <span v-if="!data.isEdit">{{data.position}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.initials')}}</span>
          <input type="text" name="initials" pattern="\w" v-if="data.isEdit" v-bind:value="data.initials" @keydown="textValidate"
                 @input="onInput">
          <span v-if="!data.isEdit">{{data.initials}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.phoneNumber')}}</span>
          <input type="text" name="phoneNumber" pattern="^\+\d{12}$" v-if="data.isEdit" v-bind:value="data.phoneNumber"
          @keydown="validatePhoneNumber"  placeholder="+380123456789" @input="onInput">
          <span v-if="!data.isEdit">{{data.phoneNumber}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.email')}}</span>
          <input type="text" name="email" pattern="\w" v-if="data.isEdit" v-bind:value="data.email" placeholder="some@email.net"
                 @input="onInput">
          <span v-if="!data.isEdit">{{data.email}}</span>
        </div>
      </div>
    </div>

    <div class="bankDetails">
      <div class="bankLeft">
        <span>{{$t('organizationQuestionnaire.bankInfo')}}</span>
      </div>
      <div class="bankRight">
        <div>
          <span>{{$t('organizationQuestionnaire.bankTitle')}}</span>
          <input type="text" name="bankTitle" v-if="data.isEdit" v-bind:value="data.bankTitle" @keydown="textValidate" @input="onInput">
          <span v-if="!data.isEdit">{{data.bankTitle}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.bankCode')}}</span>
          <input type="text" name="bankCode" pattern="^\d{6}$" v-if="data.isEdit" v-bind:value="data.bankCode"
          @keydown="enterLimit" placeholder="123456" @input="onInput">
          <span v-if="!data.isEdit">{{data.bankCode}}</span>
        </div>
        <div>
          <span>{{$t('organizationQuestionnaire.iban')}}</span>
          <input type="text" name="iban" pattern="^\w[a-z,A-Z]{1}\d{27}$" v-if="data.isEdit" v-bind:value="data.iban"
          @keydown="enterLimit" placeholder="UA27-digit" @input="onInput">
          <span v-if="!data.isEdit">{{data.iban}}</span>
        </div>
      </div>
    </div>
    
    <div class="btnPart">
      <button class="yellowBtn" @click="save" v-bind:disabled="!data.isEdit">{{$t('buttons.save')}}</button>
      <button class="greyBtn" @click="edit" v-bind:disabled="data.isEdit">{{$t('buttons.edit')}}</button>
    </div>
    
  </div>
</template>

<script>
import {mapGetters, mapActions} from "vuex"
export default {
  name: "organizationQuestionnaire",
  mounted() {
    this.getRegionsOQ();
    this.getCitiesTypeOQ();
    this.getStreetTypeOQ();

    if(this.dataOQ.companyTitle){
      this.data = this.dataOQ;
    }
  },
  computed:mapGetters(["regionsOQ","citiesTypeOQ","streetTypeOQ","dataOQ"]),
  data(){
    return{
      data:{
        isEdit: true,
        addressMatch: false,
        phoneNumber:"+380",
        iban:"UA",
        isAccounted: false
      }
    }
  },
  methods:{
    ...mapActions(["getRegionsOQ","getCitiesTypeOQ","getStreetTypeOQ","saveDataOQ"]),
    save(){
      var result = true;
      var inputs = document.querySelectorAll("input[type=text], select");
      var yPos;

      for(var k = 0; k < inputs.length; k ++){
        this.data[inputs[k].name] = inputs[k].value;
        if(inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)|| !inputs[k].value){
          inputs[k].style.border = "1px solid red";
          if(!yPos) yPos = inputs[k].offsetTop;
          this.removeRedBorder(inputs[k]);
          result = false
        }
      }
      if(this.data.addressMatch){
        this.data.postalRegion = this.data.legalRegion;
        this.data.postalTypeSettlement = this.data.legalTypeSettlement;
        this.data.postalCity = this.data.legalCity;
        this.data.postalTypeStreet = this.data.legalTypeStreet;
        this.data.postalStreet = this.data.legalStreet;
        this.data.postalBuildingNumber = this.data.legalBuildingNumber;
        this.data.postalRoomNumber = this.data.legalRoomNumber;
      }
      if(!result) window.scrollTo(0,yPos-100);

      if(result){
        this.data.isAccounted = true;
        this.data.isEdit = false;
        this.saveDataOQ(Object.assign({},this.data));
      }

    },
    edit(){
      this.data.isEdit = true;
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    chbClick(e){
      if(e.target.checked){
        this.data.addressMatch = true;
      }else{
        this.data.addressMatch = false;
      }
    },
    enterLimit(e){
      var limit;
      if(e.target.name == "companyCode"){
        limit=10;
      }else if(e.target.name == "legalBuildingNumber" || e.target.name == "postalBuildingNumber"||
               e.target.name == "legalRoomNumber" || e.target.name == "postalRoomNumber"){
        limit = 4;
      }else if(e.target.name == "bankCode"){
        limit = 6;
      }else if(e.target.name =="iban"){
        limit = 29;
      }

      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >= limit){
        e.preventDefault();
      }
    },
    textValidate(e){
      if(e.target.name === "personPosition" ||e.target.name == "street" && e.key === " ") return
      if(e.target.name === "firstName" && e.key == "-") return
      if(e.key == "Backspace"|| e.key === "ArrowLeft" || e.key === "ArrowRight"||e.key == " "){
        return
      } else if(!isNaN(e.key) ||e.key =="+"||e.key =="?"||e.key =="."||e.key ==","||e.key =="_"
          ||e.key =="/"||e.key ==">"||e.key =="<"||e.key =="%"||e.key =="&"||e.key =="*"||e.key =="#"||e.key =="@"||e.key =="!"
          ||e.key =="№"||e.key ==";"||e.key ==":"||e.key ==")"||e.key =="("||e.key =="₴"||e.key =="~"){
        e.preventDefault();
      }
    },
    validatePhoneNumber(e){
      if(e.key == "Backspace" || e.key === "+"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length >=13){
        e.preventDefault();
      }
    },
    onInput(e){
      this.data[e.target.name] = e.target.value;
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
  input, select{
    border: 1px solid #3c3a3a;
    padding: 2px;
    font-size: 17px;
    border-radius: 2px;
    padding-left: 5px;
    width: 160px;
    outline: none;
  }
  select{
    width: 169px;
    background-color: #b3e5fc;
  }
  input[type=checkbox]{
    width: 20px;
    height: 20px;
    margin-right: 10px;
  }

.organizationQuestionnaire{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  align-content: center;
}
.title{
  text-align: center;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 18px;
}
.head{
  width: 400px;
}
.head>div{
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.legalAddress,.guidance,.bankDetails{
  display: flex;
  width: 600px;
  margin-top: 30px;
  padding-bottom: 10px;
  border-bottom: 1px solid #3c3a3a;
}
  .legalAddress:first-child{
    border-bottom: none;
  }
.legalLeft,.leftGuidance,.bankLeft{
  width: 200px;
  padding-top: 5px;
}
.legalLeft>span,.leftGuidance>span,.bankLeft>span{
  font-size: 18px;
}

.legalRight>div,.rightGuidance>div,.bankRight>div{
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  width: 400px;
  align-items: center;
}
.addressesMatch{
  margin-top: 25px;
  width: 600px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #3c3a3a;
  border-top: 1px solid #3c3a3a;
  padding: 10px 0 10px 0;
}

.btnPart{
  margin-top: 50px;
  margin-bottom: 50px;
}
.disabled{
  opacity: 0.5;
}
.noBorder{
  border-bottom: none;
}
input[name=iban],input[name=bankTitle]{
  width: 280px;
}

/*///*/
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
</style>