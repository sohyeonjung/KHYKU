package com.example.khyku.Nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.khyku.screens.FacilityScreen
import com.example.khyku.screens.HomeScreen
import com.example.khyku.screens.NoticeScreen
import com.example.khyku.screens.ProfileScreen
import com.example.khyku.screens.RankingScreen

//하단바 및 그래프
//https://velog.io/@chuu1019/Android-Jetpack-Compose-Bottom-Navigation-%EB%A7%8C%EB%93%A4%EA%B8%B0

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(route = Routes.Home.route){
            HomeScreen()
        }
        composable(route = Routes.Facility.route){
            FacilityScreen()
        }
        composable(route = Routes.Notice.route){
            NoticeScreen()
        }
        composable(route = Routes.Profile.route){
            ProfileScreen()
        }
        composable(route = Routes.Ranking.route){
            RankingScreen()
        }
    }
}