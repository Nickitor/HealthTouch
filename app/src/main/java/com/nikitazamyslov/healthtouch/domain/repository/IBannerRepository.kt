package com.nikitazamyslov.healthtouch.domain.repository

import com.nikitazamyslov.healthtouch.domain.entities.Banner
import kotlinx.coroutines.flow.Flow

interface IBannerRepository {

    suspend fun getBanners(): Flow<List<Banner>>
}
