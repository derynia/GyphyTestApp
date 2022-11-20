package com.gyphytestapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyphytestapp.network.BasePagingSource
import com.gyphytestapp.data.EncryptedSharedPrefs
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.network.usecase.GifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val encryptedSharedPrefs: EncryptedSharedPrefs,
    private val useCase: GifsUseCase
): ViewModel() {
    private val mutableRefreshAdapterEvent : MutableSharedFlow<Unit> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val refreshAdapterEvent: Flow<Unit> = mutableRefreshAdapterEvent

    private var mutablePagingData: Flow<PagingData<Data>> = newPagerInstance()

    val pagingData: Flow<PagingData<Data>> = mutablePagingData

    init {
        useCase.setSearchString("")
    }

    private fun invalidate() {
        mutablePagingData = newPagerInstance()
        viewModelScope.launch {
            mutableRefreshAdapterEvent.emit(Unit)
        }
    }

    private fun newPagerInstance() = Pager(
        config = PagingConfig(pageSize = useCase.perPage.toInt(), enablePlaceholders = false),
        pagingSourceFactory = { BasePagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)

    fun fetchData(searchString: String) {
        if (encryptedSharedPrefs.accessKey == null || searchString.isEmpty()) return

        useCase.setSearchString(searchString)
        invalidate()
    }

    fun getApiKey() = encryptedSharedPrefs.accessKey

    fun saveApiKey(key: String) {
        encryptedSharedPrefs.accessKey = key
    }
}