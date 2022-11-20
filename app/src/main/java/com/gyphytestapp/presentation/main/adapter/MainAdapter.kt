package com.gyphytestapp.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.gyphytestapp.databinding.GifsListItemBinding
import com.gyphytestapp.network.model.Data

class MainAdapter(
    private val onItemClick : (Data) -> Unit
) : PagingDataAdapter<Data, MainViewHolder>(MainComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = GifsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClick)
        }
    }
}
