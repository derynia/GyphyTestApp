package com.gyphytestapp.network.usecase

import com.gyphytestapp.core.Resource
import com.gyphytestapp.data.GifsRepository
import com.gyphytestapp.data.db.repository.LoadedPicsRepository
import com.gyphytestapp.di.IoDispatcher
import com.gyphytestapp.network.IPageable
import com.gyphytestapp.network.PaginationListResponseModel
import com.gyphytestapp.network.model.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val gifsRepository: GifsRepository,
    private val loadedPicsRepository: LoadedPicsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IPageable<Data> {
    private lateinit var searchString: String

    override suspend fun execute(page: Long): Resource<PaginationListResponseModel<Data>> =
        withContext(ioDispatcher) {
            val result = gifsRepository.getGifs(searchString, offset = page, perPage = perPage)
            result.data?.entities?.let { entities ->
                loadedPicsRepository.insertList(entities.toList())

                val deletedList = loadedPicsRepository.getDeletedList(entities)
                deletedList.forEach { pic ->
                    val itemIndex = entities.indexOfFirst { it.id == pic.id }
                    if (itemIndex >= 0) entities.removeAt(itemIndex)
                }
            }
            return@withContext result
        }

    fun setSearchString(search: String) {
        searchString = search
    }
}