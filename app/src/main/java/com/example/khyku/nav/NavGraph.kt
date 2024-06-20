package com.example.khyku.nav

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.khyku.HomeScreen.HomeScreen
import com.example.khyku.facilityScreen.FacilityScreen
import com.example.khyku.loginoutscreen.LoginScreen
import com.example.khyku.loginoutscreen.RegisterScreen
import com.example.khyku.roomDB.PostDatabase
import com.example.khyku.screen.CommunityScreen
import com.example.khyku.screen.PostDetailScreen
import com.example.khyku.screen.PostInputScreen
import com.example.khyku.viewmodel.PostRepository
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory
import com.example.khyku.yh.ProfileScreen.UserProfileScreen
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userDB.UserProfileDatabase
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import com.example.khyku.yh.userViewmodel.UserProfileViewModelFactory
import com.example.khyku.yh.userViewmodel.UserRepository

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {

    val context = LocalContext.current
    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
    val userviewModel: UserProfileViewModel =
        viewModel(factory = UserProfileViewModelFactory(UserRepository(userdb)))

    val postdb = PostDatabase.getPostDatabase(context)
    val postviewModel: PostViewModel =
        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }

    NavHost(navController = navController, startDestination = Routes.Login.route){
        composable(route = Routes.Community.route){
            CommunityScreen(navController, userProfile!!.userName)
        }
        composable(route = Routes.InputPost.route){
            PostInputScreen(viewModel = postviewModel, navController = navController, userProfile!!.userName)
        }
        composable(route = "Post/{userName}/{currentUserName}/{postTitle}/{postContents}/{postType}/{postId}",
            arguments = listOf(
                navArgument("userName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("currentUserName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("postTitle"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("postContents"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("postType"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("postId"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )

        ){
            PostDetailScreen(navController = navController,
                userName = it.arguments?.getString("userName"),
                currentUserName = it.arguments?.getString("currentUserName"),
                postTitle = it.arguments?.getString("postTitle") ,
                postContents = it.arguments?.getString("postContents"),
                postType = it.arguments?.getString("postType"),
                postId = it.arguments?.getString("postId")
            )
        }

        composable(route = Routes.Home.route){
            HomeScreen()
        }
        composable(route = Routes.Facility.route){
            FacilityScreen()
        }
        composable(route = Routes.Login.route){
            LoginScreen(navController = navController, viewModel = userviewModel) { profile ->
                userProfile = profile
                navController.navigate(Routes.Home.route)
            }
        }
        composable(route = Routes.Register.route){
            RegisterScreen(navController = navController, viewModel = userviewModel)
        }
        // userName 넘겨야함
        composable(route = Routes.Profile.route){
            userProfile?.let { profile ->
                UserProfileScreen(navController = navController, viewModel = userviewModel, userName = profile.userName)
            }
        }



    }
}

