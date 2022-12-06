package com.nikitazamyslov.healthtouch.data.local

import com.nikitazamyslov.healthtouch.data.dao.BannerDao
import javax.inject.Inject

class BannerApiImp @Inject constructor() : IBannerApi {

    private val banners = listOf<BannerDao>(
        BannerDao(
            "1", "General recommendations",
            "Eat at least one fresh apple every day. Drink more water and do sport.",
            "https://lepsija.cz/wp-content/uploads/2021/04/workout-composition-with-healthy-food.jpg"
        ),
        BannerDao(
            "2", "Result of all measurements",
            "You can see graphics and recommends",
            "https://kvd-moskva.ru/sites/default/files/1-144.jpg"
        )
    )

    override suspend fun getBanners(): List<BannerDao> {
        return banners
    }
}
