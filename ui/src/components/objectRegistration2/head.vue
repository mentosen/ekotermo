<template>
    <div class="head">
      <div class="headHeading">Регестрация объекта<br>раздел №2</div>
      <div class="headInfo">
        <div class="headAddress">
          <div>Адрес объекта: </div>
          <div>Region</div>
          <div>City</div>
          <div>Street</div>
          <div>HouseNumber</div>
        </div>
        <div class="houseInfo">
          <div>Количество подъездов:</div>
          <div></div>
          <div>Количество этажей:</div>
          <div></div>
          <div>Количество квартир:</div>
          <div></div>
          <div>Нумерация квартир с</div>
          <div></div>
          <div>по</div>
          <div></div>
        </div>
        <div class="headCategory">
          <div>Категория дома:</div>
          <div></div>
        </div>
        <div class="headTitle">
          <div>Описание:</div>
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
    border-bottom: 1px solid #ccc;
  }
  .headHeading{
    text-align: center;
    font-size: 22px;
  }

  .headInfo{
    width: 1000px;
  }

  .headAddress,.houseInfo, .headCategory,.headTitle{
    display: flex;
    justify-content: space-between;
    margin-top: 15px;
  }

</style>
