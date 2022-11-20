package com.gyphytestapp.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gyphytestapp.R
import com.gyphytestapp.databinding.GifsListItemBinding
import com.gyphytestapp.di.MainModule
import com.gyphytestapp.network.model.Data

class MainViewHolder(private val binding: GifsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        pic: Data,
        onItemClick: (Data) -> Unit
    ) {
        with(binding) {
            val context = binding.root.context
            val indicator = MainModule.ProgressIndicator(context).getIndicator()
            Glide.with(context)
                .asBitmap()
                .load(pic.images?.preview_webp?.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.cross_s)
                .placeholder(indicator)
                .into(imagePreview)
        }
        itemView.setOnClickListener { onItemClick(pic) }
    }
}