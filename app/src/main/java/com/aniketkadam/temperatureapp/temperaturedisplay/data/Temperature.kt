package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class Temperature(
    @field:SerializedName("temp_c") val celsius: Float
)