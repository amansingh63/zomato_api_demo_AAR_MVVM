package com.amansingh63.zomatoapidemo.data

import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.search.Restaurants
import com.amansingh63.zomatoapidemo.models.search.SearchResponse

interface AppDataSource {

    suspend fun searchResturant(query: String): ApiResult<SearchResponse>

    suspend fun getAllResturant(): ApiResult<SearchResponse>

    suspend fun getFavRestaurants(): ApiResult<SearchResponse>

    suspend fun saveRestaurantsDb(searchResponse: SearchResponse)

}