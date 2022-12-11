package com.nikitazamyslov.healthtouch.presentation.measurement.model

import javax.inject.Inject
import kotlin.time.Duration

data class MeasurementScreenUiModel @Inject constructor(
    val bpm: Int,
    val remainingSeconds: Duration,
    val progress: Int,
    val maxProgress: Int,
    val isStart: Boolean,
    val isComplete: Boolean,
)
