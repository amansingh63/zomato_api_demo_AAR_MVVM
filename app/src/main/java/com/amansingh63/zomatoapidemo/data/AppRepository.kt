package com.amansingh63.zomatoapidemo.data

import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.search.Restaurants
import com.amansingh63.zomatoapidemo.models.search.SearchResponse

interface AppRepository {

    suspend fun getResturants(query: String, fromApi: Boolean): ApiResult<SearchResponse>

    suspend fun getFavRestaurants(): ApiResult<SearchResponse>

    suspend fun saveRestaurantsDb(searchResponse: SearchResponse)

    suspend fun markUnmarkFavorite(restaurants: Restaurants, favorite: Boolean)


}