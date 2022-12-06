package com.nikitazamyslov.healthtouch.data.mapper

import com.nikitazamyslov.healthtouch.data.dao.BannerDao
import com.nikitazamyslov.healthtouch.domain.entities.Banner

fun List<BannerDao>.toDomain(): List<Banner> {
    return map { it.toDomain() }
}

fun BannerDao.toDomain(): Banner {
    return Banner(
        id = id,
        title = title,
        subtitle = subtitle,
        url = url,
    )
}
