package sayan.banerjee.weatherlyapp.weather.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_forecastday.view.*
import sayan.banerjee.weatherlyapp.R
import sayan.banerjee.weatherlyapp.common.APIConstants.Companion.CELCIUS
import sayan.banerjee.weatherlyapp.common.CommonUtils
import sayan.banerjee.weatherlyapp.weather.model.forecast.ListWeather


open class ForecastAdapter(private val context: Context, private val forecastDays: MutableList<ListWeather>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_forecastday, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastDays.size
    }

    override fun onBindViewHolder(ForecastViewHolder: ForecastViewHolder, position: Int) {
        val forecastDays = forecastDays[position]
        ForecastViewHolder.bindData(forecastDays)
    }

    fun setData(forecastDaysList: List<ListWeather>) {
        for (i in 0..forecastDaysList.size) {
            forecastDays.add(i, forecastDaysList[i + 1])
            notifyDataSetChanged()
        }
    }

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(forecastdays: ListWeather?) {
            forecastdays?.let {
                itemView.textView_forecast_temp.text = it.main?.temp.toString()
                it.weather?.forEach {
                    CommonUtils.configureGlideForWeatherIcon(
                        itemView.imageView_forecast_icon,
                        it.icon!!,
                        context
                    )
                }
                itemView.textView_forecast_day_name.text = it.dtTxt.toString()
                itemView.textView_forecast_temp_scale.text = CELCIUS
            }
        }
    }

    companion object {
        val TAG: String = ForecastAdapter::class.java.simpleName
    }
}