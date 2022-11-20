package com.gyphytestapp.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyphytestapp.R
import com.gyphytestapp.databinding.GifsListItemBinding
import com.gyphytestapp.network.model.Data

class MainViewHolder(private val binding: GifsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        pic: Data,
        onItemClick: (Data) -> Unit
    ) {
        with(binding) {
            val context = binding.root.context
            Glide.with(context)
                .asBitmap()
                .load(pic.images?.preview_webp?.url)
                .error(R.drawable.cross_s)
                .into(imagePreview)
        }
        itemView.setOnClickListener { onItemClick(pic) }
    }
}