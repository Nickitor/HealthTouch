package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel

class MeasurementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val smile: ImageView = view.findViewById(R.id.ivSmile)
    private val date: TextView = view.findViewById(R.id.tvDate)
    private val number: TextView = view.findViewById(R.id.tvNumber)
    private val bpm: TextView = view.findViewById(R.id.tvBPM)
    private val hrv: TextView = view.findViewById(R.id.tvHRV)
    private val statusText: TextView = view.findViewById(R.id.tvStatus)
    private val statusColor: View = view.findViewById(R.id.vColor)

    fun bind(measurement: MeasurementUiModel) {
        Glide.with(itemView).load(R.drawable.icon_smile_good).into(smile)

        date.text = measurement.date
        number.text = measurement.number.toString()
        bpm.text = measurement.bpm.toString()
        hrv.text = measurement.hrv.toString()
        statusText.text = itemView.resources.getString(measurement.status.name)
        statusColor.setBackgroundColor(itemView.resources.getColor(measurement.status.color))
    }
}
