package com.aniketkadam.temperatureapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.aniketkadam.temperatureapp.di.MAIN_VM
import com.aniketkadam.temperatureapp.loading.LoadingFragmentDirections
import com.aniketkadam.temperatureapp.temperaturedisplay.LceWeather
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
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
            is LceWeather.Success -> safeNavigate(LoadingFragmentDirections.actionLoadingFragmentToTemperatureDisplayFragment())
            is LceWeather.Error -> safeNavigate(LoadingFragmentDirections.actionLoadingFragmentToErrorDisplayFragment())
            // Assuming you can only navigate back to loading from the error display fragment
            is LceWeather.Loading -> safeNavigate(ErrorDisplayFragmentDirections.actionErrorDisplayFragmentToLoadingFragment())
        }
    }

}

private fun NavController.safeNavigate(d: NavDirections) =
    currentDestination?.getAction(d.actionId)?.let { navigate(d) }
        ?: Timber.e("Invalid route for direction ${d} with id ${d.actionId}")