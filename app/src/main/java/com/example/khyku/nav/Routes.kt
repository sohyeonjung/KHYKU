package com.example.khyku.nav


sealed class Routes (val route: String) {
    object Home : Routes("Home")
    object Facility : Routes("Facility")
    object Community : Routes("Community")
    object InputPost : Routes("InputPost")
    object Login : Routes("Login")
    object Register : Routes("Register")
    object Profile : Routes("Profile")
}