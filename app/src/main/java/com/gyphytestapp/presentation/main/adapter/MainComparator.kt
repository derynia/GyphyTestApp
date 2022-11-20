package com.gyphytestapp.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gyphytestapp.network.model.Data

class MainComparator : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Data, newItem: Data) =
        oldItem.hashCode() == newItem.hashCode()
}
