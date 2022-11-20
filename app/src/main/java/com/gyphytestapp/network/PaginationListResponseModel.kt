package com.gyphytestapp.network

import com.google.gson.annotations.SerializedName

data class PaginationListResponseModel<T>(
    @SerializedName("total") val total: Long? = null,
    @SerializedName("entities") val entities: MutableList<T>? = null
)