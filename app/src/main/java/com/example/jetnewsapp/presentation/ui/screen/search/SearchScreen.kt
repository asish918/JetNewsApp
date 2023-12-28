package com.example.jetnewsapp.presentation.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.utils.dummyNewsItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import com.example.jetnewsapp.presentation.ui.screen.news.NewsItem
import com.example.jetnewsapp.utils.encode
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val trial = 123
    val searchResult = viewModel.multiSearchState.value.collectAsLazyPagingItems()
    var searchInput: String by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    LaunchedEffect(key1 = searchInput) {
        if (viewModel.searchParam.value.trim().isNotEmpty() &&
            viewModel.searchParam.value.trim().length != viewModel.previousSearch.value.length
        ) {
            delay(750)
            viewModel.searchRemoteMovie()
            viewModel.previousSearch.value = searchInput.trim()
        }
    }

    Scaffold(
        topBar = {
            SearchAppBar(
            ) {
                navController.navigateUp()
            }
        },
        backgroundColor = Color.Transparent
    ) { innerPadding ->
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
                    value = searchInput,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    onValueChange = { newValue ->
                        searchInput = if (newValue.trim().isNotEmpty()) newValue else ""
                        viewModel.searchParam.value = searchInput
                    },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = { Text(text = "Search News", color = Color.Black) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        if (viewModel.searchParam.value.trim().isNotEmpty()) {
                            focusManager.clearFocus()
                            viewModel.searchParam.value = searchInput
                            if (searchInput != viewModel.previousSearch.value) {
                                viewModel.previousSearch.value = searchInput
                                viewModel.searchRemoteMovie()
                            }
                        }
                    }
                    ),
                    placeholder = { Text(text = "What are you looking for?", color = Color.Black) },
                    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = "Search") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    when (searchResult.loadState.refresh) {
                        is LoadState.NotLoading -> {
                            items(searchResult.itemCount) { index ->
                                val item = searchResult[index]
                                if (item != null) {
                                    NewsItem(news = item) {
                                        val encodedTitle = encode(item.title)
                                        val encodedDesc = encode(item.description)
                                        val encodedImgUrl = encode(item.urlToImage)
                                        val encodedContent = encode(item.content)
                                        val encodedPubAt = encode(item.publishedAt)
                                        val encodedAuthor = encode(item.author)
                                        val navArgs =
                                            "${encodedTitle}/${encodedDesc}/${encodedImgUrl}/${encodedContent}/${encodedPubAt}/${encodedAuthor}"
                                        navController.navigate("${Screen.Detail.route}/${navArgs}")
                                    }
                                } else
                                    NewsItem(news = dummyNewsItem) {
                                        val encodedTitle = encode(dummyNewsItem.title)
                                        val encodedDesc = encode(dummyNewsItem.description)
                                        val encodedImgUrl = encode(dummyNewsItem.urlToImage)
                                        val encodedContent = encode(dummyNewsItem.content)
                                        val encodedPubAt = encode(dummyNewsItem.publishedAt)
                                        val encodedAuthor = encode(dummyNewsItem.author)
                                        val navArgs =
                                            "${encodedTitle}/${encodedDesc}/${encodedImgUrl}/${encodedContent}/${encodedPubAt}/${encodedAuthor}"
                                        navController.navigate("${Screen.Detail.route}/${navArgs}")
                                    }
                            }
                            if (searchResult.itemCount == 0) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 60.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "Hard Luck")
                                    }

                                }
                            }
                        }

                        is LoadState.Loading -> item {
                            if (viewModel.searchParam.value.isNotEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    LinearProgressIndicator(
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                        else -> item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Hard Luck")
                            }
                        }
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun SearchScreenPreview() {
//    SearchScreen(
//        navController = rememberNavController(),
//    )
//}