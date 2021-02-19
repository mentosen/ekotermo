<template>
    <div class="tile__row--paddings tile__row--background">
        <div class="tile__row">
            <span>{{$t('registration.registration')}}</span>
            <button class="btn btn-link" @click.prevent="close">{{$t('common.back')}}</button>
        </div>
        <div  class="tile__row tile__row--flex-center">
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
            </div>

            <div class="tile__row tile__row--column-direction">
                <!--city-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.city')"
                        :is-error="!!errors.first('input_city')"
                >
                    <input
                            type="text"
                            :placeholder="$t('registration.city')"
                            v-model="form.city"
                            ref="input_city"
                            name="input_city"
                            autocomplete='off'
                            v-validate="rules.city"
                            id="input_city"
                    />
                    <span v-if="!!errors.first('input_city')" class="font--error">
                        {{form.city === '' ? $t('errors.emptyField') : $t('errors.incorrectField')}}
                    </span>
                </float-label>

                <!--street-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.street')"
                        :is-error="!!errors.first('input_street')"
                >
                    <input
                            type="text"
                            :placeholder="$t('registration.street')"
                            v-model="form.street"
                            ref="input_street"
                            name="input_street"
                            autocomplete='off'
                            v-validate="rules.street"
                            id="input_street"
                    />
                    <span v-if="!!errors.first('input_street')" class="font--error">
                        {{form.street === '' ? $t('errors.emptyField') : $t('errors.incorrectField')}}
                    </span>
                </float-label>

                <!--building-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.building')"
                        :is-error="!!errors.first('input_building')"
                >
                    <input
                            type="number"
                            :placeholder="$t('registration.building')"
                            v-model="form.building"
                            ref="input_building"
                            name="input_building"
                            autocomplete='off'
                            v-validate="rules.building"
                            id="input_building"
                    />
                    <span v-if="!!errors.first('input_building')" class="font--error">
                        {{form.building === '' ? $t('errors.emptyField') : $t('errors.incorrectField')}}
                    </span>
                </float-label>

                <!--apartment -->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.apartment')"
                        :is-error="!!errors.first('input_apartment')"
                >
                    <input
                            type="number"
                            :placeholder="$t('registration.apartment')"
                            v-model="form.apartment"
                            ref="input_apartment"
                            name="input_apartment"
                            autocomplete='off'
                            v-validate="rules.apartment"
                            id="input_apartment"
                    />
                    <span v-if="!!errors.first('input_apartment')" class="font--error">
                        {{form.apartment  === '' ? $t('errors.emptyField') : $t('errors.incorrectField')}}
                    </span>
                </float-label>

                <!--serialNumber-->
                <float-label
                        :dispatch="true"
                        :read-only="false"
                        :fixed="false"
                        :label="$t('registration.serialNumber')"
                        :is-error="!!errors.first('input_serialNumber')"
                >
                    <input
                            type="number"
                            :placeholder="$t('registration.serialNumber')"
                            v-model="form.serial_number"
                            ref="input_serialNumber"
                            name="input_serialNumber"
                            autocomplete='off'
                            v-validate="rules.serial_number"
                            id="input_serialNumber"
                    />
                    <span v-if="!!errors.first('input_serialNumber')" class="font--error">
                        {{form.serial_number === '' ? $t('errors.emptyField') : $t('errors.incorrectField')}}
                    </span>
                </float-label>
            </div>
        </div>
        <div  class="tile__row tile__row--flex-end shadow--top">
            <button class="btn" @click.prevent="submit">{{$t('registration.registration')}}</button>
            <button class="btn" @click="testUser">Fill in test user</button>
        </div>
    </div>
</template>

<script>
    import {signup} from '@/api/auth';
    import {findDuplicate} from '@/api/user';
    import { setToken } from "@/utils/auth";

    export default {
        name: "ClientRegistrationComponent",
        data(){
            return {
                form: {
                    login:'',
                    password:'',
                    repeat_password:'',
                    email:'',
                    phone:'',

                    city:'',
                    street:'',
                    building:'',
                    apartment:'',

                    serial_number:'',
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

                    city:"required",
                    street:"required",
                    building:"required",
                    apartment:"required",

                    serial_number:"required"
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
                        debugger
                        signup(form).then(response => {
                            debugger
                            setToken(response.data.access_token);
                            that.$store.commit("SET_TOKEN", response.data.access_token);
                            that.$store.dispatch("GetUserInfo").then(() => {
                                that.currentUser = that.$store.getters.getUserInfo;
                                this.$router.push('/')
                                    });
                            debugger
                        }).catch((e) => {
                            debugger
                            console.log(e.message);
                        });
                    }
                });
            },
            testUser(){
                let that = this;
                debugger
                that.form = {
                    login:'000',
                        password:'00000000',
                        repeat_password:'00000000',
                        email:'mentosentrue0'+ that.getRandomIntInclusive(0,10)+'@gmail.com',
                        phone:'380688982769',
                        city:'000',
                        street:'000',
                        building:'000',
                        apartment:'000',
                }
            },
            getRandomIntInclusive(min, max) {
                min = Math.ceil(min);
                max = Math.floor(max);
                return Math.floor(Math.random() * (max - min + 1)) + min; //Максимум и минимум включаются
            },
            // setCurrentUser: function (authUser) {
            //     this.$store.commit("SET_TOKEN", authUser.access_token);
            //     this.$store.dispatch("GetUserInfo").then(() => {
            //         this.currentUser = this.$store.getters.getUserInfo;
            //     });
            //     if (
            //         !this.showTicketForm &&
            //         this.currentUser.email &&
            //         this.currentUser.phone
            //     ) {
            //         this.showTicketForm = true;
            //     }
            // },

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
