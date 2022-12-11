package com.nikitazamyslov.healthtouch.presentation.mainscreen.model

import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus

data class MeasurementUiModel(
    val id: Int,
    val number: Int,
    val date: String,
    val status: MeasurementStatus,
    val bpm: Int,
    val hrv: Int,
)
