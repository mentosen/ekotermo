<template>
    <div class="head">
      <div class="headHeading">{{ $t('objectRegistrationHead.objectRegistration')}}<br>{{ $t('objectRegistrationHead.section')}} №2</div>
      <div class="headInfo">
        <div class="headAddress">
          <div>{{ $t('objectRegistrationHead.objectAddress')}}</div>
          <div>{{data.region}}</div>
          <div>{{data.city}}</div>
          <div>{{data.street}}</div>
          <div>{{data.buildingNumber}}</div>
        </div>
        <div class="houseInfo">
          <div>{{ $t('objectRegistrationHead.porchNumber')}}</div>
          <div>{{data.entranceCount}}</div>
          <div>{{ $t('objectRegistrationHead.floorNumber')}}</div>
          <div>{{data.floorsCount}}</div>
          <div>{{ $t('objectRegistrationHead.flatNumber')}}</div>
          <div>{{data.flatsCount}}</div>
          <div>{{ $t('objectRegistrationHead.flatNumeration')}} {{ $t('common.from')}}</div>
          <div>{{data.numerableFlatsFrom}}</div>
          <div>{{ $t('common.to')}}</div>
          <div>{{data.numerableFlatsTo}}</div>
        </div>
        <div class="headCategory">
          <div>{{ $t('objectRegistrationHead.buildingCategory')}}</div>
          <div>{{data.buildingCategory}}</div>
        </div>
        <div class="headTitle">
          <div>{{ $t('objectRegistrationHead.buildingTitle')}}</div>
          <div></div>
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
      data:{}
    }
  },
  created() {
    let id = this.$route.params.id;
    if(typeof id !== 'undefined'){
      this.findBuilding(id);
    }
  },
  mounted() {
    this.data = this.getFlatInfo;
    debugger
  },
  computed: {
    getFlatInfo() {
      debugger
      return this.$store.getters.getFlatInfo
    },
  },
  methods:{
    ...mapActions(["findBuilding"]),
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

  .headAddress,.houseInfo, .headCategory,.headTitle{
    display: flex;
    justify-content: space-between;
    margin-top: 15px;
  }

</style>
