package com.example.jetnewsapp.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.jetnewsapp.R
import com.example.jetnewsapp.presentation.ui.navigation.Screen
import com.example.jetnewsapp.presentation.ui.theme.Black
import com.example.jetnewsapp.presentation.ui.theme.Calisto
import com.example.jetnewsapp.presentation.ui.theme.RockWell
import com.example.jetnewsapp.utils.ResourceDrawable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@Composable
fun HomeAppBar(
    openDrawer: () -> Unit = {},
    navController: NavController?,
    ) {
    val currentDate = LocalDate.now()
    val formatter = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    val formattedDate = formatter.format(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {

        Box(
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = openDrawer,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
            ) {
                Image(
                    painter = painterResource(id = ResourceDrawable.ic_menu),
                    contentDescription = null
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(
//                    text = "DAILY NEWS",
//                    fontFamily = RockWell,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp
//                )
                Image(
                    painter = painterResource(id = R.drawable.newspaper_logo),
                    contentDescription = "logo",
                    modifier = Modifier.height(25.dp).padding(bottom = 4.dp)
                )
                Text(
                    text = formattedDate,
                    fontFamily = Calisto,
                    fontSize = 16.sp
                )
            }

            IconButton(
                onClick = {
                    navController?.navigate(Screen.Search.route)
                },
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
            ) {
                Image(

                    painter = painterResource(id = ResourceDrawable.ic_search),
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Black)
        )

    }

}


@Preview
@Composable
fun HomeAppBarPreview() {
    HomeAppBar(
        navController = null
    )
}