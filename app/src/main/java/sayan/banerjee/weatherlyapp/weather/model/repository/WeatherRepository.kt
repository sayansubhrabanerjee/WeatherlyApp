package sayan.banerjee.weatherlyapp.weather.model.repository

class WeatherRepository : BaseRepository() {

    fun getCurrentTempAndLocation() = getCurrentWeatherData()

    fun getWeatherForecast() = getForecastWeatherData()

    companion object {
        val TAG: String = WeatherRepository::class.java.simpleName
    }
}