package com.nikitazamyslov.healthtouch.presentation.measurement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.healthtouch.presentation.measurement.model.MeasurementScreenUiModel
import com.nikitazamyslov.healthtouch.presentation.util.getTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MeasurementScreenViewModel @Inject constructor(initState: MeasurementScreenUiModel) :
    ViewModel() {

    private var _state: MutableStateFlow<MeasurementScreenUiModel> =
        MutableStateFlow(initState)
    val state get() = _state

    private var measure: Job? = null

    init {
        viewModelScope.launch {
            state.emit(
                state.value.copy(
                    remainingSeconds = MEASUREMENT_DURATION,
                    maxProgress = MEASUREMENT_DURATION.inWholeSeconds.toInt()
                )
            )
        }
    }

    fun startMeasure() {
        stopMeasure()
        state.value = state.value.copy(isStart = true)
        measure = getTimer(
            tickAction = ::oneTickHasPassed,
            duration = state.value.remainingSeconds,
            scope = viewModelScope
        )
    }

    fun updateBPM(bpm: Int) {
        state.value = state.value.copy(bpm = bpm)
    }

    private suspend fun oneTickHasPassed() {
        with(state) {
            emit(
                value.copy(
                    remainingSeconds = value.remainingSeconds - 1.seconds,
                    progress = value.progress + 1
                )
            )
        }
    }

    fun stopMeasure() {
        state.value = state.value.copy(isStart = false)
        viewModelScope.launch {
            measure?.cancel()
        }
    }

    companion object {
        private val MEASUREMENT_DURATION = 10.seconds
    }
}
