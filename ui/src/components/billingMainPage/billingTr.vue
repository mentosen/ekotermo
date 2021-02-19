<template>
  <tr>
    <td>{{ data.number }}</td>
    <td>{{ data.region }}</td>
    <td>{{ data.city }}</td>
    <td>{{ data.street }}</td>
    <td>{{ data.buildingNumber }}</td>
    <td>{{data.category}}</td>
    <td v-if="data.status == 'onAccount'" class="green" title="В учете"></td>
    <td v-if="data.status == 'onRegister'" class="yellow" title="На регистрации"></td>
    <td v-if="data.status == 'onDeleted'" class="red" title="Удален"></td>
    <td class="arrowRight"></td>
  </tr>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  name: "billingTr",
  props:["index"],
  mounted() {
    this.data = Object.assign({}, this.billingObjects[this.index]);
  },
  data(){
    return{
      data:{}
    }
  },
  computed:mapGetters(["billingObjects"]),
  methods:{

  }
}
</script>


<style scoped>
table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}
td{
  height: 24px;
}
.green,.yellow,.red{
  background-repeat: no-repeat;
  background-position: center center;
}
.green{
  background-image: url("../../assets/icons/saved.png");
}
.yellow{
  background-image: url("../../assets/icons/onRegister.png");
}
.red{
  background-image: url("../../assets/icons/deleted.png");
}
.arrowRight{
  background-repeat: no-repeat;
  background-position: center center;
  background-image: url("../../assets/icons/whiteArrowRight.png");
  opacity: 0.85;
  cursor: pointer;
}
.arrowRight:hover{
  opacity: 1;
}
</style>