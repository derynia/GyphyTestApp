package com.gyphytestapp.network

import com.gyphytestapp.network.model.GifResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {
    companion object {
        private const val SEARCH = "v1/gifs/search"
        private const val API_KEY = "api_key"
        private const val QUERY = "q"
        private const val LIMIT = "limit"
        private const val OFFSET = "offset"
        private const val RATING = "rating"
        private const val LANG = "lang"
    }

    @GET(SEARCH)
    suspend fun getData(
        @Query(API_KEY) apiKey: String,
        @Query(QUERY) query: String = "",
        @Query(LIMIT) limit: String = "25",
        @Query(OFFSET) offset: String = "0",
        @Query(RATING) rating: String = "g",
        @Query(LANG) lang: String = "en"
    ): Response<GifResponse>
}