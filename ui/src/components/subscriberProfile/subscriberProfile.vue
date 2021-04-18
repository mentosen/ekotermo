<template>
  <div class="subscriberProfile">
    <router-link to="/billingMainPage">
      <button class="home" v-bind:disabled="!isAccounted" v-bind:class="{disabled:!isAccounted}"></button>
    </router-link>
    <div class="title">{{$t('organizationQuestionnaire.subscriberTitle')}}</div>

    <div class="content">
      <div>
        <div class="contentTitle">{{$t('organizationQuestionnaire.initials')}}</div>
        <div class="contentInfo">
          <input type="text" name="name" v-if="data.isEdit" @keydown="textValidate" v-bind:value="data.name">
          <div v-if="!data.isEdit">{{data.name}}</div>
        </div>
      </div>
      <div>
        <div class="contentTitle">{{$t('organizationQuestionnaire.IPN')}}</div>
        <div class="contentInfo">
          <input type="text" name="IPN" v-if="data.isEdit" @keydown="enterLimit" placeholder="1234567890" pattern="^\d{10}$"
                 v-bind:value="data.IPN">
          <div v-if="!data.isEdit">{{data.IPN}}</div>
        </div>
      </div>
      <div>
        <div class="contentTitle">{{$t('organizationQuestionnaire.subscriberAddress')}}</div>
        <div class="contentInfo address">

          <div class="fix">
            <div>{{$t('organizationQuestionnaire.region')}}</div>
            <div>
              <select name="region" v-if="data.isEdit">
                <option v-bind:value="item" v-bind:selected="data.region == item" v-for="item in regionsSP">{{item}}</option>
              </select>
              <div v-if="!data.isEdit">{{data.region}}</div>
            </div>
          </div>
          <div>
            <div>{{$t('organizationQuestionnaire.cityType')}}</div>
            <div>
              <select name="cityType" v-if="data.isEdit">
                <option v-bind:value="item" v-bind:selected="data.cityType == item" v-for="item in cityTypeSP">{{item}}</option>
              </select>
              <div v-if="!data.isEdit">{{data.cityType}}</div>
            </div>
          </div>
          <div>
            <div>{{$t('organizationQuestionnaire.cityTitle')}}</div>
            <div>
              <input type="text" name="city" v-if="data.isEdit"  @keydown="textValidate" v-bind:value="data.city">
              <div v-if="!data.isEdit">{{data.city}}</div>
            </div>
          </div>
          <div>
            <div>
              <select name="streetType" v-if="data.isEdit">
                <option v-bind:value="item" v-bind:selected="data.streetType == item" v-for="item in streetTypeSP">{{item}}</option>
              </select>
              <div v-if="!data.isEdit">{{data.streetType}}</div>
            </div>
            <div>
              <input type="text" name="street" v-if="data.isEdit" v-bind:value="data.street">
              <div v-if="!data.isEdit">{{data.street}}</div>
            </div>
          </div>
          <div>
            <div>{{$t('organizationQuestionnaire.buildingNumber')}}</div>
            <div>
              <input type="text" name="buildingNumber" v-if="data.isEdit" @keydown="enterLimit" v-bind:value="data.buildingNumber">
              <div v-if="!data.isEdit">{{data.buildingNumber}}</div>
            </div>
          </div>
          <div>
            <div>{{$t('organizationQuestionnaire.roomNumber')}}</div>
            <div>
              <input type="text" name="roomNumber" v-if="data.isEdit" @keydown="enterLimit" v-bind:value="data.roomNumber">
              <div v-if="!data.isEdit">{{data.roomNumber}}</div>
            </div>
          </div>

        </div>
      </div>
      <div>
        <div class="contentTitle">{{$t('organizationQuestionnaire.phoneNumber')}}</div>
        <div class="contentInfo">
          <input type="text" name="phoneNumber" v-if="data.isEdit" @keydown="validatePhoneNumber" pattern="^\+\d{12}$"
                 placeholder="+380000000000" v-bind:value="data.phoneNumber">
          <div v-if="!data.isEdit">{{data.phoneNumber}}</div>
        </div>
      </div>
      <div>
        <div class="contentTitle">{{$t('organizationQuestionnaire.email')}}</div>
        <div class="contentInfo">
          <input type="text" name="email" v-if="data.isEdit" placeholder="some@email.net" v-bind:value="data.email">
          <div v-if="!data.isEdit">{{data.email}}</div>
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
import {mapActions,mapGetters} from 'vuex';
export default {
  name: "subscriberProfile",
  computed: mapGetters(["regionsSP","cityTypeSP","streetTypeSP"]),
  mounted() {
    this.getRegionsSP();
    this.getCityTypeSP();
    this.getStreetTypeSP();
  },
  data(){
    return{
      data:{
        isEdit: true
      },
      isAccounted:false
    }
  },
  methods:{
    ...mapActions(["getRegionsSP","getCityTypeSP","getStreetTypeSP","sendDataSP"]),
    save(){
      var result = true;
      var inputs = document.querySelectorAll("input[type=text], select");
      var el;

      for(var k = 0; k < inputs.length; k ++){
        this.data[inputs[k].name] = inputs[k].value;
        if(inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)|| !inputs[k].value){
          inputs[k].style.border = "1px solid red";
          if(!el) el = inputs[k];
          this.removeRedBorder(inputs[k]);
          result = false
        }
      }

      if(!result) el.scrollIntoView({behavior: "smooth", block: "center", inline: "center"});

      if(result){
        this.isAccounted = true;
        this.data.isEdit = false;
        this.sendDataSP(Object.assign({},this.data));
      }
    },
    edit(){
      this.data.isEdit = true;
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
    enterLimit(e){
      var limit;
      if(e.target.name == "IPN"){
        limit=10;
      }else if(e.target.name == "buildingNumber" || e.target.name == "roomNumber"){
        limit = 4;
      }

      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"){
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
  width: 195px;
  outline: none;
}
select{
  width: 204px;
  background-color: #b3e5fc;
}

input[name=name]{
  width: 300px;
}

.subscriberProfile{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.title{
  margin-top: 20px;
  margin-bottom: 20px;
}
.contentInfo{
  margin-left: 50px;
}
.contentInfo>div:first-child{
  width: 250px;
}
.contentInfo>.fix:first-child{
  width: auto;
}
.content>div{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
}


.address>div{
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}
.address>div>div:last-child{
  margin-left: 60px;
}
.address>div>div>div{
  width: 250px;
  text-align: left;
}
.btnPart{
  margin-top: 70px;
}
.disabled{
   opacity: 0.5;
 }
/*/////*/
.yellowBtn,.greyBtn{
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

.greyBtn{
  background-color: #d4d4cb;
  border: 1px solid #757572;
}

.yellowBtn:hover{
  background-color: #ffffa1;
}
.greyBtn:hover{
  background-color: #e6e6de;
}

</style>