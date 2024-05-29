package com.example.khyku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.khyku.roomDB.Post
import com.example.khyku.screen.InputScreen
import com.example.khyku.ui.theme.KHYKUTheme
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory
import com.example.khyku.viewmodel.Repository
import com.google.firebase.Firebase
import com.google.firebase.database.database

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
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val context = LocalContext.current
    val table = Firebase.database.getReference("Products/items")
    val viewModel:PostViewModel =
        viewModel(factory = PostViewModelFactory(Repository(table)))

    val postlist by viewModel.postList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
    var selectedPost: Post? by remember {
        mutableStateOf(null)
    }
    val selectedEvent = {post:Post -> selectedPost = post }
    InputScreen(viewModel = viewModel)


}

