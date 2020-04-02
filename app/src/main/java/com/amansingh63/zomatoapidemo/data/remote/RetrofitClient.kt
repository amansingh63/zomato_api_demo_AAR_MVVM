package com.amansingh63.zomatoapidemo.data.remote

import com.amansingh63.zomatoapidemo.BuildConfig
import com.amansingh63.zomatoapidemo.util.KotlinxJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {

    @JvmStatic
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.apply {
            addInterceptor(HeaderInterceptor())
            addInterceptor(getHttpLoggingInterceptor())
        }.build()
    }

    @JvmStatic
    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }


    @JvmStatic
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                KotlinxJson.instance.asConverterFactory("application/json".toMediaType())
            )
            .baseUrl(BuildConfig.BASE_URL)
            .client(getOkHttpClient()).build()
    }
}