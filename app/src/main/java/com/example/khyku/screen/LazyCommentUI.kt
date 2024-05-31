package com.example.khyku.screen

import androidx.compose.foundation.layout.Column
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
import com.example.khyku.roomDB.Comment
@Composable
fun CommentList(list: List<Comment>) {
    LazyColumn {
        items(list) { comment ->
            CommentUI(comment)
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
fun CommentUI(comment:Comment) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        //Text(text=comment.userName)
        Text(text = comment.commentContents)
        Text(text = comment.commentTime)
        //recomments도 구현해야함
    }

}