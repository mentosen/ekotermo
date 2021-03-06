<template>
  <div class="workingWithObject">
    <router-link to="/billingMainPage">
      <button class="home"></button>
    </router-link>

    <AppHead></AppHead>
    <generalMeterReadings></generalMeterReadings>
  </div>
</template>

<script>
import AppHead from './head';
import generalMeterReadings from './generalMeterReadings';
export default {
  name: "WorkingWithObject",
  data(){
    return{

    }
  },
  components:{AppHead:AppHead,generalMeterReadings:generalMeterReadings},
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
  position: fixed;
  top: 90px;
  left: 30px;
  outline: none;
}
.home:hover{
  background-color: #6ace6a;
}
.workingWithObject{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>