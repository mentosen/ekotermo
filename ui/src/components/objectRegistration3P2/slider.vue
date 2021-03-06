<template>
  <div class="overlay">
    <div class="controls">
      <div class="count">{{$t('objectRegistration3.photo')}} {{count}} {{$t('objectRegistration3.of')}} {{photos.length}}</div>
      <button class="redBtn remove" @click="remove">{{$t('buttons.delete')}}</button>
      <button @click="close" class="close"></button>
    </div>

    <div class="content">
      <button class="arrows arrow1" @click="switchPhoto" name="previous" v-show="!isFirst"></button>
      <div class="photoDiv">
        <div class="photo">
          <img v-bind:src="item" alt="" v-for="item in photos">
        </div>
      </div>
      <button class="arrows arrow2" @click="switchPhoto" name="next" v-show="!isLast"></button>
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  name:"slider",
  props:["photos"],
  mounted() {
    window.scrollTo(0,0);
    document.querySelector("body").style.overflow = "hidden";
    this.images = document.querySelector(".photoDiv").querySelectorAll("img");
    this.removeBtn = document.querySelector(".controls").querySelector(".remove");
    this.removeBtn.value = this.count-1;
    if(this.images.length == 1) this.isLast = true;
  },
  data(){
    return{
      count:1,
      images:[],
      isFirst: true,
      isLast: false,
      removeBtn: null
    }
  },
  methods:{
    ...mapActions(["removeImage"]),
    close(){
      this.$emit("closeSlider");
      document.querySelector("body").style.overflow = "";
    },
    changeIsPhoto(){
      this.$emit("isPhoto", false);
    },
    switchPhoto(e){
      if(e.target.name === "previous"){
        this.images[this.count-1].style.display = "none";
        this.images[this.count-2].style.display = "";
        this.count--;
        this.removeBtn.value = this.count-1;
      }else{
        this.images[this.count-1].style.display = "none";
        this.images[this.count].style.display = "";
        this.count++;
        this.removeBtn.value = this.count-1;
      }

      this.count == 1?this.isFirst = true:this.isFirst = false;
      this.count == this.photos.length?this.isLast = true:this.isLast = false;

    },
    remove(e){
      var previous = document.querySelector("button[name=previous]");
      var event = new Event("click");

      if(e.target.value == this.photos.length-1 && this.photos.length > 1){
        this.photos.splice(e.target.value,1);
        previous.dispatchEvent(event);
      }else{
        this.photos.splice(e.target.value,1);
      }
      this.removeImage(e.target.value);
      this.images = document.querySelector(".photoDiv").querySelectorAll("img");
      if(this.photos.length <= 0){
        this.close();
        this.changeIsPhoto();
      }
      this.count == 1?this.isFirst = true:this.isFirst = false;
      this.count == this.photos.length?this.isLast = true:this.isLast = false;
    }
  }
}
</script>

<style scoped>
.overlay{
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.5);
  z-index: 9999999;
}
.controls{
  padding: 15px;
}
.count{
  color: #efecec;
  margin-bottom: 10px;
}
.close,.arrows{
  border: none;
  background: none;
  outline: none;
  color: #efecec;
  width: 16px;
  height: 16px;
  background-image: url("../../assets/icons/controls.png");
  background-position: -3px 0;
  opacity: 0.5;
  transition: opacity 150ms linear;
  cursor: pointer;
  position: absolute;
  top: 5%;
  left: 96.5%;
}
.close:hover,.arrows:hover{
  opacity: 1;
}
.arrows{
  height: 32px;
  width: 20px;
}
.content{
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.arrow1{
  top:49%;
  left:1%;
  background-position: 0 -25px;
}
.arrow2{
  top:49%;
  left:97.5%;
  background-position: 0 -63px;
}
.photoDiv{
  width: 900px;
  height: 600px;
}
.photo{
  height: 600px;
  display: flex;
  overflow: hidden;
  flex-wrap: wrap;
  justify-content: center;
}
.photo>img{
  height: 100%;
  width: auto;
  flex-shrink: 0;
}
.redBtn{
  width: 120px;
  height: 36px;
  padding: 5px;
  border: 1px solid #f9ed17;
  border-radius: 3px;
  outline: none;
  text-decoration: none;
  background-color: yellow;
  cursor: pointer;
}
.redBtn{
  border: 1px solid #f12929;
  background-color: #ea4c4c;
}
.redBtn:hover{
  background-color: #fb6f6f;
}
</style>
