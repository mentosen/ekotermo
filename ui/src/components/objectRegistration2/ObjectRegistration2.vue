<template>
  <div class="content">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <Head></Head>

    <FlatData v-bind:flatData="{title: $t('objectRegistration2.singleRoom'),
        dataTitle: 'SINGLE_ROOM',
        tableTitle: this.$t('objectRegistration2.oneFlatRoomTableTitle')}" style="margin-top: 50px"></FlatData>
    <FlatData v-bind:flatData="{title: this.$t('objectRegistration2.doubleRoom'),
        dataTitle: 'DOUBLE_ROOM',
        tableTitle: this.$t('objectRegistration2.twoFlatRoomTableTitle')}"></FlatData>
    <FlatData v-bind:flatData="{title: this.$t('objectRegistration2.tripleRoom'),
        dataTitle: 'TRIPLE_ROOM',
        tableTitle: this.$t('objectRegistration2.threeFlatRoomTableTitle')}"></FlatData>
    <FlatData v-bind:flatData="{title: this.$t('objectRegistration2.quadripleRoom'),
        dataTitle: 'QUADRUPLE_ROOM',
        tableTitle: this.$t('objectRegistration2.fourFlatRoomTableTitle')}"></FlatData>
    <FlatData v-bind:flatData="{title: this.$t('objectRegistration2.quintipleRoom'),
        dataTitle: 'QUINTUPLE_ROOM',
        tableTitle: this.$t('objectRegistration2.fiveFlatRoomTableTitle')}"></FlatData>
    <FlatData v-bind:flatData="{title: this.$t('objectRegistration2.nonLivePremises'),
        dataTitle: 'NON_LIVE_PREMISES',
        tableTitle: this.$t('objectRegistration2.nonLivePremisesTableTitle')}"></FlatData>
    <div class="btnPart">
      <button class="yellowBtn" @click="toRegForm1">{{ $t('buttons.backToSection1')}}</button>

      <button class="greenBtn" @click="toRegForm3" v-bind:disabled="!getIsSaved">{{ $t('buttons.toSection3')}}</button>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import FlatData from './flatData'
import Head from './head'
export default {
  name: 'app',
  data(){
    return{
    }
  },
  created(){
    this.findAllFlatTypes(this.$route.params.id)
  },
  mounted() {
  },
  computed:{
    ...mapGetters(["section3Url"]),

    flatsData() {
      return this.$store.getters.getFlatsData
    },
    getIsSaved() {
      let that = this;
      let isSaved = false;

      Object.keys(that.flatsData).forEach(function (item) {
        if(that.flatsData[item].length > 0){
          isSaved = true;
        }
      })

      return isSaved
    }
  },
  methods:{
    ...mapActions(["saveData", "findAllFlatTypes"]),
    ...mapMutations([]),

    toRegForm1(){
      this.$router.push({ name: 'Object registration1', params: { id: this.$route.params.id } })
    },
    toRegForm3(){
      this.$router.push({ name: 'Object registration3P2', params: { id: this.$route.params.id } })
    },
  } ,
  components:{FlatData: FlatData, Head: Head}

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

.content{
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
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
