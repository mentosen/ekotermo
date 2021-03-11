<template>
  <footer class="footer">
    <div class="footer__wrap tile__row--flex-end">
      <feedback v-if="isShowFeedback"></feedback>
      <button-link-footer-component
              class="feedback"
              :name="$t('homePage.feedback')"
              @click="showFeedback"
      />
    </div>
  </footer>
</template>

<script>
  import ButtonLinkFooterComponent from "@/components/common/ButtonLinkFooterComponent";
  import feedback from "@/components/feedback/feedback";
  import {mapMutations, mapGetters} from "vuex";

  export default {
    components:{ButtonLinkFooterComponent,feedback:feedback},
    name: "app-footer",
    computed:mapGetters(["isShowFeedback"]),
    methods:{
      ...mapMutations(["changeIsShowFeedback"]),
      showFeedback(){
        this.changeIsShowFeedback(true);
      }
    }
  };
</script>
<style scoped>
</style>
