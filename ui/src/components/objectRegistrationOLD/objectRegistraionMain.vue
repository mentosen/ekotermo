<template>
    <div class="object-reg-container">
        <!--Title-->
        <div class="object-reg-container__title">
            <span class="object-reg-container__title__h1">{{ $t('objectRegistration.objectRegistration')}}</span>
            <span class="object-reg-container__title__h1">{{ $t('objectRegistration.section')}}</span>
            <span class="object-reg-container__title__h2">{{ $t('objectRegistration.objectAddress')}}</span>
        </div>

        <!--Object address-->
        <div class="object-reg-container__sub-title object-reg-container_row">
            <div class="object-reg-container_column">
                <div class="object-reg-container__sub-title__item">
                    <label for="region">{{ $t('common.region')}}</label>
                    <input
                            type="text"
                            v-model="objectAddress.region"
                            autocomplete='off'
                            v-validate="rules.region"
                            id="region"
                    />
                </div>
                <div class="object-reg-container__sub-title__item">
                    <label for="street">{{ $t('registration.street')}}</label>
                    <input
                            type="text"
                            v-model="objectAddress.street"
                            autocomplete='off'
                            v-validate="rules.street"
                            id="street"
                    />
                </div>
            </div>
            <div class="object-reg-container_column">
                <div class="object-reg-container__sub-title__item">
                    <label for="area">{{ $t('common.area')}}</label>
                    <input
                            type="text"
                            v-model="objectAddress.area"
                            autocomplete='off'
                            v-validate="rules.area"
                            id="area"
                    />
                </div>
                <div class="object-reg-container__sub-title__item">
                    <label for="building">{{ $t('registration.building')}}</label>
                    <input
                            type="text"
                            v-model="objectAddress.building"
                            autocomplete='off'
                            v-validate="rules.building"
                            id="building"
                    />
                </div>
            </div>
            <div class="object-reg-container__sub-title__item">
                <label for="city">{{ $t('registration.city')}}</label>
                <input
                        type="text"
                        v-model="objectAddress.city"
                        autocomplete='off'
                        v-validate="rules.city"
                        id="city"
                />
            </div>
        </div>

        <!--Object info-->
        <div>
            <span class="object-reg-container__title__h2">{{ $t('objectRegistration.objectInfo')}}</span>

            <div>
                <!--Building Category-->
                <div>
                    <div>
                        <span>{{ $t('objectRegistration.buildingCategory')}}</span>
                        <div>
                            <label for="sectionPointTwo">{{ $t('objectRegistration.sectionPointTwo')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointTwo"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointThree">{{ $t('objectRegistration.sectionPointThree')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointThree"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointThreeSubOne">{{ $t('objectRegistration.sectionPointThreeSubOne')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointThreeSubOne"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointThreeSubTwo">{{ $t('objectRegistration.sectionPointThreeSubTwo')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointThreeSubTwo"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointFour">{{ $t('objectRegistration.sectionPointFour')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointFour"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointFive">{{ $t('objectRegistration.sectionPointFive')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointFive"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="sectionPointSix">{{ $t('objectRegistration.sectionPointSix')}}</label>
                            <input type="radio"
                                   class=""
                                   id="sectionPointSix"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                    </div>
                </div>

                <!--Building Info-->
                <div>
                    <div>
                        <div>
                            <label for="porchNumber">{{ $t('objectRegistration.porchNumber')}}</label>
                            <input type="number"
                                   class=""
                                   id="porchNumber"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="flatNumber">{{ $t('objectRegistration.flatNumber')}}</label>
                            <input type="number"
                                   class=""
                                   id="flatNumber"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="floorNumber">{{ $t('objectRegistration.floorNumber')}}</label>
                            <input type="number"
                                   class=""
                                   id="floorNumber"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                    </div>

                    <div>
                        <span>{{ $t('objectRegistration.flatNumeration')}}</span>
                        <div>
                            <label for="from">{{ $t('common.from')}}</label>
                            <input type="number"
                                   class=""
                                   id="from"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="to">{{ $t('common.to')}}</label>
                            <input type="number"
                                   class=""
                                   id="to"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="buildingInfo">{{ $t('objectRegistration.buildingInfo')}}</label>
                            <input type="number"
                                   class=""
                                   id="buildingInfo"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                    </div>

                    <div>
                        <span>{{ $t('objectRegistration.buildingOwnerInfo')}}</span>
                        <div>
                            <label for="surname">{{ $t('common.surname')}}</label>
                            <input type="text"
                                   class=""
                                   id="surname"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="name">{{ $t('common.name')}}</label>
                            <input type="text"
                                   class=""
                                   id="name"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="patronymic">{{ $t('common.patronymic')}}</label>
                            <input type="text"
                                   class=""
                                   id="patronymic"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="position">{{ $t('common.position')}}</label>
                            <input type="text"
                                   class=""
                                   id="position"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                        <div>
                            <label for="phoneNumber">{{ $t('common.phoneNumber')}}</label>
                            <input type="number"
                                   class=""
                                   id="phoneNumber"
                                   @change="isIndividual = !isIndividual"
                                   :checked="isIndividual"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "mainInfo",
        data(){
            return {
                objectAddress: {
                    region:'',
                    area:'',
                    city:'',
                    street:'',
                    building:'',
                },

                buildingCategory: '',

                rules: {
                    region:'required',
                    area:'required',
                    city:'required',
                    street:'required',
                    building:'required',
                }
            }
        }
    }
</script>

<style scoped>

</style>