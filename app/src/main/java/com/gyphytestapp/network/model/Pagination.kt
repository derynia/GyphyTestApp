package com.gyphytestapp.network.model

data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
)