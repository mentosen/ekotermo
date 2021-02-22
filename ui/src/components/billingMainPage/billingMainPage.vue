<template>
  <div class="content">
    <BillingHead></BillingHead>
    <div class="billingTableTitle">Перечень объектов</div>
    <table class="billingTable">
      <thead>
      <tr>
        <th rowspan="2" width="50px">№ п/п</th>
        <th colspan="4">Адрес</th>
        <th width="200px" rowspan="2">Категория дома</th>
        <th width="50px" rowspan="2">Статус</th>
        <th rowspan="2">Перейти</th>
      </tr>
      <tr>
        <th width="170px" @click="onThClick" class="filter">
          Область
          <tableFilter v-bind:arr="filter.regions" title="region" v-show="filter.regionsIsShow"
                       @cancelFilter="hideFilter" @filtered="confirmFilter"></tableFilter>
        </th>
        <th width="150px">Город</th>
        <th width="200px" @click="onThClick" class="filter">
          Улица
          <tableFilter v-bind:arr="filter.streets" title="street" v-show="filter.streetsIsShow"
                       @cancelFilter="hideFilter" @filtered="confirmFilter"></tableFilter>
        </th>
        <th width="70px">№ дома</th>
      </tr>

      </thead>
      <tbody>
        <BillingTr v-for="(item, key) in billingObjects" v-bind:index="key" :key="key"></BillingTr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
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
      filter:{
        regions:["Миколаївська","Київська","Івано-Франківська"],
        regionsIsShow: false,
        streets:["Соборна","Богдана Хмельницького","Площа ринок"],
        streetsIsShow: false
      }
    }
  },
  computed:mapGetters(["billingObjects"]),
  components:{BillingHead: BillingHead, BillingTr:BillingTr, tableFilter:tableFilter},
  methods:{
    onThClick(e){
      if(e.target == this.tableHeadTr2[0]){
        this.filter.regionsIsShow = true;
      }else if(e.target == this.tableHeadTr2[2]){
        this.filter.streetsIsShow = true;
      }
    },
    hideFilter(title){
      if(title == "region"){
        this.filter.regionsIsShow = false;
      }else if(title == "street"){
        this.filter.streetsIsShow = false;
      }
    },
    confirmFilter(title){
      if(title == "region"){
        this.filter.regionsIsShow = false;
      }else if(title == "street"){
        this.filter.streetsIsShow = false;
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
}
.filter:hover{
  background-color: rgb(243 243 243);
}

</style>