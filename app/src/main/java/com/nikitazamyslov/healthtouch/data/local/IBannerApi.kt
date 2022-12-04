package com.nikitazamyslov.healthtouch.data.local

import com.nikitazamyslov.healthtouch.data.dao.BannerDao

interface IBannerApi {

    suspend fun getBanners(): List<BannerDao>
}
