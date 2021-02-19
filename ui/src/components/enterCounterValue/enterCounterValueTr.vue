<template>
  <tr>
    <td>{{ data.number }}</td>
    <td>{{ data.region }}</td>
    <td>{{ data.city }}</td>
    <td>{{ data.street }}</td>
    <td>{{ data.buildingNumber }}</td>
    <td>{{data.category}}</td>
    <td>
      <input type="text" class="counterVal" placeholder="000000,00"
             @keydown="validateCounterValue" @keyup="validateCounterValueKeyUp"
             v-if="data.isEdit" name="counterValue" v-bind:value="data.counterValue">
      <div v-if="!data.isEdit">{{data.counterValue}}</div>
    </td>
    <td>
      <div class="btnPart">
        <button class="yellowBtn" @click="save" v-bind:disabled="data.isSaved">Сохранить</button>
        <button class="greyBtn" @click="edit" v-bind:disabled="data.isEdit">Редактировать</button>
      </div>
    </td>
  </tr>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
  name: "enterCounterValueTr",
  props:["index"],
  mounted() {
    this.data = Object.assign({}, this.billingObjects[this.index]);
  },
  data(){
    return{
      data:{}
    }
  },
  computed:mapGetters(["billingObjects"]),
  methods:{
    ...mapActions(["saveCounterValue"]),
    save(e){
      var result = this.validate(e.target);
      if(result){
        this.data.isSaved = true;
        this.data.isEdit = false;
        this.saveCounterValue({data:this.data, index:this.index});
      }
    },
    edit(){
      this.data.isSaved = false;
      this.data.isEdit = true;
    },
    validate(el){
      var regEx = /^\d{6}\,\d{2}$/;
      var input = el.closest("tr").querySelector("input[name=counterValue]");

      if(input.value.match(regEx)){
        this.data.counterValue = input.value;
        return true
      }else{
        input.style.border = "1px solid red";
        this.removeBorder(input);
        return false
      }
    },
    removeBorder(el){
      setTimeout(function (){
        el.style.border = "";
      },1000)
    },
    validateCounterValue(e){
      if(e.key == "Backspace" || e.key === "ArrowLeft" || e.key === "ArrowRight"|| e.key == ","){
        return
      } else if(isNaN(e.key) || e.key == " " ||e.target.value.length === 9){
        e.preventDefault();
        return
      }
    },
    validateCounterValueKeyUp(e){
      if(e.target.value.length == 6){
        if(e.key === "Backspace") return
        e.target.value += ",";
      }
    }
  }
}
</script>


<style scoped>
table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}
td{
  height: 24px;
}
td:last-child{
  padding: 0;
}
.green,.yellow,.red{
  background-repeat: no-repeat;
  background-position: center center;
  background-color: white;
}
.green{
  background-image: url("../../assets/icons/saved.png");
}
.yellow{
  background-image: url("../../assets/icons/onRegister.png");
}
.red{
  background-image: url("../../assets/icons/deleted.png");
}
.arrowRight{
  background-repeat: no-repeat;
  background-position: center center;
  background-image: url("../../assets/icons/whiteArrowRight.png");
  opacity: 0.85;
  cursor: pointer;
}
.arrowRight:hover{
  opacity: 1;
}
.btnPart{
  width: 100%;
  height: 100%;
}
table input{
  border: 1px solid #3c3a3a;
  width: 100px;
  padding: 3px 3px 3px 5px;
  border-radius: 4px;
  outline: none;
}
button{
  border: none;
  outline: none;
  text-decoration: none;
  background: none;
  cursor: pointer;
}
.yellowBtn,.greenBtn,.greyBtn,.blueBtn{
  width: 100%;
  height: 25px;
  padding: 3px;
  border: 1px solid #f9ed17;
  background-color: yellow;
  font-size: 15px;
}
.redBtn{
  width: 100%;
  height: 100%;
  border: none;
  background-color: #ea4c4c;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  outline: none;
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