package com.morteza.sadapay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.morteza.sadapay.ui.trending.TrendingMainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Trending.route) {
        composable(route = Screens.Trending.route) {
            TrendingMainScreen(navController = navController)
        }
    }
}