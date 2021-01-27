<template>
    <div class="tile__row tile__row--column-direction tile__row--align-start">
        <span class="font--description">{{$t('heatPoint.title')}}</span>
        <br/>
        <span class="font--description">{{$t('heatPoint.description')}}</span>
        <br/>
        <div class="tile__row">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/1.jpg"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.description1')}}</span>
            </div>
        </div>
        <br/>
        <div class="tile__row">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/2.jpg"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.description2')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatPoint.itpTitle')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/3.jpg"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.itpDescription1')}}</span>
            </div>
        </div>
        <br/>
        <div class="tile__row tile__row--flex-start">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/4.jpg"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.itpDescription2')}}</span>
            </div>
        </div>
        <br/>
        <div class="tile__row tile__row--flex-start">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/5.png"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.itpDescription3')}}</span>
                <br/>
                <span style="color: red" class="font--description">{{$t('heatPoint.itpDescription3red')}}</span>
            </div>
        </div>
        <span class="font--description">{{$t('heatPoint.itpDescription4')}}</span>
        <span style="color: red" class="font--description">{{$t('heatPoint.itpDescription4red')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start">
            <img
                    class="image"
                    src="../../assets/images/heatPoint/6.png"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatPoint.itpDescription5')}}</span>
                <br/>
                <span style="color: red" class="font--description">{{$t('heatPoint.itpDescription5red')}}</span>
            </div>
        </div>
        <br/>
        <span style="font-weight: 600;" class="font--description">{{$t('heatPoint.price')}}</span>
        <br/>
        <span style="text-decoration: underline; font-weight: 600;" class="font--description">{{$t('common.end')}}</span>
        <br/>
        <span style="color: red" class="font--description">{{$t('common.footer')}}</span>
        <span style="text-decoration: underline; color: red" class="font--description">{{$t('common.phone')}}</span>
    </div>
</template>

<script>
    export default {
        name: "Heat point"
    }
</script>

<style scoped>

</style>