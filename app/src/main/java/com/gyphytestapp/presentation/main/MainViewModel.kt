package com.gyphytestapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyphytestapp.core.Resource
import com.gyphytestapp.data.EncryptedSharedPrefs
import com.gyphytestapp.data.GifsRepository
import com.gyphytestapp.state.MainFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val encryptedSharedPrefs: EncryptedSharedPrefs,
    private val gifsRepository: GifsRepository
): ViewModel() {
    private val gifsData = MutableStateFlow(MainFragmentState())
    val gifs: StateFlow<MainFragmentState> = gifsData

    fun fetchData(searchString: String) {
        if (encryptedSharedPrefs.accessKey == null || searchString.isEmpty()) {
            return
        }

        gifsData.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            when (val result = gifsRepository.getGifs(searchString = searchString)) {
                is Resource.Success -> gifsData.update {
                    it.copy(isSuccessful = true, dataList = result.data)
                }
                else -> gifsData.update {
                    it.copy(errorMessage = result.message)
                }
            }
        }
    }

    fun networkErrorsShown() = gifsData.update { it.copy(isLoading = false, errorMessage = null) }

    fun getApiKey() = encryptedSharedPrefs.accessKey

    fun saveApiKey(key: String) {
        encryptedSharedPrefs.accessKey = key
    }
}