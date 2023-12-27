package com.example.jetnewsapp.presentation.ui.screen.search

import androidx.lifecycle.ViewModel
import com.example.jetnewsapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

}