package com.example.khyku.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.khyku.roomDB.Post

@Composable
fun PostList(list: List<Post>, onClick: (post:Post) -> Unit) {
    LazyColumn {
        items(list) { post ->
            PostUI(post, onClick)
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
fun PostUI(post: Post, onClick: (post:Post) -> Unit) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .clickable { onClick(post)}){
        Row {
            var donetext:String = "모집중"
            if(post.postDone==false) donetext="모집 완료"
            Text(text = donetext)
            Text(text = post.postType)
        }
        Text(text = post.postTitle)
        Text(text = post.postTime)
    }
}