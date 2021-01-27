<template>
    <div>
        <div class="tile__row tile__row--column-direction tile__row--padding-top">
            <div class="tabs">
                <button
                        v-for="tab in tabs"
                        :key="tab"
                        :class="['tab__button', { 'tab__button--active': currentTab === tab }]"
                        @click="currentTab = tab"
                >{{ $t('registration.tabs.'+tab)}}
                </button>
            </div>
            <div v-if="currentTab === 'client'" style="width: 50%">
                <client-registration-component/>
            </div>
            <div v-else style="width: 30%">
                <billing-registration-component/>
            </div>
        </div>

    </div>
</template>

<script>
    import ClientRegistrationComponent from "./ClientRegistrationComponent";
    import BillingRegistrationComponent from "./BillingRegistrationComponent";

    export default {
        name: "RegistrationContainer",
        components:{ClientRegistrationComponent, BillingRegistrationComponent},
        data() {
            return {
                tabs: ['client', 'billing'],
                currentTab: 'client',
            }
        }
    }
</script>

<style lang="scss" scoped>
    @import "../../assets/styles/global/variables";

    .tabs {
        display: flex;
        justify-content: space-between;
        margin-bottom: var(--indent);

        .tab__button {
            width: 100%;
            height: 54px;
            background: none;
            border: none;
            color: $secondary-text-color;
            border-bottom: 2px solid $secondary-text-color;
            font-size: 22px;
            font-weight: 500;
            margin-right: var(--indent);

            &:hover {
                cursor: pointer;
                transition: 0.3s;
                color: darken($secondary-text-color, 10%);
                border-color: darken($secondary-text-color, 10%);
            }

            &--active {
                border-bottom: 3px solid #009357;
                color: #009357;

                &:hover {
                    color: #009357;
                    border-color: #009357;
                }
            }
        }
    }
</style>