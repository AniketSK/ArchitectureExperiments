package com.aniketkadam.temperatureapp.temperaturedisplay.futureforecast

import androidx.recyclerview.widget.RecyclerView
import com.aniketkadam.temperatureapp.databinding.TemperatureDayListItemBinding
import com.aniketkadam.temperatureapp.temperaturedisplay.data.FormattedForecastDay

class ForecastDayViewHolder(private val view: TemperatureDayListItemBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(item: FormattedForecastDay) {
        view.forecast = item
        view.executePendingBindings()
    }
}
