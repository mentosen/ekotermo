<template>
    <div :class="['vfl-has-label', { 'vfl-has-label--shadow': isFocused, 'vfl-has-label--no-border' : readOnly, 'vfl-has-label--error' : isError} ]">
        <label class="vfl-label" :class="classObject" :for="inputId">
            {{ floatLabel }}
        </label>
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: 'float-input',
        props: {
            on: {
                type: Boolean,
                default: true
            },
            fixed: {
                type: Boolean,
                default: false
            },
            label: {
                type: String,
                default: ''
            },
            dispatch: {
                type: Boolean,
                default: true
            },
            for: {
                type: String,
                default: null
            },
            readOnly: {
                type: Boolean,
                default: false
            },
            isError: {
                type: Boolean,
                default: false
            }
        },
        data () {
            return {
                formEl: undefined,
                labelEl: undefined,
                isActive: false,
                isFocused: false,
                prevPlaceholder: ''
            }
        },
        mounted () {
            this.formEl = this.$el.querySelector('input, textarea, select')
            this.formEl.addEventListener('input', this.updateIsActive)
            this.formEl.addEventListener('input', this.updateIsFocused)
            this.formEl.addEventListener('blur', this.updateIsFocused)
            this.formEl.addEventListener('focus', this.updateIsFocused)
            if (!this.for) {
                this.labelEl = this.$el.querySelector('label')
                this.labelEl.addEventListener('click.prevent', this.focusFormEl)
            }
            this.dispatchInput()
        },
        beforeDestroy () {
            this.formEl.removeEventListener('input', this.updateIsActive)
            this.formEl.removeEventListener('input', this.updateIsFocused)
            this.formEl.removeEventListener('blur', this.updateIsFocused)
            this.formEl.removeEventListener('focus', this.updateIsFocused)
        },
        methods: {
            dispatchInput () {
                if (this.dispatch) {
                    const event = document.createEvent('HTMLEvents')
                    event.initEvent('input', true, false)
                    this.formEl.dispatchEvent(event)
                }
            },
            focusFormEl () {
                this.formEl.focus()
            },
            updateIsFocused (e) {
                this.isFocused = e.target === document.activeElement;
                this.isActive = this.isFocused || e.target.value.length > 0;
                this.prevPlaceholder = e.target.placeholder !== '' ? e.target.placeholder : this.prevPlaceholder;
                e.target.placeholder = this.isFocused ? '' : this.prevPlaceholder;
            },
            updateIsActive (e) {
                this.isActive = this.isFocused || e.target.value.length > 0;
            }
        },
        computed: {
            inputId () {
                return this.for
            },
            classObject () {
                return {
                    'vfl-label-on-input': this.on && (this.isActive || this.fixed),
                    'vfl-label-on-focus': this.isFocused && !this.isError,
                    'vfl-label-on-error': this.isError,
                }
            },
            formElType () {
                return this.formEl ? this.formEl.tagName.toLowerCase() : ''
            },
            floatLabel () {
                if (this.label) return this.label
                switch (this.formElType) {
                    case 'input':
                    case 'textarea':
                        return this.formEl.placeholder
                    case 'select':
                        return this.formEl.querySelector('option[disabled][selected]').innerHTML
                    default:
                        return ''
                }
            }
        }
    }
</script>

<style lang="scss">
    .vfl-has-label {
        width: 100%;
        min-height: 71px;
        text-align: left;
        padding: 20px 8px;
        padding-bottom: 0;
        position: relative;
        &--shadow {
            box-shadow: 0px 2px 10px rgba(3, 145, 81, 0.2);
            border-radius: 4px;
        }
        input, textarea, select {
            width: 100%;
            background-color: inherit;
            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 18px;
            line-height: 120%;
            border: none;
            border-bottom: 1px solid #BCC0BC;
            &:focus {
                border-color: #00B66B;
            }
        }
        &--no-border {
            width: 100%;
            input, textarea, select {
                border: none;
                cursor: default;

                &:disabled {
                    color: inherit;
                }
            }
        }
        &--error {
            box-shadow: 0px 2px 10px rgba(252, 114, 84, 0.2);
            border-radius: 4px;
            input, textarea, select {
                width: 100%;
                border-color: #FF7F6E;
                margin:  0;
                cursor: default;

                &:focus {
                    border-color: #FF7F6E;
                }
                &:disabled {
                    color: inherit;
                }
            }
        }
    }
    .vfl-label {
        position: absolute;
        top: 0;
        right: 0;
        left: 0.1em;
        overflow: hidden;
        color: #aaa;
        text-overflow: ellipsis;
        white-space: nowrap;
        pointer-events: none;
        opacity: 0;
        transition: all 0.2s ease-out;
        font-family: Roboto;
        font-style: normal;
        font-weight: 300;
        font-size: 12px;
        line-height: 120%;
        /* or 14px */

        letter-spacing: 0.5px;
    }
    .vfl-label-on-input {
        padding-left: 8px;
        padding-top: 4px;
        pointer-events: all;
        opacity: 1;
    }
    .vfl-label-on-focus {
        color: #00B66B;
    }
    .vfl-label-on-error {
        color: #FF7F6E;
    }

    .vfl-has-label input:focus,
    .vfl-has-label textarea:focus,
    .vfl-has-label select:focus {
        outline: 0;
    }

    /* Chrome, Safari, Edge, Opera */
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    /* Firefox */
    input[type=number] {
        -moz-appearance: textfield;
    }
</style>
