package dev.khaled.bosta.data.model

import com.google.gson.annotations.SerializedName

data class CityWrapper(
    @SerializedName("data")
    var data: List<City>
)
