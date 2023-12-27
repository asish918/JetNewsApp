package com.example.jetnewsapp.presentation.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetnewsapp.R
import com.example.jetnewsapp.data.model.NewsResponse
import com.example.jetnewsapp.presentation.ui.screen.details.DetailScreen
import com.example.jetnewsapp.presentation.ui.screen.news.NewsAppBar
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.Calisto
import com.example.jetnewsapp.presentation.ui.theme.Grey
import com.example.jetnewsapp.presentation.ui.theme.PrimaryRed
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.utils.dummyNewsItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun SearchScreen(
    navController: NavController,
) {
    val trial = 123
    val focusManager = LocalFocusManager.current

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Scaffold(
        topBar = {
            SearchAppBar(
            ) {
                navController.navigateUp()
            }
        },
        backgroundColor = Color.Transparent
    ) {
            innerPadding ->
        if (trial == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something went wrong!!!",
                    fontFamily = RockWell,
                    fontSize = 18.sp
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RectangleShape
                    )
                    .padding(12.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    onValueChange = {},
                    singleLine = true,
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = { Text(text = "Search News", color = Color.Black) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                    placeholder = { Text(text = "What are you looking for?", color = Color.Black) },
                    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "Search") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
    )
}