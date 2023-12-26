package com.example.jetnewsapp.presentation.ui.screen.home

import com.example.jetnewsapp.data.model.NewsResponse

data class HomeDataState(
    var isLoading: Boolean = false,
    val news: HomeData = HomeData(emptyList(), emptyList()),
    val error: String? = ""
)

data class HomeData(
    val banners: List<NewsResponse.Article> = emptyList(),
    val everything: List<NewsResponse.Article> = emptyList(),
)