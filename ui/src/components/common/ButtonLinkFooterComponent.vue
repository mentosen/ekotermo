<template>
    <button class="btn btn-margin btn-link-footer" @click="click">{{name}}</button>
</template>

<script>
    export default {
        name: "ButtonComponent",
        props:{
            name:{
                type: String,
                required: true
            }
        },
        methods: {
            click(){
                this.$emit('click');
            }
        }
    }
</script>

<style scoped>

</style>