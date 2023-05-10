package com.morteza.sadapay.ui.trending

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController


@Composable
fun TrendingMainScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Trending",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                elevation = AppBarDefaults.TopAppBarElevation,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                TrendingScreen(navController)
            }
        }
    )
}

@Composable
fun TrendingScreen(navController: NavController) {

}
