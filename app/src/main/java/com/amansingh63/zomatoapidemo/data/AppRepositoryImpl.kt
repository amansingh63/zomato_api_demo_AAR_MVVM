package com.amansingh63.zomatoapidemo.data

import com.amansingh63.zomatoapidemo.data.pref.AppPreference
import com.amansingh63.zomatoapidemo.di.module.data.RepositoryModule
import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.search.Restaurants
import com.amansingh63.zomatoapidemo.models.search.SearchResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @RepositoryModule.RemoteDataSource private val remoteDataSource: AppDataSource,
    @RepositoryModule.LocalDataSource private val localDataSource: AppDataSource,
    private val appPreferenceImpl: AppPreference,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppRepository, AppPreference by appPreferenceImpl {


    override suspend fun getResturants(
        query: String,
        fromApi: Boolean
    ): ApiResult<SearchResponse> {
        return withContext(ioDispatcher) {
            if (query.isNotEmpty()) {
                if (fromApi) {
                    return@withContext remoteDataSource.searchResturant(query)
                } else {
                    return@withContext localDataSource.searchResturant(query)
                }
            } else {
                if (fromApi) {
                    return@withContext remoteDataSource.getAllResturant()
                } else {
                    return@withContext localDataSource.getAllResturant()
                }
            }
        }
    }

    override suspend fun getFavRestaurants(): ApiResult<SearchResponse> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getFavRestaurants()
        }
    }

    override suspend fun saveRestaurantsDb(searchResponse: SearchResponse) {
        localDataSource.saveRestaurantsDb(searchResponse)
    }

    override suspend fun markUnmarkFavorite(restaurants: Restaurants, favorite: Boolean) {
        restaurants.restaurant.isFavourite = favorite
        localDataSource.saveRestaurantsDb(SearchResponse(restaurants = listOf(restaurants)))
    }


}