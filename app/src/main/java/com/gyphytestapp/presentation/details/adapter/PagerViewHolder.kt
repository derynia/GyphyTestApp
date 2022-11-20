package com.gyphytestapp.presentation.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyphytestapp.R
import com.gyphytestapp.databinding.PagerItemBinding
import com.gyphytestapp.di.MainModule
import com.gyphytestapp.network.model.Data

class PagerViewHolder(private val binding: PagerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pic: Data
    ) {
        with(binding) {
            val context = binding.root.context
            val indicator = MainModule.ProgressIndicator(context).getIndicator()
            Glide.with(context)
                .asGif()
                .load(pic.images?.original?.url)
                .placeholder(indicator)
                .error(R.drawable.cross_s)
                .into(imageDetail)
        }
    }
}