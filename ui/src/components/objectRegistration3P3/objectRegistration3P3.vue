<template>
  <div id="registrationForm3">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <Head></Head>
    <afterHead></afterHead>
    <table>
      <caption class="tableCaption">{{ $t('objectRegistration3.tableCaption')}}</caption>
      <thead>
      <th width="75">{{ $t('objectRegistration3.flatNumber')}}</th>
      <th>{{ $t('objectRegistration3.porchNumber') }}</th>
      <th width="110">{{ $t('objectRegistration3.floorNumber')}}</th>
      <th>{{ $t('objectRegistration3.flatType')}}</th>
      <th width="180">{{ $t('objectRegistration3.tableCorrectionFactor')}}</th>
      <th width="255">{{ $t('objectRegistration3.roomType')}}</th>
      <th width="110">{{ $t('objectRegistration3.objectPhoto')}}</th>
      <th width="110">{{ $t('objectRegistration3.action')}}</th>
      </thead>
      <tbody>
      <appTrP3 v-for="(data, key) in getApartments" v-bind:flatNumber="key+1" :key="data.num" v-bind:roomTypeP3="roomTypeP3"></appTrP3>
      </tbody>
    </table>
    <div class="btnPart">
      <router-link to="/objectRegistration2">
        <button class="yellowBtn" @click="toRegForm2">{{ $t('buttons.backToSection2')}}</button>
      </router-link>
      <button class="greenBtn" @click="register" v-bind:disabled="!getIsAllFilled1 || !getIsAllFilled2">{{ $t('buttons.register')}}</button>
    </div>
    <confirmPopUp v-if="getShowPopUp"></confirmPopUp>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import appTrP3 from './appTrP3'
import Head from './head'
import confirmPopUp from './confirmPopUp.vue'
import afterHead from "@/components/objectRegistration3P2/afterHead";

export default {
  name: 'objectRegistration3P3',
  mounted() {
    this.getData();
  },
  data(){
    return{
    }
  },
  computed: mapGetters(["getApartments", "getIsAllFilled1","getIsAllFilled2","getShowPopUp","roomTypeP3"]),
  methods:{
    ...mapActions(["getData",'saveAllFlatDataP2']),
    ...mapMutations(["changeShowPopUp"]),
    toRegForm2(){

    },
    register(){
      this.changeShowPopUp(true);
      //
    }
  } ,
  components:{Head: Head, appTrP3: appTrP3, confirmPopUp:confirmPopUp,afterHead:afterHead}

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

#registrationForm3{
  display: flex;
  flex-direction: column;
  align-items: center;
}

table{
  width: 1100px;
  margin-top: 50px;
  table-layout: fixed;
}

table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
}
th{
  font-weight: 400;
}
.tableCaption{
  margin-bottom: 5px;
}

.btnPart{
  margin-top: 50px;
}
.greenBtn,.yellowBtn{
  width: 200px;
  height: 35px;
  padding: 3px;
  border: 1px solid #1bd400;
  background-color: #13f113;
  border-radius:3px;
  font-size: 15px;
  cursor: pointer;
  outline: none;
  margin-left: 10px;
}
.yellowBtn{
  background-color: yellow;
  border: 1px solid #f9ed17;
}
.greenBtn:hover{
  background-color: #a7fda7;
}
.yellowBtn:hover{
  background-color: #ffffa1;
}

</style>
