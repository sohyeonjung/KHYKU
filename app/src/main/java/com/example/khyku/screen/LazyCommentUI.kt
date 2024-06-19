package com.example.khyku.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        //.padding(top=10.dp)
        .fillMaxWidth()
        .background(
            color = Color.White
        )){
        Text(text="comment.userName", fontWeight = FontWeight.Bold, fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 5.dp, top=5.dp))
        Text(text = comment.commentContents, fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 5.dp))
        Text(text = comment.commentTime, fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 5.dp))
        //recomments도 구현해야함
    }
    //Spacer(modifier = Modifier.padding(top=10.dp).background(color = Color.LightGray))

}