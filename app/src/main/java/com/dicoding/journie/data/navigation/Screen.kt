package com.dicoding.journie.data.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")
}
