@file:Suppress("DEPRECATION")

package com.example.khyku.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.roomDB.PostDatabase
import com.example.khyku.viewmodel.PostRepository
import com.example.khyku.viewmodel.PostViewModel
import com.example.khyku.viewmodel.PostViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    val context = LocalContext.current
    val postdb = PostDatabase.getPostDatabase(context)
    val viewModel: PostViewModel =
        viewModel(factory = PostViewModelFactory(PostRepository(postdb)))

    val postlist by viewModel.postList.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getAllItems()
    }


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
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(100.dp, 50.dp),
                        fontSize = 30.sp //이게 크기 최대
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
                onClick = {
                          //navigate
                },
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
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = KonkukGreen
                    ),
                    placeholder = {
                        Text( text = "스터디 검색", color = Color.White) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.getItems(searchText)
                    }),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    },
                    modifier = Modifier.width(360.dp)

                )
                Icon(Icons.Default.Refresh,
                    contentDescription = "update",
                    modifier = Modifier
                        .size(56.dp)
                        .background(color = KonkukGreen)
                        .clickable {
                            viewModel.getAllItems()
                        },
                )

            }

            if(postlist.isEmpty()){
                Text(text = "해당하는 게시물이 없습니다.",
                    modifier = Modifier.padding(start = 115.dp))
                searchText=" "
            }

            PostList(
                list = postlist
            ) {
//                post ->
//                navController.navigate("post_detail/$post")
            }
        }

    }

}