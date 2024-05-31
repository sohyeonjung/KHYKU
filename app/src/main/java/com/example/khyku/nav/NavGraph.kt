package com.example.khyku.nav

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.khyku.roomDB.PostDatabase
import com.example.khyku.screen.CommunityScreen
import com.example.khyku.screen.PostDetailScreen
import com.example.khyku.screen.PostInputScreen
import com.example.khyku.viewmodel.PostRepository
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {

    val context = LocalContext.current
    val postdb = PostDatabase.getPostDatabase(context)
    val postviewModel:PostViewModel =
        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))

    NavHost(navController = navController, startDestination = Routes.Community.route){
        composable(route = Routes.Community.route){
           CommunityScreen(navController)
        }
        composable(route = Routes.InputPost.route){
            PostInputScreen(viewModel = postviewModel, navController = navController)
        }
        composable("Post/{postId}"){ backStackEntry->
            PostDetailScreen(backStackEntry.arguments?.getInt("postId"),navController)
        }
    }
}