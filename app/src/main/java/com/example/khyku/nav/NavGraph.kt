package com.example.khyku.nav

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.khyku.loginoutscreen.LoginScreen
import com.example.khyku.loginoutscreen.RegisterScreen

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {

    val context = LocalContext.current
//    val postdb = PostDatabase.getPostDatabase(context)
//    val postviewModel:PostViewModel =
//        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))

    NavHost(navController = navController, startDestination = Routes.Login.route){
//        composable(route = Routes.Community.route){
//           CommunityScreen(navController)
//        }
//        composable(route = Routes.InputPost.route){
//            PostInputScreen(viewModel = postviewModel, navController = navController)
//        }
//        composable(route = "Post/{postTitle}/{postContents}/{postType}/{postId}",
//            arguments = listOf(
//                navArgument("postTitle"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                },
//                navArgument("postContents"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                },
//                navArgument("postType"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                },
//                navArgument("postId"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
//
//        ){
//            PostDetailScreen(navController = navController,
//                postTitle = it.arguments?.getString("postTitle") ,
//                postContents = it.arguments?.getString("postContents"),
//                postType = it.arguments?.getString("postType"),
//                postId = it.arguments?.getString("postId")
//            )
//        }
        composable(route = Routes.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = Routes.Register.route){
            RegisterScreen(navController = navController)
        }
    }
}