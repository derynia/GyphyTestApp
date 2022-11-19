package com.gyphytestapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gyphytestapp.R
import com.gyphytestapp.databinding.FragmentMainBinding
import com.gyphytestapp.model_o.Data
import com.gyphytestapp.presentation.main.adapter.MainAdapter
import com.gyphytestapp.presentation.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private val gifAdapter = MainAdapter { pic -> openInfo(pic) }

    private fun openInfo(pic: Data) {
//        findNavController().navigate(
//            R.id.action_homeFragment_to_detailsFragment,
//            bundleOf(SERVER_BUNDLE_KEY to server.serv_id)
//        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        launchStateFlowCollectors()
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
            viewModel.networkErrorsShown()
        }
    }

    private fun launchStateFlowCollectors() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gifs.collectLatest { event ->
                    when {
                        event.hasError() -> handleError(event.errorMessageAsString())
                        event.isSuccessful -> submitAdapterData(event.dataList)
                        event.isLoading -> showProgress()
                        else -> hideProgress()
                    }
                }
            }
        }
    }

    private fun submitAdapterData(dataList: List<Data>?) {
        dataList?.let {
            gifAdapter.submitList(it)
        }
        hideProgress()
    }
}