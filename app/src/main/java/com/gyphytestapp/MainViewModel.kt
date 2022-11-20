package com.gyphytestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gyphytestapp.data.EncryptedSharedPrefs
import com.gyphytestapp.data.db.repository.LoadedPicsRepository
import com.gyphytestapp.di.IoDispatcher
import com.gyphytestapp.network.BasePagingSource
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.network.usecase.GifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val encryptedSharedPrefs: EncryptedSharedPrefs,
    private val useCase: GifsUseCase,
    private val loadedPicsRepository: LoadedPicsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private var pagingSource: BasePagingSource<Data>? = null

    private var mutablePagingData: Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = useCase.perPage.toInt(), enablePlaceholders = false),
        pagingSourceFactory = { BasePagingSource(useCase).also { source -> pagingSource = source } }
    ).flow.cachedIn(viewModelScope)
    val pagingData: Flow<PagingData<Data>> = mutablePagingData

    init {
        useCase.setSearchString("")
    }

    fun fetchData(searchString: String) {
        if (encryptedSharedPrefs.accessKey == null || searchString.isEmpty()) return

        useCase.setSearchString(searchString)
        pagingSource?.invalidate()
    }

    fun getApiKey() = encryptedSharedPrefs.accessKey

    fun saveApiKey(key: String) {
        encryptedSharedPrefs.accessKey = key
    }

    fun deleteData(pic: Data) {
        viewModelScope.launch(ioDispatcher) {
            loadedPicsRepository.setLoadedPicDeleted(pic)
            pagingSource?.invalidate()
        }
    }
}