package com.gyphytestapp.presentation.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gyphytestapp.R
import com.gyphytestapp.databinding.PagerItemBinding
import com.gyphytestapp.di.MainModule
import com.gyphytestapp.network.model.Data

class PagerViewHolder(private val binding: PagerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pic: Data,
        delOnClick: (Data) -> Unit
    ) {
        with(binding) {
            val context = binding.root.context
            val indicator = MainModule.ProgressIndicator(context).getIndicator()
            Glide.with(context)
                .asGif()
                .load(pic.images?.original?.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(indicator)
                .error(R.drawable.cross_s)
                .into(imageDetail)

            buttonDelete.setOnClickListener { delOnClick(pic) }
        }
    }
}