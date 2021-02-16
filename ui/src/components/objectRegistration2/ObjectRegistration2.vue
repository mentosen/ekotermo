<template>
  <div id="app">
    <Head></Head>
    <FlatData v-bind:flatData="{title: 'Однокомнатная квартира', dataTitle: 'oneFlatRoom', tableTitle: 'Однокомнатные квартиры'}" style="margin-top: 50px"></FlatData>
    <FlatData v-bind:flatData="{title: 'Двухкомнатная квартира', dataTitle: 'twoFlatRoom', tableTitle: 'Двухкомнатные квартиры'}"></FlatData>
    <FlatData v-bind:flatData="{title: 'Трехкомнатная квартира', dataTitle: 'threeFlatRoom', tableTitle: 'Трехкомнатные квартиры'}"></FlatData>
    <FlatData v-bind:flatData="{title: 'Четырехкомнатная квартира', dataTitle: 'fourFlatRoom', tableTitle: 'Четырехкомнатные квартиры'}"></FlatData>
    <FlatData v-bind:flatData="{title: 'Пятикомнатная квартира', dataTitle: 'fiveFlatRoom', tableTitle: 'Пятикомнатные квартиры'}"></FlatData>
    <div class="btnPart">
      <router-link to="/objectRegistration1">
        <button class="yellowBtn" @click="toRegForm1">НАЗАД В РАЗДЕЛ №1</button>
      </router-link>

      <router-link to="/objectRegistration3">
        <button class="greenBtn" @click="toRegForm3" v-bind:disabled="!getIsSaved">ПЕРЕЙТИ К РАЗДЕЛУ №3</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import FlatData from './flatData'
import Head from './head'
export default {
  name: 'app',
  mounted() {
  },data(){
    return{
    }
  },
  computed:{
    ...mapGetters(["getIsSaved"])
  },
  methods:{
    ...mapActions(["saveData"]),
    ...mapMutations([]),

    toRegForm1(){

    },
    toRegForm3(){

    }
  } ,
  components:{FlatData: FlatData, Head: Head}

}
</script>

<style scoped>

 #app{
   display: flex;
   flex-direction: column;
   align-items: center;
 }
 .btnPart{
   margin-top: 70px;
 }
 .yellowBtn,.greenBtn,.greyBtn{
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
