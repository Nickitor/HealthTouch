package com.nikitazamyslov.healthtouch.presentation.util

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.nikitazamyslov.healthtouch.R

sealed class MeasurementStatus(@ColorRes val color: Int, @StringRes val name: Int) {
    object Bad : MeasurementStatus(R.color.red, R.string.item_measurement_bad)
    object Normal : MeasurementStatus(R.color.orange, R.string.item_measurement_normal)
    object Good : MeasurementStatus(R.color.green, R.string.item_measurement_good)
}
