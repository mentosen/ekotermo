<template>
  <div class="content">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="enterCounterValueTitle">{{$t('billingMainPage.enterMeterReadings')}}</div>
    <table>
      <thead>
      <tr>
        <th rowspan="2" width="50px">{{$t('billingMainPage.serialNumber')}}</th>
        <th colspan="4">{{$t('billingMainPage.address')}}</th>
        <th width="200px" rowspan="2">{{$t('billingMainPage.buildingCategory')}}</th>
        <th rowspan="2" width="150px">{{$t('billingMainPage.currentMeterReadings')}}</th>
        <th rowspan="2" width="100px">{{$t('objectRegistration3.action')}}</th>
      </tr>
      <tr>
        <th width="170px">{{$t('billingMainPage.region')}}</th>
        <th width="150px">{{$t('billingMainPage.city')}}</th>
        <th width="200px">{{$t('billingMainPage.street')}}</th>
        <th width="70px">{{$t('billingMainPage.buildingNumber')}}</th>
      </tr>

      </thead>
      <tbody>
      <counterValueTr v-for="(item, key) in billingObjects" v-bind:index="key" :key="key"></counterValueTr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import counterValueTr from './enterCounterValueTr'
export default {
  name: "enterCounterValue",
  mounted() {
    this.getBillingData();
  },
  data(){
    return{

    }
  },
  computed:mapGetters(["billingObjects"]),
  components:{counterValueTr:counterValueTr},
  methods:{
    ...mapActions(["getBillingData"]),

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
 .content{
     display: flex;
     flex-direction: column;
     justify-content: center;
     align-items: center;
   }
  .enterCounterValueTitle{
    text-align: center;
    margin-top: 40px;
    margin-bottom: 10px;
  }
 table,td,th{
   border: 1px solid #3c3a3a;
   border-collapse: collapse;
   padding: 3px;
   text-align: center;
 }

 th{
   font-weight: 500;
 }

</style>