package com.example.jetnewsapp.presentation.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository,
): ViewModel() {
    private var _multiSearch = mutableStateOf<Flow<PagingData<NewsResponse.Article>>>(emptyFlow())
    val multiSearchState: State<Flow<PagingData<NewsResponse.Article>>> = _multiSearch

    var searchParam = mutableStateOf("")
    var previousSearch = mutableStateOf("")
    var searchParamState: State<String> = searchParam

    init {
        searchParam.value = ""
    }

    fun searchRemoteMovie() {
        viewModelScope.launch {
            if (searchParam.value.isNotEmpty()) {
                _multiSearch.value = repository.searchNews(
                    searchParam.value,
                ).cachedIn(viewModelScope)
            }
        }
    }
}