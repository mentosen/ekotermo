<template>
  <tr>
    <td>{{ this.index+1 }}</td>
    <td>{{ data.region }}</td>
    <td>{{ data.city }}</td>
    <td>{{ data.street }}</td>
    <td>{{ data.buildingNumber }}</td>
    <td>{{$t('objectRegistration.' + getBuildingCategory())}}</td>
    <td v-if="data.status === 'REGISTERED'" class="green" v-bind:title="$t('billingMainPage.objectStatus1')"></td>
    <td v-if="data.status === 'NEW'" class="yellow" v-bind:title="$t('billingMainPage.objectStatus2')"></td>
    <td v-if="data.status === 'DELETED'" class="red" v-bind:title="$t('billingMainPage.objectStatus3')"></td>
    <td class="arrowRight" @click="moveToObject"></td>
  </tr>
</template>

<script>
export default {
  name: "billingTr",
  props:["item", "index"],
  computed: {
    data() {
      return this.item
    }
  },
  data(){
    return{
    }
  },
  methods:{
    getBuildingCategory(){
      let that = this;
      return typeof that.data.buildingCategory !== 'undefined' ? that.convertSnakeCaseToCamelCase(that.data.buildingCategory) + 'Title' : "";
    },

    convertSnakeCaseToCamelCase(string){
      return string.toLowerCase().replace(/([-_]\w)/g, group => group[1].toUpperCase());
    },

    moveToObject(){
      this.$router.push({ name: 'Object registration1', params: { id: this.data.id } })
    }
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
  height: 30px;
}
.green,.yellow,.red{
  background-repeat: no-repeat;
  background-position: center center;
}
.green{
  background-image: url("../../assets/icons/saved24.png");
}
.yellow{
  background-image: url("../../assets/icons/onRegister24.png");
}
.red{
  background-image: url("../../assets/icons/deleted24.png");
}
.arrowRight{
  background-repeat: no-repeat;
  background-position: center center;
  background-image: url("../../assets/icons/whiteArrowRight24,2.png");
  opacity: 0.85;
  cursor: pointer;
}
.arrowRight:hover{
  opacity: 1;
}
</style>
