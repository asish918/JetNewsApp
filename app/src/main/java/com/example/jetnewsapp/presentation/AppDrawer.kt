package com.example.jetnewsapp.presentation

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnewsapp.R
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import com.example.jetnewsapp.presentation.ui.theme.Grey
import com.example.jetnewsapp.presentation.ui.theme.JetNewsAppTheme
import com.example.jetnewsapp.presentation.ui.theme.PrimaryRed
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.presentation.ui.theme.ShimmerDarkGray

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier) {
        Box(modifier = Modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
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
                JetNewsLogo(
                    modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.home_title), color = Color.White) },
                    icon = { Icon(Icons.Filled.Home, null, tint = Color.White) },
                    selected = currentRoute == Screen.Home.route,
                    onClick = { navigateToHome(); closeDrawer() },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryRed, unselectedContainerColor = Grey),
                    shape = RectangleShape
                )
                Spacer(modifier = Modifier.height(20.dp))
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.saved_title), color = Color.White) },
                    icon = { Icon(Icons.Filled.Search, null, tint = Color.White) },
                    selected = currentRoute == Screen.News.route,
                    onClick = { navigateToInterests(); closeDrawer() },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryRed, unselectedContainerColor = Grey),
                    shape = RectangleShape

                )
            }
        }
    }
}

@Composable
private fun JetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(R.drawable.ic_jetnews_logo),
            contentDescription = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Menu",
            fontFamily = RockWell,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    JetNewsAppTheme {
        AppDrawer(
            currentRoute = Screen.Home.route,
            navigateToHome = {},
            navigateToInterests = {},
            closeDrawer = { }
        )
    }
}
