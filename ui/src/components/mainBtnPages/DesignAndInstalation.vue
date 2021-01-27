<template>
    <div class="tile__row tile__row--column-direction tile__row--align-start">
        <span style="color: red" class="font--description">{{$t('designAndInstall.title')}}</span>
        <br/>
        <span class="font--description">{{$t('designAndInstall.description')}}</span>
        <br/>
        <span class="font--description">{{$t('designAndInstall.price')}}</span>
        <br/>
        <span style="color: red" class="font--description">{{$t('designAndInstall.description1')}}</span>
        <br/>
        <span class="font--description">{{$t('designAndInstall.footer')}}</span>
        <br/>
        <span style="text-decoration: underline; font-weight: 600;" class="font--description">{{$t('common.end')}}</span>
        <br/>
        <span style="color: red" class="font--description">{{$t('common.footer')}}</span>
        <span style="text-decoration: underline; color: red" class="font--description">{{$t('common.phone')}}</span>
    </div>
</template>

<script>
    export default {
        name: "Design and Instalation"
    }
</script>

<style scoped>

</style>