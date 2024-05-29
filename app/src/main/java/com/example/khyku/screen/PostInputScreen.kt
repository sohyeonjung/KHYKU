package com.example.khyku.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.khyku.DB.Post
import com.example.khyku.viewmodel.PostViewModel



@Composable
fun InputScreen(viewModel: PostViewModel) {

    var postTitle by remember {
        mutableStateOf("")
    }
    var postContent by remember {
        mutableStateOf("")
    }
    var postType by remember {
        mutableStateOf("")
    }
    var postDone by remember {
        mutableStateOf(false)
    }

    val post = Post(postTitle, postContent, postType, postDone, getCurrentTime())

    fun clearText(){
        postTitle = " "
        postContent = " "
        postType = " "
    }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Text("스터디 글쓰기")
        Row {
            TextField(
                value = postType,
                onValueChange = {postType = it},
                label = { Text("분야")}
            )
            Switch(checked = postDone, onCheckedChange = {postDone=it} )

        }
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

        Row {
            Button(onClick = {
                viewModel.InsertPost(post); clearText()
            }) {
                Text("글쓰기")
            }
        }


    }

}

