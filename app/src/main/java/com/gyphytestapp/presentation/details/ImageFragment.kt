package com.gyphytestapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gyphytestapp.R
import com.gyphytestapp.core.DATA_KEY
import com.gyphytestapp.core.SEARCH_STRING_KEY
import com.gyphytestapp.databinding.FragmentImageBinding
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.presentation.details.adapter.PagerAdapter
import com.gyphytestapp.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {
    private val binding: FragmentImageBinding by viewBinding(FragmentImageBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    private val pagerAdapter : PagerAdapter = PagerAdapter()
    private var data: Data? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.initViews()
    }

    private fun FragmentImageBinding.initViews() {
        viewPagerPics.adapter = pagerAdapter
        initDataCollectors()
        arguments?.let { it ->
            val searchString = it.getString(SEARCH_STRING_KEY)
            searchString?.let {
                viewModel.fetchData(searchString)
            }
            it.getParcelable<Data>(DATA_KEY).let { bundleData ->
                data = bundleData
            }
        }
        pagerAdapter.addLoadStateListener {

        }
    }

    private fun initDataCollectors() {
        pagerAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && pagerAdapter.itemCount > 0) {
                data?.let {
                    val position = pagerAdapter.getPositionByData(data!!)
                    if (position >= 0) {
                        binding.viewPagerPics.currentItem = position
                        data = null
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.pagingData.distinctUntilChanged().collectLatest  {
                pagerAdapter.submitData(it)
            }
        }
    }
}