package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel

class MeasurementListAdapter :
    ListAdapter<MeasurementUiModel, MeasurementViewHolder>(MeasurementItemDiffCallback()) {

    var onClickListener: ((MeasurementUiModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        return MeasurementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main_measurement, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        with(holder) {
            val data = getItem(position)
            bind(data)
            itemView.setOnClickListener {
                onClickListener?.invoke(data)
            }
        }
    }
}
