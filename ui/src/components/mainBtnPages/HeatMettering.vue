<template>
    <div class="tile__row tile__row--column-direction tile__row--align-start">
        <span style="color: #8BC34A" class="font--description">{{$t('heatMettering.title')}}</span>
        <span style="color: red" class="font--description">{{$t('heatMettering.titleRed')}}</span>
        <span style="font-weight: 600;" class="font--description">{{$t('heatMettering.titleBold')}}</span>
        <br/>
        <div class="tile__row">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/1.1.png"
                    alt="map"
            />
            <div>
                <span class="font--description">{{$t('heatMettering.subTitle')}}</span>
                <br/>
                <span class="font--description">{{$t('heatMettering.listTitle')}}</span>
                <br/>
                <span class="font--description">{{$t('heatMettering.list1')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatMettering.list2')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/2.jpg"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span style="color: red" class="font--description">{{$t('heatMettering.mettering')}}</span>
                <br/>
                <span class="font--description">{{$t('heatMettering.metteringDescription')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatMettering.metteringInstall')}}</span>
        <br/>
        <span class="font--description">{{$t('heatMettering.metteringParts')}}</span>
        <br/>
        <div>
            <span class="font--description">{{$t('heatMettering.howItDo')}}</span>
            <div class="tile__row tile__row--flex-start tile__row--align-start">
                <img
                        class="image"
                        src="../../assets/images/heatMettering/1.1.png"
                        alt="map"
                />
                <div style="margin-top: 4px;">
                    <span class="font--description">{{$t('heatMettering.itp')}}</span>
                </div>
            </div>
            <br/>
            <div class="tile__row tile__row--flex-start tile__row--align-start">
                <img
                        class="image"
                        src="../../assets/images/heatMettering/3.jpg"
                        alt="map"
                />
                <div style="margin-top: 4px;">
                    <span class="font--description">{{$t('heatMettering.counter')}}</span>
                </div>
            </div>
        </div>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/4.jpg"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.itpFull')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatMettering.valveTitle')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/5.1.png"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.valve')}}</span>
            </div>
        </div>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/6.1.png"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.fiting')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatMettering.termoValve')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/7.1.png"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.highValve')}}</span>
            </div>
        </div>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    class="image"
                    src="../../assets/images/heatMettering/8.1.png"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.termoElement')}}</span>
            </div>
        </div>
        <br/>
        <span style="text-decoration: underline; font-weight: 600;" class="font--description">{{$t('heatMettering.sontex')}}</span>
        <br/>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    style="width: 11%;"
                    class="image"
                    src="../../assets/images/heatMettering/9.png"
                    alt="map"
            />
            <img
                    class="image"
                    src="../../assets/images/heatMettering/10.png"
                    alt="map"
            />
            <img
                    style="width: 18%;"
                    class="image"
                    src="../../assets/images/heatMettering/11.1.png"
                    alt="map"
            />
        </div>
        <div class="tile__row tile__row--flex-start tile__row--align-start">
            <img
                    style="width: 11%;"
                    class="image"
                    src="src/assets/images/heatMettering/9.png"
                    alt="map"
            />
            <div style="margin-top: 4px;">
                <span class="font--description">{{$t('heatMettering.sontexElement')}}</span>
            </div>
        </div>
        <br/>
        <span class="font--description">{{$t('heatMettering.mainInfo')}}</span>
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