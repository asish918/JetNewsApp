package com.example.jetnewsapp.data.remote

import com.example.jetnewsapp.BuildConfig
import com.example.jetnewsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val BASE_URL = "https://news-api-dummy.onrender.com/"
    }

    @GET("news")
    suspend fun getTopHeadLines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): NewsResponse


    @GET("news")
    suspend fun getEverything(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): NewsResponse

    @GET("news")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse

    @GET("news")
    suspend fun searchNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse


}