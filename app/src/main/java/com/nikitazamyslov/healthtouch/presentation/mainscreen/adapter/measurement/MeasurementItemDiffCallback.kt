package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement

import androidx.recyclerview.widget.DiffUtil
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel

class MeasurementItemDiffCallback : DiffUtil.ItemCallback<MeasurementUiModel>() {
    override fun areItemsTheSame(
        oldItem: MeasurementUiModel,
        newItem: MeasurementUiModel
    ): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(
        oldItem: MeasurementUiModel,
        newItem: MeasurementUiModel
    ): Boolean {
        return oldItem == newItem
    }
}
