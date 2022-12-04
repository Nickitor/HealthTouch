package com.nikitazamyslov.healthtouch.di

import com.nikitazamyslov.healthtouch.data.local.BannerApiImp
import com.nikitazamyslov.healthtouch.data.local.IBannerApi
import com.nikitazamyslov.healthtouch.data.repository.BannerRepositoryImp
import com.nikitazamyslov.healthtouch.domain.repository.IBannerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideBannerApi(): IBannerApi = BannerApiImp()

    @Provides
    @Singleton
    fun provideBannerRepository(bannerApi: IBannerApi): IBannerRepository =
        BannerRepositoryImp(bannerApi)
}
