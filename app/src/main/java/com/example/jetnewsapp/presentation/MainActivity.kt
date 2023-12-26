package com.example.jetnewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.jetnewsapp.presentation.ui.theme.JetNewsAppTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.jetnewsapp.R
import com.example.jetnewsapp.presentation.ui.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNewsAppTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.background),
                        contentScale = ContentScale.Crop,
                        contentDescription = null)

                    AppNavGraph(navController = navController)

                }
            }
        }
    }
}