<template>
  <div class="content">
    <BillingHead></BillingHead>
    <div class="billingTableTitle">Перечень объектов</div>
    <table>
      <thead>
      <tr>
        <th rowspan="2" width="50px">№ п/п</th>
        <th colspan="4">Адрес</th>
        <th width="200px" rowspan="2">Категория дома</th>
        <th width="50px" rowspan="2">Статус</th>
        <th rowspan="2">Перейти</th>
      </tr>
      <tr>
        <th width="150px">Область</th>
        <th width="150px">Город</th>
        <th width="200px">Улица</th>
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
export default {
  name: "BillingMainPage",
  data(){
    return{

    }
  },
  computed:mapGetters(["billingObjects"]),
  components:{BillingHead: BillingHead,BillingTr:BillingTr},
  methods:{

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

</style>