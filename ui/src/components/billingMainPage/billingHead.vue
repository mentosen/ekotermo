<template>
  <div class="head">
    <div class="headDivsTop">
      <div class="title">Название билинговой компании</div>
      <div class="dropMenu" @mouseover="onMenuOver" @mouseout="onMenuOut">
        <button class="dropMenuItem">{{$t('billingMainPage.additionalFunctions')}}</button>

        <router-link to="/enterCounterValue" class="dropMenuItemRouter">
          <button class="dropMenuItem">{{$t('billingMainPage.enterMeterReadings')}}</button>
        </router-link>
        <router-link to="/organizationQuestionnaire">
          <button class="dropMenuItem">{{$t('billingMainPage.objectQuestionnaireForm')}}</button>
        </router-link>
        <button class="dropMenuItem">{{$t('billingMainPage.invoiceForPayment')}}</button>
        <button class="dropMenuItem">{{$t('billingMainPage.statistics')}}</button>
        <button class="dropMenuItem">{{$t('billingMainPage.normativeDocuments')}}</button>
        <button class="dropMenuItem">{{$t('billingMainPage.feedback')}}</button>
      </div>
    </div>

    <div class="headDivs">
      <div>
        <span>{{$t('billingMainPage.numberOfObjects')}}</span>
        <span class="countSpan"></span>
      </div>
      <div>
        <span>{{$t('billingMainPage.registeredDistributors')}}</span>
        <span class="countSpan"></span>
      </div>
      <div>
        <router-link to="/objectRegistration1">
          <button class="yellowBtn">{{$t('billingMainPage.registerNewObject')}}</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  name: "billingHead",
  data(){
    return{
      top: null,
      left:null
    }
  },
  computed: mapGetters(["billingDataHead"]),
  methods:{
    onMenuOver(e){
      var parent = e.target.closest(".dropMenu");
      !this.top? this.top = e.target.offsetTop: "";
      !this.left? this.left = e.target.offsetLeft: "";

      var clone = document.createElement("div");
      clone.classList.add("clone");
      clone.style.width = "210px";
      clone.style.height = "33px";


      parent.style.position = "absolute";
      parent.style.left = this.left + "px";
      parent.style.top = this.top + "px";
      parent.style.height = "auto";
      parent.parentElement.appendChild(clone);
    },
    onMenuOut(e){
      var parent = e.target.closest(".dropMenu");
      var clone = parent.parentElement.querySelector(".clone");
      parent.parentElement.removeChild(clone);
      parent.style.position = "";
      parent.style.height = "33px";
    }
  }
}
</script>


<style scoped>
  .head{
    width: 1000px;
    padding: 20px 5px 20px 5px;
    border-bottom: 1px solid #3c3a3a;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .headDivsTop{
    display: flex;
    justify-content: space-between;
    width: 100%;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  .title{
    text-align: center;
    font-size: 18px;
    margin-left: 340px
  }
  .headDivs{
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .countSpan{
    margin-left: 10px;
  }
  .dropMenu{
    width: 210px;
    height: 33px;
    overflow: hidden;
    background-color: #f3f3f3;
    border-radius: 4px;
  }
  .dropMenuItem{
    width: 100%;
    height: 35px;
    border:none;
    background: none;
    border-bottom: 2px solid #ccc;
    text-align: center;
    outline: none;
    cursor: pointer;
    box-sizing: border-box;
    font-size: 15px;
  }

  .dropMenuItem:hover{
    background-color: #01beff;
  }

  .dropMenu>.dropMenuItem:hover:first-child{
    background-color: #f3f3f3;
  }

  .yellowBtn,.greenBtn,.greyBtn{
    width: 210px;
    height: 36px;
    padding: 5px;
    border: 1px solid #f9ed17;
    border-radius: 3px;
    outline: none;
    text-decoration: none;
    background-color: yellow;
    cursor: pointer;
  }
  .greenBtn{
    background-color: #13f113;
    border: 1px solid #1bd400;
  }
  .greyBtn{
    background-color: #d4d4cb;
    border: 1px solid #757572;
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
</style>