package com.example.khyku.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.khyku.roomDB.Post

@Composable
fun PostDetailScreen(post: Post) {
    Column {
        Text(text="스터디원 구하기")
        //사람 정보
        Text(text = post.postTitle)
        Text(text=post.postType)
        Text(text = post.postContents)
        //여기에 lazycommentUI (댓글)
        FloatingActionButton(onClick = {  }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }

}