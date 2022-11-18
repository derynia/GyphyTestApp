package com.gyphytestapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gyphytestapp.R
import com.gyphytestapp.databinding.FragmentMainBinding
import com.gyphytestapp.model_o.Data
import com.gyphytestapp.presentation.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        binding.initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun FragmentMainBinding.initViews() {
        binding.recyclerMain.adapter = gifAdapter
        gifAdapter.submitList(viewModel.provideList())
    }
}