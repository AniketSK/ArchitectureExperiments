package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class IpLocationData(
    @field:SerializedName("city") val city: String
)