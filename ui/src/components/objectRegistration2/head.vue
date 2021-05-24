<template>
    <div class="head">
      <div class="headHeading">{{ $t('objectRegistrationHead.objectRegistration')}}<br>{{ $t('objectRegistrationHead.section')}} №2</div>
      <div class="headInfo">
        <div class="headAddress">
          <span class="titleMessage">{{ $t('objectRegistrationHead.objectAddress')}}</span>
          <span>{{prepareAddress()}}</span>
        </div>
        <div class="houseInfo">
          <div>{{ $t('objectRegistrationHead.porchNumber')}}</div>
          <div>{{getFlatInfo.entranceCount}}</div>
          <div>{{ $t('objectRegistrationHead.floorNumber')}}</div>
          <div>{{getFlatInfo.floorsCount}}</div>
          <div>{{ $t('objectRegistrationHead.flatNumber')}}</div>
          <div>{{getFlatInfo.flatsCount}}</div>
          <div>{{ $t('objectRegistrationHead.flatNumeration')}} {{ $t('common.from')}}</div>
          <div>{{getFlatInfo.numerableFlatsFrom}}</div>
          <div>{{ $t('common.to')}}</div>
          <div>{{getFlatInfo.numerableFlatsTo}}</div>
        </div>
        <div class="headCategory">
          <span class="titleMessage">{{ $t('objectRegistrationHead.buildingCategory')}}</span>
          <span>{{$t('objectRegistration.'+convertSnakeCaseToCamelCase(getFlatInfo.buildingCategory)+"Title")}}</span>
        </div>
        <div class="headTitle">
          <span class="titleMessage">{{ $t('objectRegistrationHead.buildingTitle')}}</span>
          <span>{{$t('objectRegistration.'+convertSnakeCaseToCamelCase(getFlatInfo.buildingCategory))}}</span>
        </div>
      </div>
    </div>
</template>

<script>
  import {mapActions} from 'vuex'
export default {
  name: 'appHead',
  data(){
    return{
    }
  },
  created() {
    let id = this.$route.params.id;
    if(typeof id !== 'undefined'){
      this.findBuilding(id);
    }
  },
  mounted() {
  },
  computed: {
    getFlatInfo() {
      return this.$store.getters.getFlatInfo
    },
  },
  methods:{
    ...mapActions(["findBuilding"]),
    prepareAddress(){
      let that = this;
      let region = that.getFlatInfo.region + " " + that.$t('common.region').toLowerCase();
      let area = that.getFlatInfo.area + " " + that.$t('common.area').toLowerCase();
      let city = that.$t('common.city').toLowerCase().substr(0,1) + ". " + that.getFlatInfo.city;
      let address = that.getFlatInfo.street + " " + that.getFlatInfo.buildingNumber;
      return region + " " + area + " " + city + " " + address;
    },
    convertSnakeCaseToCamelCase(str){
      if(typeof str !== 'undefined'){
        return str.toLowerCase().replace(/([-_]\w)/g, g => g[1].toUpperCase())
      }
    }
  }
}
</script>

<style scoped>
  *{
    font-size: 18px;
  }
  .head{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
    padding-bottom: 20px;
    border-bottom: 1px solid #3c3a3a;
  }
  .headHeading{
    text-align: center;
    font-size: 22px;
  }

  .headInfo{
    width: 1100px;
  }

  .headAddress, .headCategory, .headTitle {
    display: flex;
    margin-top: 15px;
  }

  .titleMessage {
    margin-right: 5px;
  }

  .houseInfo{
    display: flex;
    justify-content: space-between;
    margin-top: 15px;
  }

</style>
