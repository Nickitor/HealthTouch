package com.nikitazamyslov.healthtouch.presentation.measurement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.healthtouch.data.dao.MeasurementDao
import com.nikitazamyslov.healthtouch.data.entity.MeasurementEntity
import com.nikitazamyslov.healthtouch.presentation.measurement.model.MeasurementScreenUiModel
import com.nikitazamyslov.healthtouch.presentation.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MeasurementScreenViewModel @Inject constructor(
    initState: MeasurementScreenUiModel,
    private val itemDao: MeasurementDao,
    private val resourceHelper: ResourceHelper
) :
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
            duration = state.value.remainingSeconds,
            tickAction = ::oneTickHasPassed,
            completeAction = ::onCompleteAction,
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

    private suspend fun onCompleteAction() {
        viewModelScope.launch(Dispatchers.IO) {
            stopMeasure()
            state.value = state.value.copy(isComplete = true)
            insertMeasurement()
        }
    }

    fun stopMeasure() {
        state.value = state.value.copy(
            isStart = false,
            remainingSeconds = MEASUREMENT_DURATION,
            progress = 0
        )
        viewModelScope.launch {
            measure?.cancel()
        }
    }

    private fun getMeasurementStatus(bpm: Int): Int {
        return when (bpm) {
            in 60..80 -> {
                MeasurementStatus.Good.name
            }
            in 81..100 -> {
                MeasurementStatus.Normal.name
            }
            else -> {
                MeasurementStatus.Bad.name
            }
        }
    }

    private suspend fun insertMeasurement() {
        insertItem(
            MeasurementEntity(
                number = getLastNumber() + 1,
                bpm = state.value.bpm,
                hrv = 0,
                date = getCurrentDate(),
                status = resourceHelper.getStringResource(getMeasurementStatus(state.value.bpm))
            )
        )
    }

    private suspend fun insertItem(item: MeasurementEntity) {
        itemDao.insert(item)
    }

    private suspend fun getLastNumber(): Int {
        val last = itemDao.getLastItem()
        if (last.isNotEmpty())
            return last[0].number
        return 0
    }

    private fun getCurrentDate(): String {
        return getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")
    }

    companion object {
        private val MEASUREMENT_DURATION = 10.seconds
    }
}
