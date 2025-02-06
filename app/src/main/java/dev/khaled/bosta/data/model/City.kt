package dev.khaled.bosta.data.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("cityId")
    var cityId: String,
    @SerializedName("cityName")
    var cityName: String,
    @SerializedName("districts")
    var districts: List<District>
)
