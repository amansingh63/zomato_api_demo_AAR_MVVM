package com.amansingh63.zomatoapidemo.data.remote

import com.amansingh63.zomatoapidemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()

        //Only add if app api is host
        if (BuildConfig.BASE_URL.contains(original.url.host, true)) {
            builder.addHeader("user-key", BuildConfig.ZOMATO_API_KEY)
        }
        val request = builder.build()
        return chain.proceed(request)
    }
}