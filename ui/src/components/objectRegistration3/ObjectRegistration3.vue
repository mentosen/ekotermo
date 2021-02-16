<template>
  <div id="registrationForm3">
    <Head></Head>
    <table>
      <thead>
      <th width="75">№ квартиры</th>
      <th>№ подъезда</th>
      <th width="110">№ этажа</th>
      <th>Тип квартиры</th>
      <th width="180">Поправочный коэфициент согласно расположения квартиры (помещения)</th>
      <th width="140">Серийный номер счетчика</th>
      <th width="140">Начальные показания счетчика</th>
      <th width="110">Фото объекта</th>
      <th width="110">Действие</th>
      </thead>
      <tbody>
        <appTr v-for="(data, key) in getApartments" v-bind:flatNumber="key+1" :key="data.num"></appTr>
      </tbody>
    </table>
    <div class="btnPart">
      <router-link to="/objectRegistration2">
        <button class="yellowBtn" @click="toRegForm2">НАЗАД В РАЗДЕЛ №2</button>
      </router-link>
      <button class="greenBtn" @click="register" v-bind:disabled="!getIsAllFilled">Ввести в учет</button>
    </div>
    <confirmPopUp v-if="getShowPopUp"></confirmPopUp>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import appTr from './appTr'
import Head from './head'
import confirmPopUp from './confirmPopUp.vue'
export default {
  name: 'registrationForm3',
  mounted() {
    this.getData();
  },
  data(){
    return{
    }
  },
  computed: mapGetters(["getApartments", "getIsAllFilled","getShowPopUp"]),
  methods:{
    ...mapActions(["getData"]),
    ...mapMutations(["changeShowPopUp"]),
    toRegForm2(){

    },
    register(){
      this.changeShowPopUp(true);
      //
    }
  } ,
  components:{Head: Head, appTr: appTr, confirmPopUp:confirmPopUp}

}
</script>

<style scoped>

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
   border: 1px solid #969393;
   border-collapse: collapse;
   padding: 3px;
   text-align: center;
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
