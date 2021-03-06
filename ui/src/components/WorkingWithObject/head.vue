<template>
    <div class="head">
      <div class="headHeading">Работа с обьектом</div>
      <div class="headInfo">
        <div class="headAddress">
          <div>{{ $t('objectRegistrationHead.objectAddress')}}</div>
          <div>Region</div>
          <div>City</div>
          <div>Street</div>
          <div>HouseNumber</div>
        </div>
        <div class="houseInfo">
          <div>{{ $t('objectRegistrationHead.porchNumber')}}</div>
          <div></div>
          <div>{{ $t('objectRegistrationHead.floorNumber')}}</div>
          <div></div>
          <div>{{ $t('objectRegistrationHead.flatNumber')}}</div>
          <div></div>
          <div>{{ $t('objectRegistrationHead.flatNumeration')}} {{ $t('common.from')}}</div>
          <div></div>
          <div>{{ $t('common.to')}}</div>
          <div></div>
        </div>
        <div class="headCategory">
          <div>{{ $t('objectRegistrationHead.buildingCategory')}}</div>
          <div></div>
        </div>
        <div class="headTitle">
          <div>{{ $t('objectRegistrationHead.buildingTitle')}}</div>
          <div></div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'appHead',
  data(){
    return{
    }
  },
  methods:{
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
