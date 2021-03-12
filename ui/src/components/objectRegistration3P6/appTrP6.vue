<template>
    <tr class="appTr">
      <td>{{flatNumber}}</td>
      <td class="selectTd">
        <select class="tableSelect" v-if="data.isEdit">
          <option v-for="(num, key) in getEntrance" v-bind:value="num.num" v-bind:selected="data.entrance == num.num">{{key+1}}</option>
        </select>
        <div v-if="!data.isEdit">{{data.entrance}}</div>
      </td>
      <td>
        <select class="tableSelect"  v-if="data.isEdit">
          <option v-for="(num, key) in getFloors" v-bind:value="num.num" v-bind:selected="data.floor == num.num">{{key+1}}</option>
        </select>
        <div v-if="!data.isEdit">{{data.floor}}</div>
      </td>
      <td class="selectTd">
        <select class="tableSelect" v-if="data.isEdit">
          <option v-for="(num, key) in getFlatType" v-bind:value="num.num" v-bind:selected="data.flatType == num.num">{{ $t('objectRegistration3.type')}}{{key+1}}</option>
        </select>
        <div v-if="!data.isEdit">{{ $t('objectRegistration3.type')}} {{data.flatType}}</div>
      </td>
      <td class="selectTd">
        <select class="tableSelect" v-if="data.isEdit">
          <option v-for="item in getCof" v-bind:value="item.value" v-bind:selected="data.cof == item.value">{{item.title}}</option>
        </select>
        <div v-if="!data.isEdit">{{data.cof}}</div>
      </td>

      <td>
        <select class="tableSelectPurpose" v-if="data.isEdit" @change="addRoomPurpose">
          <option v-for="title in getRoomPurpose" v-bind:value="title" v-bind:selected="data.roomPurpose == title">
            {{ title }}
          </option>
        </select>
        <div class="purposeDiv" v-for="(item, key) in roomPurpose" :key="key" v-bind:name="item.title">
          {{item.room}}
          <button v-bind:value="key" @click="removeFromRoomPurpose" class="removeRoomPurpose" v-if="data.isEdit"></button>
        </div>
      </td>

      <td v-bind:class="{purposeInp: data.isEdit}">
        <div class="purposeInputDiv kc" v-for="(item, key) in roomPurpose" :key="key"  v-if="data.isEdit">
          <input type="text" class="purposeInput" placeholder="00,00" pattern="^\d{2},\d{2}$" @keydown="cofKeyDown"
                 @keyup="cofKeyUp" v-bind:name="'kc'+ key" v-bind:value="item.kc">
        </div>
        <div v-if="!data.isEdit" v-for="(item) in roomPurpose" class="purposeInputDiv">{{item.kc}}</div>
      </td>
      <td v-bind:class="{purposeInp: data.isEdit}">
        <div class="purposeInputDiv kq" v-for="(item, key) in roomPurpose" :key="key" v-if="data.isEdit">
          <input type="text" class="purposeInput" placeholder="00,00" pattern="^\d{2},\d{2}$" @keydown="cofKeyDown"
                 @keyup="cofKeyUp" v-bind:name="'kq'+ key" v-bind:value="item.kq">
        </div>
        <div v-if="!data.isEdit" v-for="(item) in roomPurpose" class="purposeInputDiv">{{item.kq}}</div>
      </td>

      <td v-bind:class="{purposeInp: data.isEdit}">
        <div class="purposeInputDiv serialNum" v-for="(item, key) in roomPurpose" :key="key" v-if="data.isEdit">
          <input type="text" class="tableInpText" @keydown="validateCounter" v-bind:placeholder="$t('objectRegistration3.meterPlaceholder')"
                 v-bind:value="item.serialNumber"  pattern="^\d{12}$">
        </div>
        <div v-if="!data.isEdit" v-for="(item) in roomPurpose" class="purposeInputDiv">{{item.serialNumber}}</div>
      </td>
      <td v-bind:class="{purposeInp: data.isEdit}">
        <div class="purposeInputDiv initVal" v-for="(item, key) in roomPurpose" :key="key" v-if="data.isEdit">
          <input type="text" class="tableInpText" @keydown="validateStartCounterVal" @keyup="validateStartCounterKeyUp"
                 placeholder="123456,78" v-bind:value="item.startCounterValue"
                 pattern="^\d{6},\d{2}$">
        </div>
        <div v-if="!data.isEdit" v-for="(item) in roomPurpose" class="purposeInputDiv">{{item.startCounterValue}}</div>
      </td>

      <td>
        <div class="btnPart">
          <label>
            <div class="greenBtn btnDiv" v-bind:class="{disabled:!data.isEdit}">{{ $t('buttons.addPhoto')}}</div>
            <input type="file" class="tableInpFile" @change="addPhoto" accept="application/pdf,image/*" multiple v-bind:disabled="!data.isEdit">
          </label>
          <button class="blueBtn" v-bind:disabled="!data.isPhotos" @click="showAllPhotos">{{ $t('buttons.viewPhoto')}}</button>
        </div>
      </td>
      <td>
        <div class="btnPart">
          <button class="yellowBtn" @click="save" v-bind:disabled="!data.isEdit">{{ $t('buttons.save')}}</button>
          <button class="greyBtn" @click="edit" v-bind:disabled="data.isEdit">{{ $t('buttons.edit')}}</button>
        </div>
      </td>
      <slider v-if="showPhotos" v-bind:photos="urls" @closeSlider="closeSlider" @isPhoto="data.isPhotos = false"></slider>
    </tr>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import slider from "./slider.vue"
export default {
  props:['flatNumber'],
  name: "appTrP6",
  computed: mapGetters(['getEntrance','getFlatType','getCof','getApartments', 'getFloors','getDataFlat', 'getRoomPurpose']),
  mounted() {
    this.roomPurpose.push({room:this.$t('objectRegistration3.purposeRoomTitle')});
  },
  data(){
    return{
      data:{isEdit: true,
            isSaved:false,
            isPhotos: false,
            objectPhoto:[],
            purposeRoom:[]
      },
      urls:["https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Felis_silvestris_lybica_1.jpg/275px-Felis_silvestris_lybica_1.jpg",
        "https://icdn.lenta.ru/images/2020/07/13/16/20200713161106967/square_320_4dd6db2859b29f4b1ba2d684155a5cc6.png",
        "https://sib.fm/storage/article/February2020/Z0tp5pg7QDhkIZ06GKhM.jpg"],
      showPhotos: false,
      roomPurpose:[],
      floorLengthLimit: 1,
      counterLengthLimit: 11,
      startCounterValLimit: 8,
      cofLengthLimit: 4,
      isFirstRoom: true
    }
  },
  methods:{
    ...mapActions(['saveFlatData']),
    ...mapMutations(["changeIsAllFilled1"]),
    cofKeyDown(e){
      if(e.key == "Backspace" || e.key == ","|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.cofLengthLimit){
        e.preventDefault();
      }
    },cofKeyUp(e){
      if(e.target.value.length == 2){
        if(e.key === "Backspace") return
        e.target.value += ",";
      }
    },
    addRoomPurpose(e){
      for(var k = 0; k < this.roomPurpose.length; k ++){
        if(this.roomPurpose[k].room == e.target.value)
          return
      }
      if(this.isFirstRoom){
        this.roomPurpose.splice(0,1);
        this.roomPurpose.push({room:e.target.value});
        this.isFirstRoom = false;
      }else{
        this.roomPurpose.push({room:e.target.value});
      }
    },
    removeFromRoomPurpose(e){
      if(this.roomPurpose.length == 1) return
      this.roomPurpose.splice(e.target.value,1);
    },
    save(e){
      var parent = e.target.closest("tr");
      var inputs = parent.querySelectorAll("input[type=text]");
      var result = true;

      for( var k = 0; k < inputs.length; k ++){
        if(!inputs[k].value.match(inputs[k].pattern)){
          inputs[k].style.border = "1px solid red";
          this.removeRedBorder(inputs[k]);
          result = false
        }
      }

      if(result){
        var purposeDiv = e.target.closest("tr").querySelectorAll(".purposeDiv");
        var kc = e.target.closest("tr").querySelectorAll(".kc");
        var kq = e.target.closest("tr").querySelectorAll(".kq");
        var serialNum = e.target.closest("tr").querySelectorAll(".serialNum");
        var initVal = e.target.closest("tr").querySelectorAll(".initVal");
        var data = {};
        var counter = 0;

        for(var k = 0; k < this.getRoomPurpose.length; k ++){
          if(purposeDiv[0].innerText != this.getRoomPurpose[k]){
            counter ++;
            if(counter == 15){
              purposeDiv[0].style.color = "red";
              this.removeRedBorder(purposeDiv[0]);
              return
            }
          }
        }
        for(var k = 0; k < this.roomPurpose.length; k ++){
          data = {
            room: purposeDiv[k].innerText,
            kc: kc[k].querySelector("input[type=text]").value,
            kq: kq[k].querySelector("input[type=text]").value,
            serialNumber: serialNum[k].querySelector("input[type=text]").value,
            startCounterValue: initVal[k].querySelector("input[type=text]").value
          }
          this.roomPurpose[k] = data;
        }

        var obj = {
          flatNumber: parent.children[0].innerText,
          entrance: parent.children[1].children[0].value,
          floor: parent.children[2].children[0].value,
          flatType: parent.children[3].children[0].value,
          cof: parent.children[4].children[0].value,
          purposeRoom: this.roomPurpose,
          objectPhoto: this.data.objectPhoto
        }

        this.data = Object.assign(this.data, obj);
        this.saveFlatData(this.data);
        this.isEdit = false;
        if(this.getApartments.length === this.getDataFlat.length) this.changeIsAllFilled1(true);
      }
    },
    removeRedBorder(el){
      setTimeout(function (){
        el.style.border = "";
        el.style.color = "";
      },1000)
    },
    addPhoto(e){
      var files = e.target.files;

      for(var k = 0; k < files.length; k ++){
        this.data.objectPhoto.push(files[k]);
      }
      this.data.isPhotos = true;
    },
    edit(){
      this.data.isEdit = true;
      this.changeIsAllFilled1(false);
    },
    validateCounter(e){
      if(e.key == "Backspace"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.counterLengthLimit){
        e.preventDefault();
      }
    },
    validateStartCounterVal(e){
      if(e.key == "Backspace" || e.key == ","|| e.key === "ArrowLeft" || e.key === "ArrowRight"){
        return
      } else if(isNaN(e.key) || e.key == " " || e.target.value.length > this.startCounterValLimit){
        e.preventDefault();
      }
    },
    validateStartCounterKeyUp(e){
      if(e.target.value.length == 6){
        if(e.key === "Backspace") return
        e.target.value += ",";
      }
    },
    showAllPhotos(){
      this.showPhotos = true;
    },
    closeSlider(){
      this.showPhotos = false;
    }
  },
  components:{slider:slider}
}
</script>


<style scoped>
*{
  font-size: 18px;
}
.appTr{

}

table,td,th{
  border: 1px solid #3c3a3a;
  border-collapse: collapse;
  padding: 3px;
  text-align: center;
  font-size: 15px;
}

th{
  font-size: 15px;
}
td,th{
  height: 30px;
  overflow: hidden;
}
td{
  padding: 0;
}
td:nth-child(11){
  padding: 0;
}
td:nth-child(12){
  padding: 0;
}
.selectTd{
}

table input[type=text],table input[type=text].purposeInput{
  width: 130px;
  margin: 0;
  padding: 2px;
  padding-left: 5px;
  font-size: 16px;
  outline: none;
  border-radius: 2px;
  border: 1px solid #3c3a3a;
}
table input[type=text].purposeInput{
  width: 50px;
}
table input[type=text].purposeInput:first-child{
  /*margin-top: 10px;*/
}

table input[type=file]{
  width: 103px;
  border: 1px solid #3c3a3a;
  outline: none;
}
/*buttons*/
button{
  border: none;
  outline: none;
  text-decoration: none;
  background: none;
  cursor: pointer;
}
.yellowBtn,.greenBtn,.greyBtn,.blueBtn{
  width: 100%;
  height: 28px;
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
.btnDiv{
  width: 100%;
  height: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 15px;
  text-align: center;
  border-radius: 0px;
}

/*inputs*/

.tableInpText{

}

.tableSelect{
  display: block;
  outline: none;
  border: 1px solid #b3e5fc;
  width: 100%;
  height: 100%;
  font-size: 16px;
  text-align: center;
  cursor: pointer;
  text-align-last:center;
  background-color: #b3e5fc;
}
.tableSelectPurpose{
  display: block;
  outline: none;
  border: 1px solid #b3e5fc;
  width: 100%;
  height: auto;
  font-size: 16px;
  text-align: center;
  cursor: pointer;
  text-align-last:center;
  margin-bottom: 5px;
  background-color: #b3e5fc;
  border-bottom: 1px solid #3c3a3a;
}

.tableInpFile{
  display: none;
}
.disabled{
  /*color: #616161;*/
  /*background-color: #86f776;*/
  opacity: 0.5;
}
.purposeDiv{
  border-bottom: 1px solid #3c3a3a;
  height: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 5px;
}
.purposeDiv:first-child{

}
.purposeDiv:last-child{
  border-bottom: none;
}
.removeRoomPurpose{
  background-image: url("../../assets/icons/deleted24.png");
  width: 20px;
  height: 20px;
  background-size: 100% 100%;
  margin-right: 5px;
  opacity: 0.7;
}
.removeRoomPurpose:hover{
  opacity: 1;
}

.purposeInp{
  padding-top: 26px;
}
.purposeInputDiv{
  border-bottom: 1px solid #3c3a3a;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.purposeInputDiv:last-child{
  border-bottom: none;
}
</style>