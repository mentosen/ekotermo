<template>
    <div class="tile__row tile__row--column-direction tile__row--align-start">
        <span class="font--description">{{$t('contacts.country')}}</span>
        <br/>
        <span class="font--description">{{$t('contacts.region')}}</span>
        <br/>
        <span class="font--description">{{$t('contacts.city')}}</span>
        <br/>
        <span class="font--description">{{$t('contacts.street')}}</span>
        <br/>
        <span class="font--description">{{$t('contacts.apartment')}}</span>
        <br/>
        <span class="font--description">{{$t('contacts.phone')}}</span>
    </div>
</template>

<script>
    export default {
        name: "Contacts"
    }
</script>

<style scoped>

</style>