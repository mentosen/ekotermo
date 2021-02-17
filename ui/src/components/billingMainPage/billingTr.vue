<template>
  <tr>
    <td>{{ data.number }}</td>
    <td>{{ data.region }}</td>
    <td>{{ data.city }}</td>
    <td>{{ data.street }}</td>
    <td>{{ data.buildingNumber }}</td>
    <td v-if="data.status == 'onAccount'" class="green">В учете</td>
    <td v-if="data.status == 'onRegister'" class="yellow">На регестрации</td>
    <td v-if="data.status == 'onDeleted'" class="red">Удален</td>
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
  border: 1px solid #ccc;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}
td{
  height: 36px;
}
.green{
  background-color: #13f113;
}
.yellow{
  background-color: yellow;
}
.red{
  background-color: #ea4c4c;
}
</style>