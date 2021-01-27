<template>
    <div class="tile__row--paddings tile__row--background">
        <div class="tile__row">
            <span>{{$t('registration.registration')}}</span>
            <button class="btn btn-link" @click.prevent="close">{{$t('common.back')}}</button>
        </div>
        <div class="tile__row tile__row--flex-center">
            <!--auth block-->
            <div class="tile__row tile__row--column-direction tile__row--margin-right">
                <!--login*-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.login')"
                        :is-error="!!errors.first('input_login')"
                >
                    <input
                            type="text"
                            :placeholder="$t('registration.login')"
                            v-model="form.login"
                            ref="input_login"
                            name="input_login"
                            autocomplete='off'
                            v-validate="rules.login"
                            id="input_login"
                    />
                    <span v-if="!!errors.first('input_login')" class="form__error">{{form.login === '' ? $t('errors.emptyField') : $t('errors.registration.login')}}</span>
                </float-label>

                <!--password*-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.password')"
                        :is-error="!!errors.first('input_password')"
                >
                    <input
                            type="password"
                            :placeholder="$t('registration.password')"
                            v-model="form.password"
                            ref="input_password"
                            name="input_password"
                            autocomplete='off'
                            v-validate="rules.password"
                            id="input_password"
                    />
                    <span v-if="!!errors.first('input_login')" class="form__error">{{form.login === '' ? $t('errors.emptyField') : $t('errors.registration.login')}}</span>
                </float-label>

                <!--repeat password*-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.repeatPassword')"
                        :is-error="!!errors.first('input_repeat_password')"
                >
                    <input
                            :placeholder="$t('registration.repeatPassword')"
                            v-model="form.repeat_password"
                            ref="input_repeat_password"
                            name="input_repeat_password"
                            autocomplete='off'
                            v-validate="rules.repeat_password"
                            id="input_repeat_password"
                    />
                    <span v-if="!!errors.first('input_repeat_password')" class="form__error">{{form.login === '' ? $t('errors.emptyField') : $t('errors.registration.login')}}</span>
                </float-label>

                <!--email-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.email')"
                        :is-error="!!errors.first('input_email')"
                >
                    <input
                            :placeholder="$t('registration.email')"
                            v-model="form.email"
                            ref="input_email"
                            name="input_email"
                            autocomplete='off'
                            v-validate="rules.email"
                            id="input_email"
                    />
                    <span v-if="!!errors.first('input_email')" class="form__error">{{form.login === '' ? $t('errors.emptyField') : $t('errors.registration.email')}}</span>
                </float-label>

                <!--phone* vue-tel-component-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.phone')"
                        :is-error="!!errors.first('input_phone')"
                >
                    <input
                            :placeholder="$t('registration.phone')"
                            v-model="form.phone"
                            ref="input_phone"
                            name="input_phone"
                            autocomplete='off'
                            v-validate="rules.phone"
                            id="input_phone"
                    />
                    <span v-if="!!errors.first('input_phone')" class="form__error">{{form.phone === '' ? $t('errors.emptyField') : $t('errors.registration.phone')}}</span>
                </float-label>

                <!--access code-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.accessCode')"
                        :is-error="!!errors.first('input_accessCode')"
                >
                    <input
                            :placeholder="$t('registration.accessCode')"
                            v-model="form.accessCode"
                            ref="input_accessCode"
                            name="input_accessCode"
                            autocomplete='off'
                            v-validate="rules.accessCode"
                            id="input_accessCode"
                    />
                    <span v-if="!!errors.first('input_accessCode')" class="form__error">{{form.accessCode === '' ? $t('errors.emptyField') : $t('errors.registration.accessCode')}}</span>
                </float-label>
            </div>

        </div>
        <div class="tile__row tile__row--flex-end shadow--top">
            <button class="btn" @click.prevent="submit">{{$t('registration.registration')}}</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BillingRegistrationComponent",
        data(){
            return {
                form: {
                    login:'',
                    password:'',
                    doublePassword:'',
                    email:'',
                    phone:'',
                    accessCode:'',
                },

                rules: {
                    login: 'required|min:3|max:30',
                    password: 'required|min:8',
                    doublePassword: 'required|min:8',
                    email:"required|email",
                    phone:"required|min:13|maw:13",
                    accessCode:"required"
                },
            }
        },
        methods: {
            close(){
                this.$router.push('/')
            },
            submit(){

            }
        }
    }
</script>

<style scoped>

</style>