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
                        :dispatch="false"
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
                            @input="removeCyrillic"
                            @change="findDuplicate"
                    />
                    <span v-if="!!errors.first('input_login') || isLoginExists" class="font--error">
                        {{form.login === '' ? $t('errors.emptyField') : isLoginExists ? $t('errors.registration.loginExists') : $t('errors.registration.login')}}
                    </span>
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
                    <span v-if="!!errors.first('input_password')" class="font--error">
                        {{form.password === '' ? $t('errors.emptyField') : $t('errors.registration.password')}}
                    </span>
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
                            type="password"
                            :placeholder="$t('registration.repeatPassword')"
                            v-model="form.repeat_password"
                            ref="input_repeat_password"
                            name="input_repeat_password"
                            autocomplete='off'
                            v-validate="rules.repeat_password"
                            id="input_repeat_password"
                            @input="checkPasswords"
                    />
                    <span v-if="!!errors.first('input_repeat_password') || !isPasswordsEquals" class="font--error">
                        {{form.repeat_password === '' ? $t('errors.emptyField') : isPasswordsEquals ? $t('errors.registration.password') : $t('errors.registration.repeatPassword')}}
                    </span>
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
                            @change="findDuplicate"
                    />
                    <span v-if="!!errors.first('input_email') || isEmailExists" class="font--error">
                        {{form.email === '' ? $t('errors.emptyField') : isEmailExists ? $t('errors.registration.emailExists') : $t('errors.incorrectField')}}
                    </span>
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
                            type="number"
                            :placeholder="$t('registration.phone') "
                            v-model="form.phone"
                            ref="input_phone"
                            name="input_phone"
                            autocomplete='off'
                            v-validate="rules.phone"
                            id="input_phone"
                            @change="findDuplicate"
                    />
                    <span v-if="!!errors.first('input_phone') || isPhoneExists" class="font--error">
                        {{form.phone === '' ? $t('errors.emptyField') : isPhoneExists ? $t('errors.registration.phoneExists') : $t('errors.incorrectField')}}
                    </span>
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
    import {signup} from '@/api/auth';
    import {findDuplicate} from '@/api/user';
    import { setToken } from "@/utils/auth";

    export default {
        name: "BillingRegistrationComponent",
        data(){
            return {
                form: {
                    login:'',
                    password:'',
                    repeat_password:'',
                    email:'',
                    phone:'',
                    accessCode:'',
                },
                isLoginExists: false,
                isEmailExists: false,
                isPhoneExists: false,
                isPasswordsEquals: false,

                rules: {
                    login: 'required|min:3|max:30',
                    password: 'required|min:8',
                    repeat_password: 'required|min:8',
                    email:"email",
                    phone:"required|min:12|max:12",
                    accessCode:"required"
                },
            }
        },
        methods: {
            close(){
                this.$router.push('/')
            },
            handleValidationErrorBasic() {
                const firstField = Object.keys(this.errors.collect())[0];
                if(typeof firstField !== 'undefined'){
                    this.$refs[firstField].focus();
                }
            },
            checkPasswords(){
                let that = this;
                that.isPasswordsEquals = that.form.password === that.form.repeat_password
            },
            removeCyrillic(){
                this.form.login = this.form.login.replace(/[а-яА-ЯЁё]/, '');
            },
            addPlus(form) {
                if(!form.phone.includes('+')){
                    form.phone = '+' + form.phone;
                }
            },
            submit(){
                let that = this;

                that.$validator.validate().then(valid => {
                    if (!valid || that.isLoginExists || that.isEmailExists || that.isPhoneExists || !that.isPasswordsEquals) {
                        return that.handleValidationErrorBasic();
                    } else {
                        let form = {...that.form};
                        that.addPlus(form);
                        signup(form).then(response => {
                            debugger
                            setToken(response.data.access_token);
                            that.$store.commit("SET_TOKEN", response.data.access_token);
                            that.$store.dispatch("GetUserInfo").then(() => {
                                that.currentUser = that.$store.getters.getUserInfo;
                                this.$router.push('/billingMainPage')
                            });
                            debugger
                        }).catch((e) => {
                            debugger
                            console.log(e.message);
                        });
                    }
                });
            },
            findDuplicate(){
                let that = this;

                let checkDto = {
                    login: that.form.login,
                    email: that.form.email,
                    phone: that.form.phone
                }

                findDuplicate(checkDto).then(response => {
                    that.isLoginExists = response.data.is_login_exists;
                    that.isEmailExists = response.data.is_email_exists;
                    that.isPhoneExists = response.data.is_phone_exists;
                })
            }
        }
    }
</script>

<style scoped>

</style>
