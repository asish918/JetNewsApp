package com.example.jetnewsapp.presentation.ui.screen.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.R

@Composable
fun NewsAppBar(
    title: String?,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 56.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .height(30.dp)
                    .width(30.dp)
                    .clickable {
                        onBackClick()
                    },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )

            Text(
                text = title ?: "NEWS",
                fontFamily = RockWell,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

        }

        Box(
            modifier = Modifier
                .padding(top = 4.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)

        )
    }

}


@Preview
@Composable
fun NewsAppBarPreview() {
    NewsAppBar(
        title = "NEWS"
    ) {}
}