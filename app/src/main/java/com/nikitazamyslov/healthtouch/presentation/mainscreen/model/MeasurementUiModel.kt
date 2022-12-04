package com.nikitazamyslov.healthtouch.presentation.mainscreen.model

import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus

data class MeasurementUiModel(
    val number: Int,
    val status: MeasurementStatus,
    val bpm: Int,
    val hrv: Int,
)
