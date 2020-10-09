package com.amansingh63.zomatoapidemo.di.module.data

import android.content.Context
import androidx.room.Room
import com.amansingh63.zomatoapidemo.data.AppDataSource
import com.amansingh63.zomatoapidemo.data.AppRepository
import com.amansingh63.zomatoapidemo.data.AppRepositoryImpl
import com.amansingh63.zomatoapidemo.data.local.AppDatabase
import com.amansingh63.zomatoapidemo.data.local.AppLocalDataSource
import com.amansingh63.zomatoapidemo.data.pref.AppPreference
import com.amansingh63.zomatoapidemo.data.pref.AppPreferenceImpl
import com.amansingh63.zomatoapidemo.data.remote.ApiEndPoints
import com.amansingh63.zomatoapidemo.data.remote.AppRemoteDataSource
import com.amansingh63.zomatoapidemo.data.remote.RetrofitClient
import com.amansingh63.zomatoapidemo.util.network.RetrofitErrorUtil
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object RepositoryModule {


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @Provides
    @Singleton
    fun getRetorofit(): Retrofit {
        return RetrofitClient.getRetrofit()
    }

    @Provides
    @Singleton
    fun getApiEndPoints(retrofit: Retrofit): ApiEndPoints {
        return retrofit.create(ApiEndPoints::class.java)
    }

    @JvmStatic
    @Singleton
    @LocalDataSource
    @Provides
    fun provideAppLocalDataSource(
        database: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ): AppDataSource {
        return AppLocalDataSource(
            database.appDao(), ioDispatcher
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "zomato_restaurant.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @RemoteDataSource
    @Provides
    fun provideAppRemoteDataSource(
        apiEndPoints: ApiEndPoints,
        retrofitErrorUtil: RetrofitErrorUtil,
        appPreference: AppPreference
    ): AppDataSource {
        return AppRemoteDataSource(
            apiEndPoints,
            retrofitErrorUtil,
            appPreference
        )
    }


    @JvmStatic
    @Singleton
    @Provides
    fun getApiErrorUtil(retrofit: Retrofit) =
        RetrofitErrorUtil(retrofit)

    @JvmStatic
    @Singleton
    @Provides
    fun getAppPrefs(context: Context): AppPreference =
        AppPreferenceImpl(context.applicationContext)

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: AppRepositoryImpl): AppRepository
}