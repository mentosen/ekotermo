<template>
    <div class="tile__row tile__row--flex-center tile__row--margin-top-x8">
        <div class="tile__row--paddings tile__row--background tile__row--width30percent tile__row--flex-center">
            <div class="tile__row">
                <span>{{$t('common.login')}}</span>
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
                                v-model="login"
                                ref="input_login"
                                name="input_login"
                                autocomplete='off'
                                id="input_login"
                                v-validate="rules.login"
                        />
                        <span v-if="!!errors.first('input_login')" class="font--error">
                            {{$t('errors.emptyField')}}
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
                                v-model="password"
                                ref="input_password"
                                name="input_password"
                                autocomplete='off'
                                id="input_password"
                                v-validate="rules.password"
                        />
                        <span v-if="!!errors.first('input_password')" class="font--error">
                            {{$t('errors.emptyField')}}
                        </span>
                    </float-label>
                </div>
            </div>
            <div  class="tile__row tile__row--flex-end shadow--top">
                <button class="btn" @click.prevent="submit">{{$t('common.login')}}</button>
            </div>
        </div>
    </div>
</template>

<script>
    import {signin} from '@/api/auth';
    import { setToken } from "@/utils/auth";
    import {getSimpleCompanyByUser} from '@/api/company';

    export default {
        name: "Login",
        data(){
            return{
                login:'',
                password:'',
                currentUser: {},

                rules: {
                    login: 'required',
                    password: 'required'
                }
            }
        },
        methods:{
            close(){
                this.$router.push('/')
            },
            handleValidationErrorBasic() {
                const firstField = Object.keys(this.errors.collect())[0];
                if(typeof firstField !== 'undefined'){
                    this.$refs[firstField].focus();
                }
            },
            submit(){

                let that = this;

                that.$validator.validate().then(valid => {
                    if (!valid) {
                        return that.handleValidationErrorBasic();
                    } else {
                        signin({login: this.login, password: this.password}).then(response => {
                            setToken(response.data.access_token);
                            that.$store.commit("SET_TOKEN", response.data.access_token);
                            that.$store.commit("SET_NAME", response.data.name);
                            this.$store.dispatch("GetUserInfo").then(() => {
                                that.currentUser = that.$store.getters.getUserInfo;

                                if(that.currentUser.roles.includes('ROLE_COMPANY')){
                                    getSimpleCompanyByUser().then(response => {
                                        if(response.data.id){
                                            this.$router.push('/billingMainPage')
                                        } else {
                                            this.$router.push('/organizationQuestionnaire')
                                        }
                                    })
                                } else {
                                    this.$router.push('/')
                                }
                            });
                            // that.$emit('user-login')
                            // that.close();
                        })
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
