package sayan.banerjee.weatherlyapp.weather.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_weather_success.*
import kotlinx.android.synthetic.main.fragment_weather_success.view.*
import sayan.banerjee.weatherlyapp.R
import sayan.banerjee.weatherlyapp.common.CommonUtils
import sayan.banerjee.weatherlyapp.weather.model.current.WeatherCurrentResponse
import sayan.banerjee.weatherlyapp.weather.model.forecast.ListWeather
import sayan.banerjee.weatherlyapp.weather.viewmodel.WeatherViewModel

class WeatherSuccessFragment : Fragment() {

    private var mContext: Context? = null
    private var mWeatherViewModel: WeatherViewModel? = null
    private var mForecastAdapter: ForecastAdapter? = null
    private var mForecastDays: MutableList<ListWeather> = mutableListOf()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_success_container, container, false)
        initViews(view)
        initWeatherViewModel()
        makeRequestForCurrentWeather()
        setAdapter(view)
        makeRequestForForecastWeather(view)
        return view
    }

    private fun initViews(v: View) {
        val linearLayoutManager = LinearLayoutManager(v.context)
        v.recyclerView_weather_forecast.addItemDecoration(
            DividerItemDecoration(
                v.context,
                DividerItemDecoration.VERTICAL
            )
        )
        v.recyclerView_weather_forecast.layoutManager = linearLayoutManager
    }

    private fun initWeatherViewModel() {
        mWeatherViewModel = ViewModelProviders.of(this@WeatherSuccessFragment).get(WeatherViewModel::class.java)
        mWeatherViewModel?.getCurrentTempAndLocation()
        mWeatherViewModel?.getWeatherForecast()
    }

    private fun makeRequestForCurrentWeather() {
        mWeatherViewModel!!.getCurrentWeatherLiveData().observe(this@WeatherSuccessFragment,
            Observer<WeatherCurrentResponse> { t ->
                val currentTemp = t?.main?.temp.toString()
                val currentCity = t?.name.toString()
                val currentIcon = t?.weather?.get(0)?.icon.toString()
                Log.i(TAG, "current:: temp:: $currentTemp")
                Log.i(TAG, "current:: city:: $currentCity")
                Log.i(TAG, "current:: icon:: $currentIcon")
                if (currentTemp.isNullOrEmpty() || currentCity.isNullOrEmpty() || currentIcon.isNullOrEmpty()) {
                    //Should be handled based on the requirements
                } else {
                    textView_current_temp.text = currentTemp.plus(getString(R.string.degree))
                    textView_current_city.text = currentCity
                    CommonUtils.configureGlideForWeatherIcon(imageview_current_icon, currentIcon, mContext!!)
                }
                if (view != null) {
                    hideProgress(view!!)
                }
            })
    }

    private fun makeRequestForForecastWeather(v: View) {
        showProgress(v)
        mWeatherViewModel!!.getWeatherForecastLiveData().observe(this@WeatherSuccessFragment, Observer {
            mForecastAdapter?.setData(it?.list!!)
        })
    }


    private fun setAdapter(v: View) {
        mForecastAdapter = ForecastAdapter(mContext!!, mForecastDays)
        v.recyclerView_weather_forecast.adapter = mForecastAdapter
    }

    private fun showProgress(v: View) {
        v.imageView_loading.visibility = View.VISIBLE
        animateProgressImage(v)
    }

    private fun hideProgress(v: View) {
        clearProgressImageAnimation(v)
        v.imageView_loading.visibility = View.GONE
        startSlideUpAnimation(v)
    }

    private fun animateProgressImage(v: View) {
        val rotateClockWise = AnimationUtils.loadAnimation(v.imageView_loading.context, R.anim.rotate_clockwise)
        v.imageView_loading.startAnimation(rotateClockWise)
    }

    private fun clearProgressImageAnimation(v: View) {
        v.imageView_loading.clearAnimation()
    }

    private fun startSlideUpAnimation(v: View) {
        val slideUpAnimation = AnimationUtils.loadAnimation(
            v.context,
            R.anim.slide_up
        )
        v.recyclerView_weather_forecast.startAnimation(slideUpAnimation)
    }

    companion object {
        val TAG: String = WeatherSuccessFragment::class.java.simpleName
    }

}
