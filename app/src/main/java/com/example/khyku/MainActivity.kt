// MainActivity
package com.example.khyku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController
import com.example.khyku.roomDB.Post
import com.example.khyku.roomDB.PostDatabase
import com.example.khyku.screen.PostInputScreen
import com.example.khyku.ui.theme.KHYKUTheme
import com.example.khyku.viewmodel.PostRepository
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory
import com.example.khyku.yh.ProfileScreen.InputScreen
import com.example.khyku.yh.ProfileScreen.ProfileScreen
//import com.example.khyku.yh.ProfileScreen.ProfileScreen
import com.example.khyku.yh.ProfileScreen.UserList
import com.example.khyku.yh.userDB.Subject
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userDB.UserProfileDatabase
import com.example.khyku.yh.userViewmodel.UserRepository
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import com.example.khyku.yh.userViewmodel.ViewModelFactory


@ExperimentalMaterial3Api
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
//                    val navController = rememberNavController()
//                    //MainScreen()
//                    //CommunityScreen()
////                    val post = Post("title", "sdkjakljkxnkljskd/ndksljfksjdk;sj",
////                        "programming",true, getCurrentTime() )
//
//                    //PostDetailScreen(2, navController)
//
//                    NavGraph(navController = navController)

                    UserProfileScreen()
                }
            }
        }
    }
}
@Composable
fun MainScreen(){
    val context = LocalContext.current
    val postdb = PostDatabase.getPostDatabase(context)
    val viewModel:PostViewModel =
        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))

    val postlist by viewModel.postList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
    var selectedPost: Post? by remember{
        mutableStateOf<Post?>(null)
    }
    val selectedEvent = {post:Post -> selectedPost = post }

    val navController = rememberNavController()
    PostInputScreen(viewModel = viewModel, navController = navController)
}


@Composable
fun UserProfileScreen() {
    val context = LocalContext.current
    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
    val viewModel: UserProfileViewModel =
        viewModel(factory = ViewModelFactory(UserRepository(userdb)))

    val userlist by viewModel.userList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
    var selectedUser: UserProfile? by remember {
        mutableStateOf<UserProfile?>(null)
    }
    val selectedEvent = { user: UserProfile -> selectedUser = user }
    val subjectExample = selectedUser?.let { Subject("DB","pink", it.maxFocusTime,true) }
    Column(modifier = Modifier.fillMaxSize()) {
        InputScreen(viewModel = viewModel, selectedUser)
        if (subjectExample != null) {
            ProfileScreen(viewModel = viewModel, selectedUser, subjectExample)
        }
        UserList(list = userlist, onClick = selectedEvent)
    }
}
