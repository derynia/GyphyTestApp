package com.gyphytestapp.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gyphytestapp.databinding.GifsListItemBinding
import com.gyphytestapp.model_o.Data

class MainAdapter(
    private val onItemClick : (Data) -> Unit
) : ListAdapter<Data, MainViewHolder>(MainComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = GifsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onItemClick)
    }
}
