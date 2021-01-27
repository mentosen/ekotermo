<template>
    <div>
        <h1 class="font--title">{{$t('homePage.title')}}</h1>
        <div class="tile__row tile__row--flex-center">
            <button-component
                    :name="$t('homePage.about')"
                    @click="about"
            />
            <button-component
                    :name="$t('homePage.heatMetering')"
                    @click="mettering"
            />
            <button-component
                    :name="$t('homePage.heatPoint')"
                    @click="point"
            />
            <button-component
                    :name="$t('homePage.projecting')"
                    @click="design"
            />
            <button-component
                    :name="$t('homePage.contacts')"
                    @click="contacts"
            />
            <button-component
                    class="btn-accent"
                    :name="$t('homePage.economyResults')"
                    @click="economy"
            />
        </div>
        <div class="tile__row tile__row--column-direction tile__row--margin-top tile__row--space-evenly">
            <div>
                <img
                        style="width: auto"
                        src="../assets/images/map.png"
                        alt="map"
                />
            </div>
            <div class="tile__row tile__row--margin-top tile__row--space-evenly">
                <span>{{$t('homePage.partners')}}</span>
                <img
                        style="width:120px"
                        src="../assets/images/antap.png"
                        alt="map"
                />
                <img
                        style="width:120px"
                        src="../assets/images/atlantaCity.png"
                        alt="map"
                />
                <img
                        style="width:120px"
                        src="../assets/images/danffos.png"
                        alt="map"
                />
                <img
                        style="width:120px"
                        src="../assets/images/kaflex.png"
                        alt="map"
                />
                <img
                        style="width:120px"
                        src="../assets/images/santorin.png"
                        alt="map"
                />
            </div>
        </div>
    </div>
</template>

<script>
    import ButtonComponent from "@/components/common/ButtonComponent";

    export default {
        components:{ButtonComponent},
        name: "HomePage",
        methods:{
            about(){
                this.$router.push('/about')
            },
            contacts(){
                this.$router.push('/contacts')
            },
            design(){
                this.$router.push('/instalation')
            },
            economy(){
                this.$router.push('/economy')
            },
            mettering(){
                this.$router.push('/mettering')
            },
            point(){
                this.$router.push('/point')
            },
        }
    }
</script>

<style scoped>
</style>