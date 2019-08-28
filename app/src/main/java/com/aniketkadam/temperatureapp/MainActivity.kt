package com.aniketkadam.temperatureapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.aniketkadam.temperatureapp.di.MAIN_VM
import com.aniketkadam.temperatureapp.loading.LoadingFragmentDirections
import com.aniketkadam.temperatureapp.temperaturedisplay.LceWeather
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
        vm.currentWeather.observe(this, Observer { switchScreen(it) })
    }

    private fun switchScreen(lceWeather: LceWeather) = with(findNavController(R.id.nav_host)) {
        when (lceWeather) {
            is LceWeather.Success -> navigate(LoadingFragmentDirections.actionLoadingFragmentToTemperatureDisplayFragment())
            is LceWeather.Error -> navigate(LoadingFragmentDirections.actionLoadingFragmentToErrorDisplayFragment())
        }
    }
}
