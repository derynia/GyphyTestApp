package com.gyphytestapp.model

data class Response(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)