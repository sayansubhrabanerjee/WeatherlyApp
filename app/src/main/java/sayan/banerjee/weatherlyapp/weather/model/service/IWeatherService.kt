package sayan.banerjee.weatherlyapp.weather.model.service

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sayan.banerjee.weatherlyapp.weather.model.current.WeatherCurrentResponse
import sayan.banerjee.weatherlyapp.weather.model.forecast.WeatherForecastResponse

interface IWeatherService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("id") id: Int,
        @Query("units") units: String,
        @Query("APPID") app_id: String
    ): Single<WeatherCurrentResponse>

    @GET("forecast")
    fun getForecastWeather(
        @Query("id") id: Int,
        @Query("units") units: String,
        @Query("APPID") app_id: String
    ): Flowable<WeatherForecastResponse>
}