package dev.khaled.bosta.data.model

import com.google.gson.annotations.SerializedName

data class District(
    @SerializedName("zoneId")
    var zoneId: String,
    @SerializedName("zoneName")
    var zoneName: String,
    @SerializedName("districtName")
    var districtName: String,
)