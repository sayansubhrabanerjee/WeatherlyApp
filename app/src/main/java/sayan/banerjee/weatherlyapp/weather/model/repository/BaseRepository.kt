package sayan.banerjee.weatherlyapp.weather.model.repository

import io.reactivex.Flowable
import io.reactivex.Single
import sayan.banerjee.weatherlyapp.common.CommonUtils
import sayan.banerjee.weatherlyapp.weather.model.current.WeatherCurrentResponse
import sayan.banerjee.weatherlyapp.weather.model.forecast.WeatherForecastResponse
import sayan.banerjee.weatherlyapp.weather.model.service.IWeatherService
import sayan.banerjee.weatherlyapp.weather.model.service.ServiceGenerator

open class BaseRepository {

    companion object {

        private val TAG: String = BaseRepository::class.java.simpleName

        fun getCurrentWeatherData(): Single<WeatherCurrentResponse> {
            val weatherService = ServiceGenerator.createService(IWeatherService::class.java)
            return weatherService.getCurrentWeather(
                CommonUtils.getCityId(),
                CommonUtils.getWeatherTempScale(),
                CommonUtils.getWeatherAPIKey()
            )
        }

        fun getForecastWeatherData(): Flowable<WeatherForecastResponse> {
            val weatherService = ServiceGenerator.createService(IWeatherService::class.java)
            return weatherService.getForecastWeather(CommonUtils.getCityId(),
                CommonUtils.getWeatherTempScale(),
                CommonUtils.getWeatherAPIKey())
        }
    }
}