<template>
  <div class="overlay">
    <div class="feedBackPopUp">
      <div class="content">
        <div class="title">{{$t('feedback.feedback')}}</div>
        <div class="contentDiv">
          <div class="inpTitle mustBe">{{$t('feedback.name')}}</div>
          <input type="text" name="name" class="required">
        </div>
        <div class="contentDiv">
          <div class="inpTitle mustBe">{{$t('feedback.phoneNumber')}}</div>
          <input type="text" name="phoneNumber" v-bind:value="data.phoneNumber" placeholder="+380000000000" class="required"
          @keydown="validatePhoneNumber" pattern="^\+\d{12}$">
        </div>
        <div class="contentDiv">
          <div class="inpTitle">{{$t('feedback.theme')}}</div>
          <input type="text" name="theme">
        </div>
        <div class="contentDiv">
          <div class="inpTitle mustBe">{{$t('feedback.message')}}</div>
          <textarea name="message" class="required"></textarea>
        </div>
      </div>
      <div class="buttons">
        <button @click="cancel" class="redBtn">{{$t('buttons.discardFilter')}}</button>
        <button @click="accept" class="greenBtn">{{$t('buttons.sendFeedBack')}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapMutations, mapActions} from "vuex"
export default {
  name: "feedback",
  mounted() {
    var overlay = document.querySelector(".overlay");
    document.body.style.overflow = "hidden";
  },
  data(){
    return{
      data:{
        name:"",
        phoneNumber:"+380",
        theme:"",
        message:""
      }
    }
  },
  methods:{
    ...mapMutations(["changeIsShowFeedback"]),
    ...mapActions(["sendFeedBack"]),
    cancel(){
      document.body.style.overflow = "";
      this.changeIsShowFeedback(false);
    },
    accept(){
      var inputs = document.querySelector(".feedBackPopUp").querySelectorAll("input,textarea");
      var result = true;

      for(var k = 0; k < inputs.length;k++){
        this.data[inputs[k].name] = inputs[k].value;
        if(inputs[k].className == "required" && !inputs[k].value ||
            inputs[k].pattern && !inputs[k].value.match(inputs[k].pattern)){
          inputs[k].style.border = "1px solid red";
          this.removeRedBorder(inputs[k]);
          result = false;
        }
      }

      if(result){
        this.sendFeedBack(Object.assign({},this.data));
        document.body.style.overflow = "";
        this.changeIsShowFeedback(false);
      }
    },
    validatePhoneNumber(e){
      if(e.key == "Backspace" || e.key === "+"|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " "|| e.target.value.length > 12){
        e.preventDefault();
      }
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    }
  }
}
</script>


<style scoped>
*{
  color: black;
}
.overlay{
  position: fixed;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999999;
}
.feedBackPopUp{
  width: 400px;
  padding: 20px;
  background-color: white;
  border: 1px solid #3c3a3a;
  border-radius: 5px;
  text-align: center;
}
.title{
  font-size: 17px;
}
.contentDiv{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  width: 100%;
  margin-top: 10px;
}
.contentDiv>input,.contentDiv>textarea{
  width: 100%;
  border: 1px solid #3c3a3a;
  border-radius: 3px;
  padding: 3px;
  outline: none;
  margin-top: 5px;
}
.contentDiv>textarea{
  height: 100px;
  resize: none;
}
.mustBe:after{
  content: "*";
  color:red;
  margin-left: 5px;
}
.buttons{
  margin-top: 20px;
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