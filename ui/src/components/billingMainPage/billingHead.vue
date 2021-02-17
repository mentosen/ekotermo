<template>
  <div class="head">
    <div class="title">Название билинговой компании</div>
    <div class="headDivs">
      <div>
        <span>Колличество объектов:</span>
        <span class="countSpan"></span>
      </div>
      <div>
        <span>Колличество зарегестрированых распределителей:</span>
        <span class="countSpan"></span>
      </div>
      <div class="dropMenu" @mouseover="onMenuOver" @mouseout="onMenuOut">
        <button class="dropMenuItem">Дополнительные функции</button>
        <button class="dropMenuItem">Бланк анкеты объекта</button>
        <button class="dropMenuItem">Счет на оплату</button>
        <button class="dropMenuItem">Статистика</button>
        <button class="dropMenuItem">Нормативная документация</button>
        <button class="dropMenuItem">Обратная связь</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  name: "billingHead",
  data(){
    return{
      top: null,
      left:null
    }
  },
  computed: mapGetters(["billingDataHead"]),
  methods:{
    onMenuOver(e){
      var parent = e.target.parentElement;
      !this.top? this.top = e.target.offsetTop: "";
      !this.left? this.left = e.target.offsetLeft: "";

      var clone = document.createElement("div");
      clone.classList.add("clone");
      clone.style.width = "210px";
      clone.style.height = "33px";


      parent.style.position = "absolute";
      parent.style.left = this.left + "px";
      parent.style.top = this.top + "px";
      parent.style.height = "auto";
      parent.parentElement.appendChild(clone);
    },
    onMenuOut(e){
      var parent = e.target.parentElement;
      var clone = parent.parentElement.querySelector(".clone");
      parent.parentElement.removeChild(clone);
      parent.style.position = "";
      parent.style.height = "33px";
    }
  }
}
</script>


<style scoped>
  .head{
    width: 1000px;
    padding: 20px 5px 20px 5px;
    border-bottom: 1px solid #ccc;
  }
  .title{
    width: 100%;
    text-align: center;
    margin-bottom: 20px;
    font-size: 18px;
  }
  .headDivs{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .countSpan{
    margin-left: 10px;
  }
  .dropMenu{
    width: 210px;
    height: 33px;
    overflow: hidden;
    background-color: #f3f3f3;
    border-radius: 4px;
  }
  .dropMenuItem{
    width: 100%;
    height: 35px;
    border:none;
    background: none;
    border-bottom: 2px solid #ccc;
    text-align: center;
    outline: none;
    cursor: pointer;
    box-sizing: border-box;
    font-size: 15px;
  }
  .dropMenuItem:last-child{
    margin-bottom: 0;
  }
  .dropMenuItem:hover{
    background-color: #01beff;
  }
</style>