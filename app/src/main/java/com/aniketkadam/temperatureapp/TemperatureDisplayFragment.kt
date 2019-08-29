package com.aniketkadam.temperatureapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aniketkadam.temperatureapp.databinding.FragmentTemperatureDisplayBinding
import com.aniketkadam.temperatureapp.di.FRAGMENT_VM
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import com.aniketkadam.temperatureapp.temperaturedisplay.data.FormattedForecastDay
import com.aniketkadam.temperatureapp.temperaturedisplay.futureforecast.ForecastDayAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_temperature_display.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        temperatureVm.currentWeatherState.observe(
            viewLifecycleOwner,
            Observer { initAdapter(it?.daysForecast) })
    }

    private fun initRecyclerView() {
        val mDividerItemDecoration = DividerItemDecoration(
            upcomingTemperaturesRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        upcomingTemperaturesRecyclerView.addItemDecoration(mDividerItemDecoration)
    }

    private fun initAdapter(daysForecast: List<FormattedForecastDay>?) {
        if (daysForecast == null) {
            return
        }

        upcomingTemperaturesRecyclerView.adapter = ForecastDayAdapter().apply {
            submitList(daysForecast.toMutableList())
        }
    }

}
