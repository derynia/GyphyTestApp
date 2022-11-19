package com.gyphytestapp.state

import com.gyphytestapp.model_o.Data

data class MainFragmentState(
    override val isLoading: Boolean = false,
    override val isSuccessful: Boolean = false,
    override val errorMessage: String? = null,
    val dataList: List<Data>? = listOf()
) : IAuthUiState, IProgressable
