package com.example.jetnewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.jetnewsapp.presentation.ui.theme.JetNewsAppTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetnewsapp.R
import com.example.jetnewsapp.presentation.ui.navigation.AppNavGraph
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNewsAppTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute =
                    navBackStackEntry?.destination?.route ?: Screen.Home.route

                ModalNavigationDrawer(
                    drawerContent = {
                        AppDrawer(
                            currentRoute = currentRoute,
                            navigateToHome = { navController.navigate(Screen.Home.route) },
                            navigateToInterests = { navController.navigate("${Screen.Home.route}/Sports") },
                            closeDrawer = { coroutineScope.launch { drawerState.close() } }
                        )
                    },
                    drawerState = drawerState,
                    // Only enable opening the drawer via gestures if the screen is not expanded
                ) {
                    // A surface container using the 'background' color from the theme
                    Box(modifier = Modifier) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.background),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )

                        AppNavGraph(
                            navController = navController,
                            openDrawer = { coroutineScope.launch { drawerState.open() } },
                        )

                    }
                }
            }
        }
    }
}