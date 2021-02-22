<template>
  <div class="tableFilter" v-bind:class="arr[0]">
    <div class="tableFilterDiv">
      <input type="checkbox" value="ALL" checked @click="onClickAll">
      <div class="tableFilterTitle">ВСЕ</div>
    </div>

    <div class="tableFilterDiv" v-for="title in arr">
      <input type="checkbox" value="" checked @click="clickChbs">
      <div class="tableFilterTitle">
        {{title}}
      </div>
    </div>

    <div class="btnPart">
      <button @click="confirm" class="greenBtn">Применить</button>
      <button @click="cancel" class="redBtn">Отмена</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "tableFilter",
  props:["arr","title"],
  mounted() {
    var className = this.arr[0];
    var parent;
    var top;
    var left;
    this.el = document.querySelector("." + className);

    parent = this.el.closest("th");
    top = parent.getBoundingClientRect().top + document.body.scrollTop + parent.offsetHeight;
    left = parent.getBoundingClientRect().left + document.body.scrollLeft+1;

    this.el.style.width = (parent.offsetWidth-7) + "px";
    this.el.style.position = "absolute";
    this.el.style.top = top + "px";
    this.el.style.left = left + "px";
  },
  data(){
    return{
      el: null,
      uncheckedChbs:[]
    }
  },
  methods:{
    confirm(){
      this.$emit('filtered', this.title);
    },
    cancel(){
      for(var k = 0; k < this.uncheckedChbs.length; k ++){
        this.uncheckedChbs[k].checked = true;
      }
      this.uncheckedChbs = [];
      this.$emit('cancelFilter', this.title);
    },
    clickChbs(e){
      var arr = this.uncheckedChbs;
      if(!e.target.checked){
        this.uncheckedChbs.push(e.target);
      }else{
        for(var k = 0; k < arr.length; k ++){
          if(e.target == arr[k]){
            this.uncheckedChbs.splice(k,1);
            break;
          }
        }
      }
    },
    onClickAll(e){
      if(e.target.checked){
        for(var k = 0; k < this.uncheckedChbs.length; k ++){
          this.uncheckedChbs[k].checked = true;
        }
        this.uncheckedChbs = [];
      }
    }
  }
}
</script>


<style scoped>
  .tableFilter{
    background-color: #f3f3f3;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 2px;
    max-height: 400px;
    overflow-y:auto;
  }
  .tableFilterDiv{
    display: flex;
    justify-content: flex-start;
    margin-bottom: 5px;
    font-weight: 400;
    font-size: 14px;
  }
  .tableFilterDiv:first-child{
    margin-top: 5px;
    margin-bottom: 15px;
  }
  .tableFilterTitle{
    text-align: left;
  }
  input[type=checkbox]{
    cursor: pointer;
    margin-right: 5px;
  }
  /*///*/
  button{
    border: none;
    outline: none;
    text-decoration: none;
    background: none;
    cursor: pointer;
  }
  .yellowBtn,.greenBtn,.greyBtn,.blueBtn,.redBtn{
    width: 100%;
    height: 25px;
    padding: 3px;
    border: 1px solid #f9ed17;
    background-color: yellow;
    font-size: 15px;
  }
  .redBtn{
    background-color: #ea4c4c;
    border:1px solid #e23636;
    margin-top: 5px;
  }

  .greenBtn{
    background-color: #13f113;
    border: 1px solid #1bd400;
  }

  .greyBtn{
    background-color: #d4d4cb;
    border: 1px solid #b1b1ab;
  }

  .blueBtn{
    background-color: #01beff;
    border: 1px solid #1183c1;
  }

  .redBtn:hover{
    background-color: #fb6f6f;
  }

  .yellowBtn:hover{
    background-color: #ffffa1;
  }
  .greenBtn:hover{
    background-color: #a7fda7;
  }
  .greyBtn:hover{
    background-color: #e6e6de;
  }
  .blueBtn:hover{
    background-color: #83c4f7;
  }
</style>