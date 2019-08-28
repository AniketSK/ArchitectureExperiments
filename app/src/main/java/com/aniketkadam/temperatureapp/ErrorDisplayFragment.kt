package com.aniketkadam.temperatureapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aniketkadam.temperatureapp.databinding.FragmentErrorDisplayBinding
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class ErrorDisplayFragment : DaggerFragment() {
    @field:Named
    @Inject
    lateinit var vm: TemperatureVm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentErrorDisplayBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_error_display,
                container,
                false
            )
        binding.vm = vm
        binding.lifecycleOwner = this
        return binding.root
    }

}