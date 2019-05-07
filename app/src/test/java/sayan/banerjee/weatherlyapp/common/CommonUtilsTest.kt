package sayan.banerjee.weatherlyapp.common

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class CommonUtilsTest : BaseUnitTest() {
    @Test
    fun getWeatherAPIKeyTest() {
        Assert.assertEquals(WeatherAPIKey.WEATHER_APP_ID, CommonUtils.getWeatherAPIKey())
        Assert.assertNotEquals("123", CommonUtils.getWeatherAPIKey())
    }

    @Test
    fun getWeatherBaseURLTest() {
        Assert.assertEquals(APIConstants.WEATHER_BASE_URL, CommonUtils.getWeatherBaseURL())
        Assert.assertNotEquals("123", CommonUtils.getWeatherBaseURL())
    }

    @Test
    fun getWeatherTempScaleTest() {
        Assert.assertEquals(APIConstants.TEMP_SCALE, CommonUtils.getWeatherTempScale())
        Assert.assertNotEquals("F", CommonUtils.getWeatherTempScale())
    }

    @Test
    fun getCityIdTest() {
        Assert.assertEquals(APIConstants.CITY_ID, CommonUtils.getCityId())
        Assert.assertNotEquals(-1, CommonUtils.getCityId())
    }
}