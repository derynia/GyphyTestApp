package com.gyphytestapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gyphytestapp.MainViewModel
import com.gyphytestapp.R
import com.gyphytestapp.core.DATA_KEY
import com.gyphytestapp.core.SEARCH_STRING_KEY
import com.gyphytestapp.databinding.FragmentMainBinding
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.presentation.main.adapter.MainAdapter
import com.gyphytestapp.presentation.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    private val gifAdapter = MainAdapter { pic -> openInfo(pic) }

    private fun openInfo(pic: Data) {
        findNavController().navigate(
            R.id.action_mainFragment_to_imageFragment,
            bundleOf(
                DATA_KEY to pic,
                SEARCH_STRING_KEY to binding.editSearch.text.toString()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        initDataCollectors()
    }

    private fun FragmentMainBinding.initViews() {
        recyclerMain.adapter = gifAdapter

        editApiKey.setText(viewModel.getApiKey())
        editApiKey.doAfterTextChanged {
            viewModel.saveApiKey(it.toString())
        }
        editSearch.doAfterTextChanged {
            viewModel.fetchData(it.toString())
        }
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun handleError(message: String?) {
        hideProgress()
        message?.let {
            activity?.showError(getString(R.string.error_header), it)
            //viewModel.networkErrorsShown()
        }
    }

    private fun initDataCollectors() {
        gifAdapter.addLoadStateListener { loadState ->
            when (val lState = loadState.source.refresh) {
                is LoadState.Loading -> showProgress()
                is LoadState.NotLoading -> hideProgress()
                is LoadState.Error -> {
                    hideProgress()
                    handleError(lState.error.message)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.pagingData.distinctUntilChanged().collectLatest {
                gifAdapter.submitData(it)
            }
        }
    }
}