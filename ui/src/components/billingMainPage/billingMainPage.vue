<template>
  <div class="content">
    <BillingHead></BillingHead>

    <div class="billingTableTitle">{{$t('billingMainPage.objectList')}}</div>
    <table class="billingTable">
      <thead>
      <tr>
        <th rowspan="2" width="50px">{{$t('billingMainPage.serialNumber')}}</th>
        <th colspan="4">{{$t('billingMainPage.address')}}</th>
        <th width="200px" rowspan="2">{{$t('billingMainPage.buildingCategory')}}</th>
        <th width="50px" rowspan="2">{{$t('billingMainPage.status')}}</th>
        <th rowspan="2" width="50px">{{$t('billingMainPage.goTo')}}</th>
      </tr>
      <tr>
        <th width="170px" @click="onThClick" class="filter">
          {{$t('billingMainPage.region')}}
          <tableFilter v-if="filter.regions.length > 0" v-bind:arr="filter.regions" title="region" v-show="filter.regionsIsShow"
                       @cancelFilter="hideFilter" @filtered="confirmFilter"></tableFilter>
        </th>
        <th width="150px">{{$t('billingMainPage.city')}}</th>
        <th width="200px" @click="onThClick" class="filter">
          {{$t('billingMainPage.street')}}
          <tableFilter v-if="filter.streets.length > 0" v-bind:arr="filter.streets" title="street" v-show="filter.streetsIsShow"
                       @cancelFilter="hideFilter" @filtered="confirmFilter"></tableFilter>
        </th>
        <th width="70px">{{$t('billingMainPage.buildingNumber')}}</th>
      </tr>

      </thead>
      <tbody v-if="billingObjects.length > 0">
        <BillingTr v-for="(item, key) in billingObjects" :item="item" :index="key" :key="key"></BillingTr>
      </tbody>
      <tbody v-else>
      <tr>
        <td colspan="8">{{$t('billingMainPage.noBuildings')}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import BillingHead from './billingHead'
import BillingTr from './billingTr'
import tableFilter from './tableFilter'
export default {
  name: "BillingMainPage",
  mounted() {
    var thead = document.querySelector(".billingTable").querySelector("thead");
    this.tableHeadTr1 = thead.querySelectorAll("tr")[0].querySelectorAll("th");
    this.tableHeadTr2 = thead.querySelectorAll("tr")[1].querySelectorAll("th");
  },
  data(){
    return{
      tableHeadTr1: null,
      tableHeadTr2: null,
      listToShow:[],
      filter:{
        regions:[],
        regionsIsShow: false,
        streets:[],
        streetsIsShow: false
      }
    }
  },
  computed:{
    billingObjects() {
      let that = this;
      if(this.$store.getters.billingObjects.length > 0){
        let buildings = this.$store.getters.billingObjects;
        that.filter.regions = [...new Set(buildings.map(function (item) {
          return item.region
        }))]

        that.filter.streets = [...new Set(buildings.map(function (item) {
          return item.street
        }))]

        if(that.listToShow.length > 0){
          let result = that.$store.getters.billingObjects.filter(function (item) {
            return that.listToShow.includes(item.region) || that.listToShow.includes(item.street)
          });
          return result
        } else {
          return that.$store.getters.billingObjects
        }
      } else {
        return [];
      }
    }
  },
  components:{BillingHead: BillingHead, BillingTr:BillingTr, tableFilter:tableFilter},
  methods:{
    onThClick(e){
      if(e.target === this.tableHeadTr2[0]){
        this.filter.regionsIsShow = true;
      }else if(e.target === this.tableHeadTr2[2]){
        this.filter.streetsIsShow = true;
      }
    },
    hideFilter(title){
      if(title === "region"){
        this.filter.regionsIsShow = false;
      }else if(title === "street"){
        this.filter.streetsIsShow = false;
      }
    },
    confirmFilter(title, listToShow){
      let that = this;
      if(title === "region"){
        that.filter.regionsIsShow = false;
        that.listToShow = listToShow;
      }else if(title === "street"){
        that.listToShow = listToShow;
        that.filter.streetsIsShow = false;
      }
    }
  }
}
</script>


<style scoped>
.content{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.billingTableTitle{
  text-align: center;
  margin-top: 40px;
  margin-bottom: 10px;
}
table{
  /*width: 850px;*/
  /*table-layout: fixed;*/
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

.filter{
  cursor: pointer;
  background-image: url("../../assets/icons/filter.png");
  background-repeat: no-repeat;
  background-position: center right;
}
.filter:hover{
  background-color: rgb(243 243 243);
  background-image: url("../../assets/icons/filterHover.png");
}

</style>
