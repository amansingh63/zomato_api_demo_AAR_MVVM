package com.amansingh63.zomatoapidemo.data.local

import com.amansingh63.zomatoapidemo.data.AppDataSource
import com.amansingh63.zomatoapidemo.models.ApiError
import com.amansingh63.zomatoapidemo.models.ApiResult
import com.amansingh63.zomatoapidemo.models.search.Restaurants
import com.amansingh63.zomatoapidemo.models.search.SearchResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppLocalDataSource internal constructor(
    private val appDao: AppDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDataSource {


    override suspend fun searchResturant(query: String): ApiResult<SearchResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Restaurants>()
                val restaurantInner = appDao.getRestaurantByName("%$query%")

                for (restaurant in restaurantInner) {
                    restaurants.add(Restaurants(restaurant))
                }

                ApiResult.Success(
                    SearchResponse(
                        restaurants.size,
                        0,
                        restaurants.size,
                        restaurants
                    )
                )
            } catch (e: Exception) {
                e.message?.let {
                    ApiResult.Error(ApiError(2000, it))
                } ?: run {
                    ApiResult.Error(ApiError(2000, "Local Database Error"))
                }

            }
        }

    override suspend fun getAllResturant(): ApiResult<SearchResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Restaurants>()
                val restaurantInner = appDao.getAllRestaurants()

                for (restaurant in restaurantInner) {
                    restaurants.add(Restaurants(restaurant))
                }

                ApiResult.Success(
                    SearchResponse(
                        restaurants.size,
                        0,
                        restaurants.size,
                        restaurants
                    )
                )
            } catch (e: Exception) {
                e.message?.let {
                    ApiResult.Error(ApiError(2000, it))
                } ?: run {
                    ApiResult.Error(ApiError(2000, "Local Database Error"))
                }

            }
        }


    override suspend fun getFavRestaurants(): ApiResult<SearchResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val restaurants = mutableListOf<Restaurants>()
                val restaurantInner = appDao.getFavRestaurant()

                for (restaurant in restaurantInner) {
                    restaurants.add(Restaurants(restaurant))
                }

                ApiResult.Success(
                    SearchResponse(
                        restaurants.size,
                        0,
                        restaurants.size,
                        restaurants
                    )
                )
            } catch (e: Exception) {
                e.message?.let {
                    ApiResult.Error(ApiError(2000, it))
                } ?: run {
                    ApiResult.Error(ApiError(2000, "Local Database Error"))
                }

            }
        }


    override suspend fun saveRestaurantsDb(searchResponse: SearchResponse) {

        for (data in searchResponse.restaurants) {
            appDao.insertRestaurant(data.restaurant)
        }
    }


}