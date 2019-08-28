package com.aniketkadam.temperatureapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aniketkadam.temperatureapp.databinding.FragmentTemperatureDisplayBinding
import com.aniketkadam.temperatureapp.di.FRAGMENT_VM
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class TemperatureDisplayFragment : DaggerFragment() {

    @field:Named(FRAGMENT_VM)
    @Inject
    lateinit var temperatureVm: TemperatureVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTemperatureDisplayBinding =
            DataBindingUtil.inflate<FragmentTemperatureDisplayBinding>(
                inflater,
                R.layout.fragment_temperature_display,
                container,
                false
            )
        binding.vm = temperatureVm
        binding.lifecycleOwner = this
        return binding.root
    }

}
