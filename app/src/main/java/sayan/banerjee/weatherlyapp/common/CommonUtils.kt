package sayan.banerjee.weatherlyapp.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import sayan.banerjee.weatherlyapp.R
import sayan.banerjee.weatherlyapp.common.APIConstants.Companion.IMAGE_FORMAT
import sayan.banerjee.weatherlyapp.common.APIConstants.Companion.WEATHER_ICON_BASE
import android.app.ActivityManager
import android.os.Build




class CommonUtils {

    companion object {

        fun getWeatherAPIKey(): String = WeatherAPIKey.WEATHER_APP_ID

        fun getWeatherBaseURL(): String = APIConstants.WEATHER_BASE_URL

        fun getWeatherTempScale(): String = APIConstants.TEMP_SCALE

        fun getCityId(): Int = APIConstants.CITY_ID

        fun configureGlideForWeatherIcon(imageView: ImageView
                                         , iconUrl: String
                                         , context: Context
                                         , width: Int = 50
                                         , height: Int = 50) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.weather_icon_error)

            Glide
                .with(context)
                .applyDefaultRequestOptions(requestOptions
                    .override(width, height)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                .load(WEATHER_ICON_BASE.plus(iconUrl).plus(IMAGE_FORMAT))
                .into(imageView)
        }

        fun isAppIsInBackground(context: Context): Boolean {
            var isInBackground = true
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                val runningProcesses = am.runningAppProcesses
                for (processInfo in runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (activeProcess in processInfo.pkgList) {
                            if (activeProcess == context.packageName) {
                                isInBackground = false
                            }
                        }
                    }
                }
            } else {
                val taskInfo = am.getRunningTasks(1)
                val componentInfo = taskInfo[0].topActivity
                if (componentInfo.packageName == context.packageName) {
                    isInBackground = false
                }
            }

            return isInBackground
        }
    }
}