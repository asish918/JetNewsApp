package com.example.jetnewsapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.utils.parcelable
import com.example.jetnewsapp.presentation.ui.screen.details.DetailScreen
import com.example.jetnewsapp.presentation.ui.screen.home.HomeScreen
import com.example.jetnewsapp.presentation.ui.screen.intro.SplashScreen
import com.example.jetnewsapp.presentation.ui.screen.news.NewsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route
        ) {
            val newsItem =
                navController.previousBackStackEntry?.arguments?.parcelable<NewsResponse.Article>("news")
            DetailScreen(navController = navController, newsItem = newsItem)

        }
        composable(route = Screen.News.route) {

            NewsScreen(
                navController = navController
            )
        }

    }

}