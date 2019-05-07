package sayan.banerjee.weatherlyapp.weather.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import sayan.banerjee.weatherlyapp.weather.model.current.WeatherCurrentResponse
import sayan.banerjee.weatherlyapp.weather.model.forecast.WeatherForecastResponse
import sayan.banerjee.weatherlyapp.weather.model.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    private var mWeatherRepo: WeatherRepository = WeatherRepository()
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()
    private val mCurrentWeatherMutableRepo = MutableLiveData<WeatherCurrentResponse>()
    private val mForecastWeatherMutableRepo = MutableLiveData<WeatherForecastResponse>()

    fun getCurrentWeatherLiveData(): LiveData<WeatherCurrentResponse> {
        return mCurrentWeatherMutableRepo
    }

    fun getCurrentTempAndLocation() {
        mCompositeDisposable?.add(
            mWeatherRepo.getCurrentTempAndLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherCurrentResponse>() {
                    override fun onSuccess(t: WeatherCurrentResponse) {
                        mCurrentWeatherMutableRepo.value = t
                        Log.i("mytest::","${t.main?.temp}")
                    }

                    override fun onError(e: Throwable) {
                        Log.i(TAG,e.message)
                    }
                })
        )
    }

    fun getWeatherForecastLiveData(): LiveData<WeatherForecastResponse> {
        return mForecastWeatherMutableRepo
    }

    fun getWeatherForecast() {
        mCompositeDisposable?.add(
            mWeatherRepo.getWeatherForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mForecastWeatherMutableRepo.value = it
                }, {
                    Log.i(TAG, "error: ${it.message}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
            mCompositeDisposable = null
        }
    }

    companion object {
        val TAG: String = WeatherViewModel::class.java.simpleName
    }
}