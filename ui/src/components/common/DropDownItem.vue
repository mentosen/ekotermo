<template>
    <li class="dropdown__item" @click="startAction">
        {{ name }}
    </li>
</template>
<script>
    export default {
        name: "DropdownItem",
        props: {
            name: {
                type: String,
                required: true
            }
        },
        methods: {
            startAction: function () {
                this.$parent.closeHandler();
                this.$emit('start-action')
            }
        }
    };
</script>
