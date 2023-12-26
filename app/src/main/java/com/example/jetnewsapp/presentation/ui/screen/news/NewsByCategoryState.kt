package com.example.jetnewsapp.presentation.ui.screen.news

import androidx.paging.PagingData
import com.example.jetnewsapp.data.model.NewsResponse

data class NewsByCategoryState(
    var isLoading: Boolean = false,
    val news: PagingData<NewsResponse.Article>,
    val error: String? = ""
)

