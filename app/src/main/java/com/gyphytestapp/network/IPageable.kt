package com.gyphytestapp.network

import com.gyphytestapp.core.Resource

interface IPageable<T> {
    val perPage : Long
        get() = 25L

    suspend fun execute(page: Long) : Resource<PaginationListResponseModel<T>>
}