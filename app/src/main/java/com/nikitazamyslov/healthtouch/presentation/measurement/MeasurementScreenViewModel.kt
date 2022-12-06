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
        measure = getTimer(
            tickAction = ::oneTickHasPassed,
            duration = state.value.remainingSeconds,
            scope = viewModelScope
        )
    }

    private suspend fun oneTickHasPassed() {
        with(state) {
            emit(
                value.copy(
                    bpm = 70 + Random.nextInt(1, 10),
                    remainingSeconds = value.remainingSeconds - 1.seconds,
                    progress = value.progress + 1
                )
            )
        }
    }

    fun stopMeasure() {
        viewModelScope.launch {
            measure?.cancel()
        }
    }

    companion object {
        private val MEASUREMENT_DURATION = 30.seconds
    }
}
