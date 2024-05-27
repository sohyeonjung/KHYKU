package com.example.khyku.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.khyku.rommDB.Post

@Composable
fun InputScreen(viewModel: ViewModel) {

    var postTitle by remember {
        mutableStateOf("")
    }
    var postContent by remember {
        mutableStateOf("")
    }

    val post = Post(postTitle, postContent)

    fun clearText(){
        postTitle = " "
        postContent = " "
    }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Text("스터디 글쓰기")
        TextField(
            value = postTitle,
            onValueChange = {postTitle = it},
            label = { Text("제목")}
        )
        TextField(
            value = postContent,
            onValueChange = {postContent = it},
            label = { Text("내용을 입력하세요 (시간, 장소, 진행 방식 등)")}
        )

    }

}