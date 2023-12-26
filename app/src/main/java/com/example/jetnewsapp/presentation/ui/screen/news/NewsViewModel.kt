package com.example.jetnewsapp.presentation.ui.screen.news

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

    private val repository: NewsRepository

) : ViewModel() {

    var allNews : Flow<PagingData<NewsResponse.Article>> = emptyFlow()

    fun getNewsByCategory(category : String) {
        allNews = repository.getNewsByCategory(category)
    }


}
