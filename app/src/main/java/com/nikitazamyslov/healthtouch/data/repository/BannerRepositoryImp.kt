package com.nikitazamyslov.healthtouch.data.repository

import com.nikitazamyslov.healthtouch.data.local.IBannerApi
import com.nikitazamyslov.healthtouch.data.mapper.toDomain
import com.nikitazamyslov.healthtouch.domain.entities.Banner
import com.nikitazamyslov.healthtouch.domain.repository.IBannerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BannerRepositoryImp @Inject constructor(private val api: IBannerApi) : IBannerRepository {

    override suspend fun getBanners(): Flow<List<Banner>> {
        return flow {
            emit(api.getBanners().toDomain())
        }.flowOn(Dispatchers.IO)
    }
}
