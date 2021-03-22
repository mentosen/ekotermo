<template>
  <div class="objectDistributionArchive">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="head">
      <div class="headDiv">
        <div>{{$t('objectDistributionArchive1.companyName')}}</div>
        <div>Company Name</div>
      </div>
      <div class="headDiv">
        <div>{{$t('objectDistributionArchive1.objectAddress')}}</div>
        <div>Region City Street Building №</div>
      </div>
    </div>

    <table class="objectDistributionArchiveTable">
      <thead>
      <th>{{$t('objectDistributionArchive1.number')}}</th>
      <th width="150px">{{$t('objectDistributionArchive1.paymentDate')}}</th>
      <th colspan="2" width="200px">{{$t('objectDistributionArchive1.paymentPeriod')}}</th>
      <th colspan="2">{{$t('objectDistributionArchive1.action')}}</th>
      </thead>
      <tbody>
        <ODATr v-for="item in dataODA" v-bind:data="item"></ODATr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
import ODATr from "@/components/objectDistributionArchive/ODATr";
export default {
  name: "objectDistributionArchive",
  computed:mapGetters(["dataODA"]),
  components:{ODATr:ODATr},
  mounted() {
    this.getDataODA();
  },
  data(){
    return{
      data:[]
    }
  },
  methods:{
    ...mapActions(["getDataODA"]),
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

.objectDistributionArchive{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 15px;
}

.head{
  margin-top: 5px;
  font-size: 17px;
}
.head>.headDiv{
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}
.head>.headDiv>div:last-child{
  margin-left: 15px;
}

.objectDistributionArchiveTable{
  margin-top: 35px;
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