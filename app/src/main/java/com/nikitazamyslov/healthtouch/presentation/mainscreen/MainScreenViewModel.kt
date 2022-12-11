package com.nikitazamyslov.healthtouch.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.healthtouch.data.dao.MeasurementDao
import com.nikitazamyslov.healthtouch.domain.usecase.GetBannersUseCase
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MainScreenUiModel
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel
import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getBannersUseCase: GetBannersUseCase,
    private val itemDao: MeasurementDao
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

    fun removeItem(id: Int) {
        deleteItem(id)
    }

    private fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.getItems().collect { items ->
                state.value = state.value.copy(measurementUiModel = items.map { item ->
                    MeasurementUiModel(
                        item.id,
                        item.number,
                        item.date,
                        getMeasurementStatus(item.status),
                        item.bpm,
                        item.hrv,
                    )
                })
            }
        }
    }

    private fun deleteItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.deleteById(id)
        }
    }

    private fun getMeasurementStatus(status: String): MeasurementStatus {
        return when (status) {
            "bad" -> {
                MeasurementStatus.Bad
            }
            "normal" -> {
                MeasurementStatus.Normal
            }
            "good" -> {
                MeasurementStatus.Good
            }
            else -> {
                MeasurementStatus.Bad
            }
        }
    }
}
