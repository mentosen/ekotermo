<template>
    <div class="dropdown">
    <span
            class="dropdown__trigger"
            @click="closeHandler"
            v-if="icon === 'dots'"
    >
      <svg
              width="4"
              height="16"
              viewBox="0 0 4 16"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
      >
        <path
                d="M2 4C3.1 4 4 3.1 4 2C4 0.9 3.1 0 2 0C0.9 0 0 0.9 0 2C0 3.1 0.9 4 2 4ZM2 6C0.9 6 0 6.9 0 8C0 9.1 0.9 10 2 10C3.1 10 4 9.1 4 8C4 6.9 3.1 6 2 6ZM2 12C0.9 12 0 12.9 0 14C0 15.1 0.9 16 2 16C3.1 16 4 15.1 4 14C4 12.9 3.1 12 2 12Z"
                fill="#828282"
        />
      </svg>
    </span>
        <template v-else><slot name="label"></slot><span class="dropdown__trigger  dropdown__trigger--arrow" @click="closeHandler"></span></template>
        <ul  class="dropdown__list" v-if="isOpen"  v-on-clickaway="closeDrop">
            <slot></slot>
        </ul>
    </div>
</template>
<script>
    import { mixin as clickaway } from 'vue-clickaway';
    export default {
        mixins: [ clickaway ],
        name: "Dropdown",
        props: {
            icon: {
                type: String,
                required: true,
                default: 'dots'
            }
        },
        data() {
            return {
                isOpen: false
            };
        },
        methods: {
            closeHandler: function() {
                this.isOpen = !this.isOpen;
            },

            closeDrop() {
                this.isOpen = false;
            },
        }
    };
</script>
<style lang="scss" scoped>
</style>
