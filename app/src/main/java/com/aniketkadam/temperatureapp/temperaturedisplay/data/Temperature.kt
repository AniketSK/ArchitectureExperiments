package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp_c") val celsius: Float
)