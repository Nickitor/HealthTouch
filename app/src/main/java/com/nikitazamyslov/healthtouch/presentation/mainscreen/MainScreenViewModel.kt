package com.nikitazamyslov.healthtouch.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.healthtouch.domain.usecase.GetBannersUseCase
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MainScreenUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getBannersUseCase: GetBannersUseCase) :
    ViewModel() {

    private var _state: MutableStateFlow<MainScreenUiModel> = MutableStateFlow(
        MainScreenUiModel(
            listOf(),
            listOf()
        )
    )
    val state get() = _state

    init {
        getBanners()
    }

    private fun getBanners() {
        viewModelScope.launch {
            getBannersUseCase.getBanners().collect { banners ->
                state.emit(state.value.copy(bannerUiModel = banners))
            }
        }
    }
}
