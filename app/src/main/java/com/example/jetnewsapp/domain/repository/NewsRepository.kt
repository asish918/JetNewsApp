package com.example.jetnewsapp.domain.repository

import androidx.paging.PagingData
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(): Flow<Resource<NewsResponse>>

    fun getEverything(): Flow<Resource<NewsResponse>>

    fun getNewsByCategory(category: String) : Flow<PagingData<NewsResponse.Article>>

    fun searchNews(query : String) : Flow<PagingData<NewsResponse.Article>>

    fun searchNewsFlow(query: String) : Flow<PagingData<NewsResponse.Article>>

    fun getNewsCategories() : List<String>
}