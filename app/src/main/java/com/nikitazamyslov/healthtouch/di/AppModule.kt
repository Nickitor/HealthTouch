package com.nikitazamyslov.healthtouch.di

import com.nikitazamyslov.healthtouch.data.local.BannerApiImp
import com.nikitazamyslov.healthtouch.data.local.IBannerApi
import com.nikitazamyslov.healthtouch.data.repository.BannerRepositoryImp
import com.nikitazamyslov.healthtouch.domain.repository.IBannerRepository
import com.nikitazamyslov.healthtouch.presentation.measurement.model.MeasurementScreenUiModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

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

    @Provides
    fun provideMeasurementScreenUiModel(): MeasurementScreenUiModel =
        MeasurementScreenUiModel(0, 0.seconds, 0, 0)
}
