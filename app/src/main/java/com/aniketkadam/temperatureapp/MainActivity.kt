package com.aniketkadam.temperatureapp

import android.os.Bundle
import com.aniketkadam.temperatureapp.di.MAIN_VM
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity() {

    @field:Named(MAIN_VM)
    @Inject
    lateinit var vm: TemperatureVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
