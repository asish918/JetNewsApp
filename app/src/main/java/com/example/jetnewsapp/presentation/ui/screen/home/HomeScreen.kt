package com.example.jetnewsapp.presentation.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.example.jetnewsapp.presentation.ui.components.AnimatedShimmerItem
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.PAGING_INDICATOR_SPACING
import com.example.jetnewsapp.presentation.ui.theme.PAGING_INDICATOR_WIDTH
import com.example.jetnewsapp.presentation.ui.theme.PrimaryRed
import com.example.jetnewsapp.presentation.ui.screen.news.NewsItem
import com.example.jetnewsapp.utils.ResourceDrawable

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val pagerState = rememberPagerState()

    val topHeadLines = state.value.news.banners
    val everything = state.value.news.everything

    Scaffold(
        topBar = { HomeAppBar() },
        backgroundColor = Color.Transparent,
    ) {
        innerPadding ->
        LazyColumn(
        ) {
            item {
                HomeCategory(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 12.dp),
                    categories = viewModel.categories
                ) {
                    navController.currentBackStackEntry?.arguments?.putString(
                        "category",
                        it
                    )
                    navController.navigate(Screen.News.route)
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 8.dp, end = 8.dp)
                        .height(1.dp)
                        .background(Black)
                )
                Box {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        state = pagerState,
                        count = topHeadLines.size,
                        verticalAlignment = Alignment.Top
                    ) { position ->
                        val news = topHeadLines[position]
                        NewsSlider(
                            news = news
                        ) {
                            navController.currentBackStackEntry?.arguments?.putParcelable(
                                "news",
                                news
                            )
                            navController.navigate(Screen.Detail.route)
                        }
                    }
                    if (state.value.isLoading) {
                        AnimatedSliderShimmerItem()
                    }
                    if (!state.value.error.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = state.value.error!!)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .height(1.dp)
                        .background(Black)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .rotate(180f),
                        painter = painterResource(id = ResourceDrawable.ic_arrow_right),
                        contentDescription = null
                    )
                    HorizontalPagerIndicator(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        pagerState = pagerState,
                        activeColor = PrimaryRed,
                        inactiveColor = Color.Black.copy(alpha = 0.5f),
                        indicatorWidth = PAGING_INDICATOR_WIDTH,
                        spacing = PAGING_INDICATOR_SPACING,
                    )
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = ResourceDrawable.ic_arrow_right),
                        contentDescription = null
                    )
                }
            }

            // Shimmer for Home News
            if (state.value.isLoading) {
                items(8) {
                    AnimatedShimmerItem()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Black)
                    )
                }

            }

            itemsIndexed(everything) { index, news ->
                NewsItem(
                    news = news
                ) {
                    navController.currentBackStackEntry?.arguments?.putParcelable("news", news)
                    navController.navigate(Screen.Detail.route)
                }
                if (index < everything.lastIndex) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(1.dp)
                            .background(Black)
                    )
                }
            }

        }
    }

}

