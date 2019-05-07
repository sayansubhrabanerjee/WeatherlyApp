package sayan.banerjee.weatherlyapp.weather.view

import android.os.Bundle
import sayan.banerjee.weatherlyapp.R
import sayan.banerjee.weatherlyapp.common.CommonUtils

class WeatherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initOnlineFlow() {
        if (!CommonUtils.isAppIsInBackground(this)) {
            initWeatherSuccessFragment()
        }
    }

    override fun initOfflineFlow() {
        if (!CommonUtils.isAppIsInBackground(this)) {
            initWeatherErrorFragment()
        }
    }

    private fun initWeatherErrorFragment() {
        if (supportFragmentManager != null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.layout_container,
                    WeatherErrorFragment()
                )
                .commit()
        }
    }

    private fun initWeatherSuccessFragment() {
        if (supportFragmentManager != null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.layout_container,
                    WeatherSuccessFragment()
                )
                .commit()
        }
    }
}
