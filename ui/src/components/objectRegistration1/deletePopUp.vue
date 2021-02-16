<template>
  <div class="overlay">
    <div class="popUp">
      <div class="message" v-if="!getAccountState">После нажатия кнопки "Подтвердить", объект будет удален.</div>
      <div class="message" v-if="getAccountState">Ваш запрос на удаление объекта будет отправлен администратору и обработан в течении друх рабочих дней.</div>
      <div class="buttons">
        <button @click="cancel" class="redBtn">Отмена</button>
        <button @click="accept" class="greenBtn">Подтвердить</button>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters, mapActions, mapMutations} from 'vuex'
  export default{
    name:"delPopUp",
    data(){
      return{

      }
    },
    mounted() {
      window.scrollTo(0,0);
      document.querySelector("body").style.overflow = "hidden";
    },
    methods:{
      ...mapActions([]),
      ...mapMutations(["changeIsDel"]),
      cancel(){
        this.changeIsDel(false);
        document.querySelector("body").style.overflow = "";
      },
      accept(){

        this.changeIsDel(false);
        document.querySelector("body").style.overflow = "";
      }
    },
    computed: mapGetters(["getAccountState","getIsDel"]),
  }
</script>

<style>
.overlay{
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99999999;
}
.popUp{
  width: 400px;
  padding: 20px;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 2px;
  text-align: center;
}
.message{
  font-size: 20px;
}
.buttons{
  margin-top: 20px;
}
.greenBtn,.redBtn{
  width: 120px;
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
.redBtn{
  border: 1px solid #f12929;
  background-color: #ea4c4c;
}
.greenBtn{
  background-color: #13f113;
  border: 1px solid #1bd400;
}

.redBtn:hover{
  background-color: #fb6f6f;
}

.greenBtn:hover{
  background-color: #a7fda7;
}

</style>
