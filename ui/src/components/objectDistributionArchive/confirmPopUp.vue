<template>
  <div class="overlay">
    <div class="popUp">
      <div class="message">{{$t('objectDistributionArchive1.confirmMessage')}}</div>
      <div class="btnPart">
        <button class="redBtn" @click="remove">{{$t('buttons.yes')}}</button>
        <button class="greenBtn" @click="cancel">{{$t('buttons.no')}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default{
  name:"delPopUp",
  data(){
    return{

    }
  },
  mounted() {
    document.body.style.overflow = "hidden";
  },
  methods:{
    ...mapActions(["deleteDataODA"]),
    cancel(){
      this.$emit("cancelConfirmPopUp");
      document.querySelector("body").style.overflow = "";
    },
    remove(){
      this.$emit("cancelConfirmPopUp");
      this.deleteDataODA();
      document.querySelector("body").style.overflow = "";
    }
  }
}
</script>

<style scoped>
.overlay{
  position: fixed;
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
  width: 350px;
  padding: 20px;
  background-color: white;
  border: 1px solid #3c3a3a;
  border-radius: 2px;
  text-align: center;
}

.message{
  margin-top: 10px;
  font-size: 16px;
}
button{
  margin-top: 20px;
  margin-bottom: 20px;
}
.greenBtn,.redBtn{
  width: 120px;
  height: 36px;
  padding: 5px;
  border: 1px solid #3c3a3a;
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
