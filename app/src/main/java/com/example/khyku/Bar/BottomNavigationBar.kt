package com.example.khyku

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar(containerColor = colorResource(id = R.color.konkukgreen),
        contentColor = colorResource(id = R.color.konkukgreen)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == navItem.route) navItem.onSelectedIcon else navItem.selectIcon,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = colorResource(id = R.color.BarUnselectedText),
                    unselectedIconColor = colorResource(id = R.color.BarUnselectedText),
                    indicatorColor = colorResource(id = R.color.BarGreen)
                )
            )
        }
    }
}

