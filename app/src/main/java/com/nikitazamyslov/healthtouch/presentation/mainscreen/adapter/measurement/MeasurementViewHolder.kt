package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel
import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus

class MeasurementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val smile: ImageView = view.findViewById(R.id.ivSmile)
    private val date: TextView = view.findViewById(R.id.tvDate)
    private val number: TextView = view.findViewById(R.id.tvNumber)
    private val numberColor: CardView = view.findViewById(R.id.cvNumberColor)
    private val bpm: TextView = view.findViewById(R.id.tvBPM)
    private val hrv: TextView = view.findViewById(R.id.tvHRV)
    private val statusText: TextView = view.findViewById(R.id.tvStatus)
    private val statusColor: View = view.findViewById(R.id.vColor)

    fun bind(measurement: MeasurementUiModel) {
        Glide.with(itemView).load(getIcon(measurement.status)).into(smile)

        val cardColor = ContextCompat.getColor(
            itemView.context,
            measurement.status.color
        )

        date.text = measurement.date
        number.text = measurement.number.toString()
        numberColor.setCardBackgroundColor(cardColor)
        bpm.text = measurement.bpm.toString()
        hrv.text = measurement.hrv.toString()
        statusText.text = itemView.resources.getString(measurement.status.name)
        statusColor.setBackgroundColor(cardColor)
    }

    private fun getIcon(status: MeasurementStatus): Int {
        return when (status) {
            MeasurementStatus.Bad -> {
                R.drawable.ic_smile_bad
            }
            MeasurementStatus.Normal -> {
                R.drawable.ic_smile_normal
            }
            MeasurementStatus.Good -> {
                R.drawable.ic_smile_good
            }
        }
    }
}
