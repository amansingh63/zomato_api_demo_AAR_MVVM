package com.amansingh63.zomatoapidemo.data.remote

import com.amansingh63.zomatoapidemo.data.AppDataSource
import com.amansingh63.zomatoapidemo.data.pref.AppPreference
import com.amansingh63.zomatoapidemo.models.ApiError
import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.search.SearchResponse
import com.amansingh63.zomatoapidemo.util.network.ApiParams
import com.amansingh63.zomatoapidemo.util.network.InternetUtil
import com.amansingh63.zomatoapidemo.util.network.RetrofiltErrorUtil
import timber.log.Timber
import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val apiEndPoints: ApiEndPoints,
    private val retrofiltErrorUtil: RetrofiltErrorUtil,
    private val appPreference: AppPreference
) : AppDataSource {

    override suspend fun searchResturant(query: String): ApiResult<SearchResponse> {
        return if (!InternetUtil.isInternetOn()) {
            ApiResult.Error(
                ApiError.NO_INTERNET
            )
        } else {

            val apiParameters = ApiParams.Builder().apply {
                add("q", query)
                add("count", appPreference.apiLimitCount)
            }.build().map

            return try {
                val response = apiEndPoints.search(apiParameters)
                if (response.isSuccessful && response.body() != null) {
                    ApiResult.Success(response.body()!!)
                } else if (response.errorBody() != null) {
                    retrofiltErrorUtil.parseError(response)
                } else {
                    ApiResult.Error(ApiError())
                }
            } catch (e: Exception) {
                Timber.e(e)
                retrofiltErrorUtil.parseError(e)
            }
        }

    }

    override suspend fun getAllResturant(): ApiResult<SearchResponse> {
        return searchResturant("")
    }

    override suspend fun getFavRestaurants(): ApiResult<SearchResponse> =
        ApiResult.Success(SearchResponse())

    override suspend fun saveRestaurantsDb(searchResponse: SearchResponse) {

    }

}