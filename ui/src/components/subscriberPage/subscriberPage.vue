<template>
  <div class="subscriberPage">
    <appHead></appHead>

    <table class="subscriberPageTable">
      <thead>
      <th>{{$t('objectDistributionArchive1.number')}}</th>
      <th width="150px">{{$t('objectDistributionArchive1.paymentDate')}}</th>
      <th colspan="2" width="200px">{{$t('objectDistributionArchive1.paymentPeriod')}}</th>
      <th width="150px">{{$t('objectDistributionArchive1.currentConsumptionGkal')}}</th>
      <th width="150px">{{$t('objectDistributionArchive1.currentConsumptionGrn')}}</th>
      <th>{{$t('objectDistributionArchive1.action')}}</th>
      </thead>
      <tbody>
        <SPTr v-for="item in subscriberPageData" v-bind:data="item"></SPTr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex';
import appHead from './head';
import SPTr from './SPTr';

export default {
  name: "subscriberPage",
  components:{appHead:appHead,SPTr:SPTr},
  computed:mapGetters(["subscriberPageData"]),
  mounted() {
    this.getSubscriberPageData();
  },
  data(){
    return{
      data:[]
    }
  },
  methods:{
    ...mapActions(["getSubscriberPageData"]),
  }
}
</script>


<style scoped>
  .subscriberPage{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .subscriberPageTable{
    margin-top: 50px;
  }
  
  table,td,th{
    border: 1px solid #3c3a3a;
    border-collapse: collapse;
    padding: 3px;
    text-align: center;
    font-size: 15px;
  }
  th{
    font-weight: 500;
  }
</style>