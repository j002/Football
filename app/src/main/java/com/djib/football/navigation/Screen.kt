package com.djib.football.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object ListCountry : Screen("list_country_screen")
}