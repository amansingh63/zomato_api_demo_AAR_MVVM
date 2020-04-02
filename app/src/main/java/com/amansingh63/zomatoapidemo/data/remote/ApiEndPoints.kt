package com.amansingh63.zomatoapidemo.data.remote

import com.amansingh63.zomatoapidemo.models.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiEndPoints {

    @GET("/api/v2.1/search")
    suspend fun search(@QueryMap queryMap: Map<String, String>): Response<SearchResponse>
}