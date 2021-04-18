<template>
  <tr>
    <td>{{data.paymentNumber}}</td>
    <td>{{data.paymentDate}}</td>
    <td>{{data.paymentFrom}}</td>
    <td>{{data.paymentTo}}</td>
    <td width="190px">
      <div class="btnPart">
        <button class="greenBtn" @click="redirect">{{$t('buttons.goToPage')}}</button>
        <button class="yellowBtn" @click="download">{{$t('buttons.downloadCalculation')}}</button>
      </div>
    </td>
    <td width="80px">
      <div class="btnPart">
        <button class="redBtn" @click="showConfirmPopUp">{{$t('buttons.delete')}}</button>
      </div>
    </td>
    <downloadPopUp v-if="isShowDownloadPopUp" @cancelDownloadPopUp="hideDownloadPopUp"></downloadPopUp>
    <confirmPopUp v-if="isShowConfirmPopUp" @cancelConfirmPopUp="hideConfirmPopUp"></confirmPopUp>
  </tr>
</template>

<script>
import downloadPopUp from "@/components/objectDistributionArchive/downloadPopUp";
import confirmPopUp from './confirmPopUp';

export default {
  name: "ODATr",
  props:["data"],
  components:{downloadPopUp:downloadPopUp, confirmPopUp:confirmPopUp},
  data(){
    return{
      isShowDownloadPopUp:false,
      isShowConfirmPopUp: false
    }
  },
  methods:{
    redirect(){

    },
    download(){
      this.isShowDownloadPopUp = true;
    },
    hideDownloadPopUp(){
      this.isShowDownloadPopUp = false;
    },
    showConfirmPopUp(){
      this.isShowConfirmPopUp = true;
    },
    hideConfirmPopUp(){
      this.isShowConfirmPopUp = false;
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
}
td:nth-child(5){
  padding: 0;
}
td:nth-child(6){
  padding: 0px 0px 0px 0px;
}

.yellowBtn,.greenBtn,.redBtn{
  width: 100%;
  height: 25px;
  padding: 3px;
  border: 1px solid #f9ed17;
  background-color: yellow;
  font-size: 15px;
  cursor: pointer;
  outline: none;
}
.redBtn{
  height: 50px;
  background-color: #ea4c4c;
  border: 1px solid #ef1c1c;
}

.greenBtn{
  background-color: #13f113;
  border: 1px solid #1bd400;
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

</style>