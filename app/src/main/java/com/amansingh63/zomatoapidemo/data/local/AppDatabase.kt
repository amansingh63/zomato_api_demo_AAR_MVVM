package com.amansingh63.zomatoapidemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amansingh63.zomatoapidemo.models.search.Restaurant


@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

}