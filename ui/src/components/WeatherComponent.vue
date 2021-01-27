<template>
    <div id="SinoptikInformer" style="width:244px;" class="SinoptikInformer type1c1">
        <div class="siHeader">
            <div class="siLh">
                <div class="siMh">
                    <a onmousedown="siClickCount();" class="siLogo" href="https://ua.sinoptik.ua/" target="_blank"
                       rel="nofollow" title="Погода"> </a>Погода
                    <span id="siHeader"></span>
                </div>
            </div>
        </div>
        <div class="siBody">
            <a onmousedown="siClickCount();" href="https://ua.sinoptik.ua/погода-київ" title="Погода у Києві"
               target="_blank">
                <div class="siCity">
                    <div class="siCityName">
                        <span>Київ</span>
                    </div>
                    <div id="siCont0" class="siBodyContent">
                        <div class="siLeft">
                            <div class="siTerm"></div>
                            <div class="siT" id="siT0"></div>
                            <div id="weatherIco0"></div>
                        </div>
                        <div class="siInf">
                            <p>вологість:
                                <span id="vl0"></span>
                            </p>
                            <p>тиск:
                                <span id="dav0"></span>
                            </p>
                            <p>вітер:
                                <span id="wind0"></span>
                            </p>
                        </div>
                    </div>
                </div>
            </a><a onmousedown="siClickCount();" href="https://ua.sinoptik.ua/погода-горішні-плавні"
                   title="Погода у Горішніх Плавнях" target="_blank">
            <div class="siCity">
                <div class="siCityName"><span>Горішні Плавні</span></div>
                <div id="siCont1" class="siBodyContent">
                    <div class="siLeft">
                        <div class="siTerm"></div>
                        <div class="siT" id="siT1"></div>
                        <div id="weatherIco1"></div>
                    </div>
                    <div class="siInf"><p>вологість: <span id="vl1"></span></p>
                        <p>тиск: <span id="dav1"></span></p>
                        <p>вітер: <span id="wind1"></span></p></div>
                </div>
            </div>
        </a>
            <div class="siLinks">Погода на 10 днів від <a href="https://ua.sinoptik.ua/10-днів"
                                                          title="Погода на 10 днів" target="_blank"
                                                          onmousedown="siClickCount();"> sinoptik.ua</a></div>
        </div>
        <div class="siFooter">
            <div class="siLf">
                <div class="siMf"></div>
            </div>
        </div>
    </div>
</template>

<!--<script type="text/javascript" charset="UTF-8"-->
<!--        src="https://sinoptik.ua/informers_js.php?title=4&amp;wind=3&amp;cities=303010783,303011587&amp;lang=ua">-->

<!--</script>-->
<!--<script>-->
<!--    export default {-->
<!--        name: "WeatherComponent"-->
<!--    }-->
<!--</script>-->

<style scoped>

</style>