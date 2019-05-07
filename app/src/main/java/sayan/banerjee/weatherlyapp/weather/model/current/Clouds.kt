package sayan.banerjee.weatherlyapp.weather.model.current

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {

    @SerializedName("all")
    @Expose
    var all: Int? = null

}
