package com.example.jetnews.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    
    @GET("v2/everything")
    suspend fun getEverything(
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
        @Query("q") q: String? = "apple",
        @Query("from") from: String? = "2025-06-20",
        @Query("to") to: String? = "2025-06-21",
        @Query("language") language: String? = "es"
    ): NewsApiResponse
} 