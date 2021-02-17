<template>
  <div class="content">
    <BillingHead></BillingHead>
    <div class="bilingTableTitle">Перечень объектов</div>
    <table>
      <thead>
      <th>№ п/п</th>
      <th colspan="4">Адрес объекта</th>
      <th>Статус объекта</th>
      </thead>
      <tbody>
        <BillingTr v-for="(item, key) in billingObjects" v-bind:index="key" :key="key"></BillingTr>
      </tbody>
    </table>
    <div class="btnPart">
      <router-link to="/objectRegistration1">
        <button class="yellowBtn">Регистрация нового объекта</button>
      </router-link>

      <button class="greenBtn">Работа с объектами</button>
    </div>
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
.bilingTableTitle{
  text-align: center;
  margin-top: 40px;
  margin-bottom: 10px;
}
table{
  width: 850px;
  table-layout: fixed;

}

table,td,th{
  border: 1px solid #ccc;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
}

th{
  font-weight: 400;
}
.btnPart{
  margin-top: 50px;
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