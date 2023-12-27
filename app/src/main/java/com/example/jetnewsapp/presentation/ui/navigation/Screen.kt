package com.example.jetnewsapp.presentation.ui.navigation

sealed class Screen(val route:String) {
    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object News : Screen("news_screen")
    data object Detail : Screen("detail_screen")
    data object Search : Screen("search_screen")
}
