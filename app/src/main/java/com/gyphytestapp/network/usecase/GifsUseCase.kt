package com.gyphytestapp.network.usecase

import com.gyphytestapp.core.Resource
import com.gyphytestapp.data.GifsRepository
import com.gyphytestapp.network.model.Data
import com.gyphytestapp.network.IPageable
import com.gyphytestapp.network.PaginationListResponseModel
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val gifsRepository: GifsRepository
) : IPageable<Data> {
    private lateinit var searchString: String

    override suspend fun execute(page: Long): Resource<PaginationListResponseModel<Data>> {
        return gifsRepository.getGifs(searchString, offset = page, perPage = perPage)
    }

    fun setSearchString(search: String) {
        searchString = search
    }
}