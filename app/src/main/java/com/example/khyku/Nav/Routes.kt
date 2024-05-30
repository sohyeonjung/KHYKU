package com.example.khyku.Nav


sealed class Routes (val route: String) {
    object Home : Routes("Home")
    object Profile : Routes("Profile")
    object Ranking : Routes("Ranking")
    object Notice : Routes("Notice")
    object Facility : Routes("Facility")
    object Subject : Routes("Subject")
}