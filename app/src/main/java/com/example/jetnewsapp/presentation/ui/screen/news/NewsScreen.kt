package com.example.jetnewsapp.presentation.ui.screen.news

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        val category =
            navController.previousBackStackEntry?.arguments?.getString("category") ?: "technology"
        newsViewModel.getNewsByCategory(category)
        Log.d("SelectedCategory", "NewsScreen: $category")
    }

    val news = newsViewModel.allNews.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            NewsAppBar() {
                navController.navigateUp()
            }
        },
        backgroundColor = Color.Transparent,
        content = {
            innerPadding ->
            ListContent(
                news = news,
                navController = navController
            )

        }
    )
}