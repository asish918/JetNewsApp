package com.example.jetnewsapp.presentation.ui.screen.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.presentation.ui.components.ShimmerEffect
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.SMALL_PADDING
import com.example.jetnewsapp.utils.encode

@Composable
fun ListContent(
    navController: NavController,
    news: LazyPagingItems<NewsResponse.Article>,
) {

    val result = handlePagingResult(news = news)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(news.itemCount) { index ->
                val item = news[index]

                item?.let {
                    NewsItem(news = it) {
                        val encodedTitle = encode(item.title)
                        val encodedDesc = encode(item.description)
                        val encodedImgUrl = encode(item.urlToImage)
                        val encodedContent = encode(item.content)
                        val encodedPubAt = encode(item.publishedAt)
                        val encodedAuthor = encode(item.author)
                        val encodedUrl = encode(item.url)
                        val navArgs = "${encodedTitle}/${encodedDesc}/${encodedImgUrl}/${encodedContent}/${encodedPubAt}/${encodedAuthor}/${encodedUrl}"
                        navController.navigate("${Screen.Detail.route}/${navArgs}")
                    }
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


@Composable
fun handlePagingResult(
    news: LazyPagingItems<NewsResponse.Article>,
): Boolean {

    news.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            news.itemCount < 1 -> {
                //EmptyScreen()
                false
            }
            else -> true
        }
    }


}


@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "EMPTY")
    }
}