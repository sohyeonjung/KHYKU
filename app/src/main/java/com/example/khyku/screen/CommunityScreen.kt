@file:Suppress("DEPRECATION")

package com.example.khyku.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory
import com.example.khyku.viewmodel.Repository
import com.google.firebase.Firebase
import com.google.firebase.database.database

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun CommunityScreen(selectedPost: Post?=null) {
fun CommunityScreen() {

    val scrollState = rememberScrollState()

    val navController = rememberNavController()

    val table = Firebase.database.getReference("Products/items")
    val viewModel: PostViewModel =
        viewModel(factory = PostViewModelFactory(Repository(table)))

    val postlist by viewModel.postList.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getAllItems()
    }


    var presses by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }
    val KonkukGreen = Color(0xFF036B3F)


    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = KonkukGreen,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = "스터디원 찾기",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        ///++수정
        bottomBar = {
            BottomAppBar(
                containerColor = KonkukGreen,
                contentColor = Color.Black
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "수정해야할부분",
                )
            }
        },
        //+++수정
        floatingActionButton = {
            FloatingActionButton(
                onClick = { presses++ },
                containerColor = KonkukGreen) {
                Icon(Icons.Default.Edit, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.White,
//                    //textColor = Color.Black,
//                    focusedIndicatorColor = Color.White,
//                    unfocusedIndicatorColor = Color.White
//                ),
                    placeholder = { Text("제목 검색") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.getItems(searchText)
                    }),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    },
                    modifier = Modifier.width(250.dp),


                )
                Icon(Icons.Default.Refresh,
                    contentDescription = "update",
                    modifier = Modifier.width(100.dp)
                        .clickable {
                            viewModel.getAllItems()
                        }
                    )

            }

            Text("test")

            if(postlist.isEmpty())
               Text(text = "post 없음")

            PostList(
                list = postlist
            ) {
//                post ->
//                navController.navigate("post_detail/$post")
            }
        }
    }

}