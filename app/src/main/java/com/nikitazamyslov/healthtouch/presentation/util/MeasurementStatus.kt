package com.nikitazamyslov.healthtouch.presentation.util

import androidx.annotation.ColorRes
import com.nikitazamyslov.healthtouch.R

sealed class MeasurementStatus(@ColorRes val color: Int) {
    object Bad : MeasurementStatus(R.color.red)
    object Normal : MeasurementStatus(R.color.orange)
    object Good : MeasurementStatus(R.color.green)
}
