package com.gyphytestapp.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gyphytestapp.network.IPageable

class BasePagingSource<T : Any> (
    private val sourceUseCase: IPageable<T>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_KEY
        return when (val result = sourceUseCase.execute(page.toLong())) {
            is Resource.Success -> {
                val loadedData = result.data?.entities ?: listOf()
                LoadResult.Page(
                    data = loadedData,
                    prevKey = if (page == STARTING_KEY) null else page - 1,
                    nextKey = if (loadedData.isEmpty()) null else page + 1
                )
            }
            else -> LoadResult.Error(LoadPageException())
        }
    }

    companion object {
        private const val STARTING_KEY = 0
    }
}