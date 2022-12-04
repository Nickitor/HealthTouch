package com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.ivImage)
    private val title: TextView = view.findViewById(R.id.tvTitle)
    private val subtitle: TextView = view.findViewById(R.id.tvSubtitle)

    fun bind(banner: BannerUiModel) {
        Glide.with(itemView).load(banner.url).into(image)

        title.text = banner.title
        subtitle.text = banner.subtitle
    }
}
