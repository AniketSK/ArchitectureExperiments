package com.aniketkadam.temperatureapp.temperaturedisplay.futureforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aniketkadam.temperatureapp.R
import com.aniketkadam.temperatureapp.databinding.TemperatureDayListItemBinding
import com.aniketkadam.temperatureapp.temperaturedisplay.data.FormattedForecastDay

class ForecastDayAdapter : ListAdapter<FormattedForecastDay, ForecastDayViewHolder>(
    EMPTY_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TemperatureDayListItemBinding>(
            inflater,
            R.layout.temperature_day_list_item,
            parent,
            false
        )

        return ForecastDayViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ForecastDayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object EMPTY_CALLBACK :
        DiffUtil.ItemCallback<FormattedForecastDay>() { // This isn't needed because the items never change on screen.
        override fun areItemsTheSame(
            oldItem: FormattedForecastDay,
            newItem: FormattedForecastDay
        ): Boolean {
            throw NotImplementedError("We're supposed to be sending new adapters so this shouldn't matter. Implement if required.")
        }

        override fun areContentsTheSame(
            oldItem: FormattedForecastDay,
            newItem: FormattedForecastDay
        ): Boolean {
            throw NotImplementedError("We're supposed to be sending new adapters so this shouldn't matter. Implement if required.")
        }

    }
}