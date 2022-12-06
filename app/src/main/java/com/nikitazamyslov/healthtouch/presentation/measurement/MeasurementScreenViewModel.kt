package com.nikitazamyslov.healthtouch.presentation.measurement

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MeasurementScreenViewModel @Inject constructor() :
    ViewModel() {

    private var _state: MutableStateFlow<Unit> = MutableStateFlow(Unit)
    val state get() = _state
}
