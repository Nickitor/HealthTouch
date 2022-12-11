package com.nikitazamyslov.healthtouch.presentation.mainscreen

import androidx.lifecycle.*
import com.nikitazamyslov.healthtouch.data.dao.MeasurementDao
import com.nikitazamyslov.healthtouch.data.entity.MeasurementEntity
import com.nikitazamyslov.healthtouch.domain.usecase.GetBannersUseCase
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MainScreenUiModel
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel
import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getBannersUseCase: GetBannersUseCase, private val itemDao: MeasurementDao
) : ViewModel() {

    private var _state: MutableStateFlow<MainScreenUiModel> = MutableStateFlow(
        MainScreenUiModel(
            listOf(), listOf()
        )
    )
    val state get() = _state

    init {
        getBanners()
        getItems()
    }

    private fun getBanners() {
        viewModelScope.launch {
            getBannersUseCase.getBanners().collect { banners ->
                state.emit(state.value.copy(bannerUiModel = banners))
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            itemDao.getItems().collect { items ->
                state.value = state.value.copy(
                    measurementUiModel = items.map { item ->
                        MeasurementUiModel(
                            item.number,
                            item.date,
                            MeasurementStatus.Good,
                            item.bpm,
                            item.hrv,
                        )
                    }
                )
            }
        }
    }
}
