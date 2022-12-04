package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel

class BannerListAdapter : ListAdapter<BannerUiModel, BannerViewHolder>(BannerItemDiffCallback()) {

    var onClickListener: ((BannerUiModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main_banners, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        with(holder) {
            val data = getItem(position)
            bind(data)
            itemView.setOnClickListener {
                onClickListener?.invoke(data)
            }
        }
    }
}
