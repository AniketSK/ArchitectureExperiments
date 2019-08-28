package com.aniketkadam.temperatureapp

import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class ErrorDisplayFragment : DaggerFragment() {
    @field:Named
    @Inject
    lateinit var vm: TemperatureVm

}