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
            "2", "General recommendations",
            "Get regular check-ups with a doctor.",
            "https://kvd-moskva.ru/sites/default/files/1-144.jpg"
        ),
        BannerDao(
            "3", "General recommendations",
            "Try to be less nervous and try meditation.",
            "https://cleandan.ru/images/content2/st8-1.jpg"
        ),
        BannerDao(
            "4", "General recommendations",
            "Don't forget about rest.",
            "https://mobimg.b-cdn.net/v3/fetch/dc/dc679df7fe3584c5b3dd8c0f2cf2506e.jpeg"
        ),
        BannerDao(
            "5", "General recommendations",
            "Watch your health.",
            "https://digitalhealthbuzz.com/wp-content/uploads/2022/02/Depositphotos_26751869_L.jpg"
        )
    )

    override suspend fun getBanners(): List<BannerDao> {
        return banners
    }
}
