<template>
  <div class="content">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <div class="enterCounterValueTitle">Внести показания счетчика</div>
    <table>
      <thead>
      <tr>
        <th rowspan="2" width="50px">№ п/п</th>
        <th colspan="4">Адрес</th>
        <th width="70px" rowspan="2">Тип учета</th>
        <th width="50px" rowspan="2">Статус</th>
        <th rowspan="2" width="150px">Внести показания счетчика</th>
        <th rowspan="2" width="100px">Действие</th>
      </tr>
      <tr>
        <th width="150px">Область</th>
        <th width="150px">Город</th>
        <th width="200px">Улица</th>
        <th width="70px">№ дома</th>
      </tr>

      </thead>
      <tbody>
      <counterValueTr v-for="(item, key) in billingObjects" v-bind:index="key" :key="key"></counterValueTr>
      </tbody>
    </table>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import counterValueTr from './enterCounterValueTr'
export default {
  name: "enterCounterValue",
  data(){
    return{

    }
  },
  computed:mapGetters(["billingObjects"]),
  components:{counterValueTr:counterValueTr},
  methods:{

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
  position: absolute;
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