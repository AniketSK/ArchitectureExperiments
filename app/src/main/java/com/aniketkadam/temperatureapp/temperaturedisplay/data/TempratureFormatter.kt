package com.aniketkadam.temperatureapp.temperaturedisplay.data

import java.text.DecimalFormat

class TempratureFormatter {

    fun formattedTemperature(temp: Float): String =
        DecimalFormat("0.#").format(temp)

}