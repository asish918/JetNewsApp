package com.example.jetnewsapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.presentation.ui.screen.details.DetailScreen
import com.example.jetnewsapp.presentation.ui.screen.home.HomeScreen
import com.example.jetnewsapp.presentation.ui.screen.intro.SplashScreen
import com.example.jetnewsapp.presentation.ui.screen.news.NewsScreen
import com.example.jetnewsapp.presentation.ui.screen.search.SearchScreen
import com.example.jetnewsapp.utils.decode

@Composable
fun AppNavGraph(
    navController: NavHostController,
    openDrawer: () -> Unit = {},
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, openDrawer = openDrawer)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = "${Screen.Detail.route}/{title}/{desc}/{imgUrl}/{content}/{pubAt}/{author}",
            arguments = listOf(
                navArgument("title") {type = NavType.StringType},
                navArgument("desc") {type = NavType.StringType},
                navArgument("imgUrl") {type = NavType.StringType},
                navArgument("content") {type = NavType.StringType},
                navArgument("pubAt") {type = NavType.StringType},
                navArgument("author") {type = NavType.StringType},
            )
        ) {
            val newsItem: NewsResponse.Article? = NewsResponse.Article(
                title = decode(it.arguments?.getString("title")),
                description = decode(it.arguments?.getString("desc")),
                urlToImage = decode(it.arguments?.getString("imgUrl")),
                content = decode(it.arguments?.getString("content")),
                publishedAt = decode(it.arguments?.getString("pubAt")),
                author = decode(it.arguments?.getString("author")),
                source = null,
                url = "",
            )
            DetailScreen(navController = navController, newsItem = newsItem)

        }
        composable(
            route = "${Screen.News.route}/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) {

            NewsScreen(
                navController = navController,
                category = it.arguments?.getString("category")
            )
        }

    }

}