package com.example.jetnewsapp.presentation.ui.screen.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.Calisto
import com.example.jetnewsapp.presentation.ui.theme.NEWS_ITEM_HEIGHT
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.utils.dummyNewsItem
import com.example.jetnewsapp.R

@Composable
fun NewsItem(
    news: NewsResponse.Article,
    onNewsItemClick: () -> Unit
) {

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 3f)
            .height(NEWS_ITEM_HEIGHT)
            .clickable { onNewsItemClick() },
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(8.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = news.urlToImage)
                .placeholder(R.drawable.newspaper)
                .error(R.drawable.newspaper)
                .build(),
            colorFilter = ColorFilter.colorMatrix(matrix),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
        ) {
            news.title?.let {
                Text(
                    modifier = Modifier.padding(top = 8.dp, end = 4.dp),
                    text = it,
                    maxLines = 2,
                    lineHeight = 18.sp,
                    fontFamily = RockWell,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Text(
                modifier = Modifier.padding(top = 6.dp, end = 4.dp),
                text = news.content ?: "",
                maxLines = 4,
                color = Black.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Calisto
            )
        }

    }

}


@Preview
@Composable
fun NewsItemPreview() {


    NewsItem(news = dummyNewsItem) {

    }

}
