// MainActivity
package com.example.khyku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.khyku.nav.NavGraph
import com.example.khyku.ui.theme.KHYKUTheme

//import com.example.khyku.ui.theme.KHYKUTheme
//import com.example.khyku.roomDB.Post
//import com.example.khyku.roomDB.PostDatabase
//import com.example.khyku.screen.PostInputScreen
//import com.example.khyku.viewmodel.PostRepository
//import com.example.khyku.viewmodel.PostViewModel
//import com.example.khyku.viewmodel.PostViewModelFactory
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KHYKUTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    //MainScreen()
//                    //CommunityScreen()
////                    val post = Post("title", "sdkjakljkxnkljskd/ndksljfksjdk;sj",
////                        "programming",true, getCurrentTime() )
//
//                    //PostDetailScreen(2, navController)
//
//                    NavGraph(navController = navController)

                    //val navController = rememberNavController()
                    //NavGraph(navController = navController)

                    val navController = rememberNavController()
                    var navBackStackEntry = navController.currentBackStackEntryAsState()
                    var isBottomBarVisible = true

                    navBackStackEntry.value?.destination?.route.let{ route ->
                        isBottomBarVisible = when(route){
                            "Community" -> true
                            "Home" -> true
                            "Profile" -> true
                            "Facility" -> true
                            else -> false
                        }
                    }

                    Scaffold(
                        bottomBar = {
                            if(isBottomBarVisible)
                                BottomNavigationBar(navController = navController)
                        },

                        ) {
                        Box(Modifier.padding(it)){
                            NavGraph(navController = navController)
                        }
                    }
                    //UserProfileScreen("yh")
                }
            }
        }
    }
}
//@Composable
//fun MainScreen(){
//    val context = LocalContext.current
//    val postdb = PostDatabase.getPostDatabase(context)
//    val viewModel:PostViewModel =
//        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))
//
//    val postlist by viewModel.postList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
//    var selectedPost: Post? by remember{
//        mutableStateOf<Post?>(null)
//    }
//    val selectedEvent = {post:Post -> selectedPost = post }
//
//    val navController = rememberNavController()
//    PostInputScreen(viewModel = viewModel, navController = navController)
//}



