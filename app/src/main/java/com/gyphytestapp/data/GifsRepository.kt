package com.gyphytestapp.data

import com.gyphytestapp.R
import com.gyphytestapp.core.Resource
import com.gyphytestapp.di.IoDispatcher
import com.gyphytestapp.di.MainModule
import com.gyphytestapp.model_o.Data
import com.gyphytestapp.model_o.GifResponse
import com.gyphytestapp.network.NetworkService
import com.gyphytestapp.network.PaginationListResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GifsRepository @Inject constructor(
    private val networkService: NetworkService,
    private val encryptedSharedPrefs: EncryptedSharedPrefs,
    private val resourcesProvider: MainModule.ResourcesProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val defaultMessage = resourcesProvider.getString(R.string.wrong_server_response)

    private fun <T> getResultFromResponse(response: Response<T>?): Resource<T> {
        val body = response?.body()
        val errorBody = response?.errorBody()
        return when {
            response == null -> Resource.Error(defaultMessage)
            response.isSuccessful && body != null -> Resource.Success(body)
            errorBody != null -> Resource.Error(errorBody.toString())
            else -> Resource.Error(response.message())
        }
    }

    suspend fun getGifs(
        searchString: String,
        offset: Long,
        perPage: Long
    ): Resource<PaginationListResponseModel<Data>> =
        withContext(ioDispatcher) {
            val accessKey = encryptedSharedPrefs.accessKey
                ?: return@withContext Resource.Error(resourcesProvider.getString(R.string.api_key_not_set))

            val result: Resource<GifResponse> = getResultFromResponse(
                networkService.getData(
                    apiKey = accessKey,
                    query = searchString,
                    offset = offset.toString(),
                    limit = perPage.toString()
                )
            )

            if (result is Resource.Success) {
                result.data?.let {
                    return@withContext Resource.Success(
                        PaginationListResponseModel(
                            result.data.pagination.count.toLong(),
                            result.data.data
                        )
                    )
                }
            }

            Resource.Error(resourcesProvider.getString(R.string.api_key_not_set))
        }
}