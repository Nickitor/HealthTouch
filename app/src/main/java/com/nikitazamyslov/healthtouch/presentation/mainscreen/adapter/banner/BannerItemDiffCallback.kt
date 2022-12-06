package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner

import androidx.recyclerview.widget.DiffUtil
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel

class BannerItemDiffCallback : DiffUtil.ItemCallback<BannerUiModel>() {
    override fun areItemsTheSame(oldItem: BannerUiModel, newItem: BannerUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BannerUiModel, newItem: BannerUiModel): Boolean {
        return oldItem == newItem
    }
}
