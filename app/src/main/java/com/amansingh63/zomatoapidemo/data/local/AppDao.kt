package com.amansingh63.zomatoapidemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amansingh63.zomatoapidemo.models.search.Restaurant

@Dao
interface AppDao {


    @Query("SELECT * FROM restaurant ORDER BY isFavourite DESC")
    suspend fun getAllRestaurants(): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id = :id")
    suspend fun getRestaurantsById(id: Int): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE name like :query OR cuisines like :query")
    suspend fun getRestaurantByName(query: String): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE isFavourite IS (1)")
    suspend fun getFavRestaurant(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

}