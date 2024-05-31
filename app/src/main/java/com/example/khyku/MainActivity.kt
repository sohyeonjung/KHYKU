package com.example.khyku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.khyku.screen.CommunityScreen
import com.example.khyku.ui.theme.KHYKUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KHYKUTheme {
                // A surface container using the 'background' colo  r from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    //MainScreen()
                    CommunityScreen(navController)
                    //val post = Post("title", "contents", "type", false, " ")
//                    PostDetailScreen(post)

//                    Scaffold(
//                        bottomBar = { BottomNavigationBar(navController = navController) }
//                    ) {
//                        Box(modifier = Modifier.padding(it)){
//                            NavGraph(navController = navController)
//                        }
//                    }

                }
            }
        }
    }
}

//@Composable
//fun MainScreen(){
//    val context = LocalContext.current
//    val table = Firebase.database.getReference("Products/items")
//    val viewModel: PostViewModel =
//        viewModel(factory = PostViewModelFactory(PostRepository(table)))
//
//    val postlist by viewModel.postList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
//    var selectedPost: Post? by remember {
//        mutableStateOf(null)
//    }
//    val selectedEvent = {post: Post -> selectedPost = post }
//
//    PostInputScreen(viewModel = viewModel)
//
//
//}

