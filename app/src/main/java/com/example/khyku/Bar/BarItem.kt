package com.example.khyku

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem (val title :String, val selectIcon: ImageVector, val onSelectedIcon :ImageVector, val route:String)

object NavBarItems{
    val BarItems = listOf(
        BarItem(
            title = "Profile",
            selectIcon = Icons.Default.AccountCircle,
            onSelectedIcon = Icons.Outlined.AccountCircle,
            route = "Profile"
        ),
        BarItem(
            title = "Ranking",
            selectIcon = Icons.Default.Star,
            onSelectedIcon = Icons.Outlined.Star,
            route = "Ranking"
        ),
        BarItem(
            title = "Home",
            selectIcon = Icons.Default.Home,
            onSelectedIcon = Icons.Outlined.Home,
            route = "Home"
        ),
        BarItem(
            title = "Community",
            selectIcon = Icons.Default.List,
            onSelectedIcon = Icons.Outlined.List,
            route = "Community"
        ),
        BarItem(
            title = "Facility",
            selectIcon = Icons.Default.Build,
            onSelectedIcon = Icons.Outlined.Build,
            route = "Facility"
        )
    )
}