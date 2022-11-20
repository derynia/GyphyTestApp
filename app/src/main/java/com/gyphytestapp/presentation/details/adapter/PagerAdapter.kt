package com.gyphytestapp.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.gyphytestapp.databinding.PagerItemBinding
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.presentation.main.adapter.MainComparator

class PagerAdapter(
    private val delOnClick: (Data) -> Unit
) : PagingDataAdapter<Data, PagerViewHolder>(MainComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = PagerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, delOnClick)
        }
    }

    fun getPositionByData(data: Data): Int = snapshot().items.indexOf(data)
}
