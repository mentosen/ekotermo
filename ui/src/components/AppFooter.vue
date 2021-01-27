<template>
  <footer class="footer">
    <div class="footer__wrap tile__row--flex-end">
      <button-link-footer-component
              class="feedback"
              :name="$t('homePage.feedback')"
              @click=""
      />
    </div>
  </footer>
</template>

<script>
  import ButtonLinkFooterComponent from "@/components/common/ButtonLinkFooterComponent";

  export default {
    components:{ButtonLinkFooterComponent},
    name: "app-footer",
  };
</script>
<style scoped>
</style>
