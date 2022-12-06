package com.nikitazamyslov.healthtouch.domain.usecase.mapper

import com.nikitazamyslov.healthtouch.domain.entities.Banner
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel

fun List<Banner>.toUiModel(): List<BannerUiModel> {
    return map { it.toUiModel() }
}

fun Banner.toUiModel(): BannerUiModel {
    return BannerUiModel(
        id = id,
        title = title,
        subtitle = subtitle,
        url = url,
    )
}
