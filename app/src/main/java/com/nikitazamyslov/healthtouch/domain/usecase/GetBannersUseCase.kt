package com.nikitazamyslov.healthtouch.domain.usecase

import com.nikitazamyslov.healthtouch.domain.repository.IBannerRepository
import com.nikitazamyslov.healthtouch.domain.usecase.mapper.toUiModel
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(private val repository: IBannerRepository) {

    suspend fun getBanners(): Flow<List<BannerUiModel>> {
        return repository.getBanners().map { it.toUiModel() }
    }
}
