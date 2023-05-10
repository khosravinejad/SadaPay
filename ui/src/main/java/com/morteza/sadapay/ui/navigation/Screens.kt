package com.morteza.sadapay.ui.navigation

sealed class Screens(val route: String) {
    object Trending : Screens("trending")
}
