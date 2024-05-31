package com.example.khyku.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.khyku.roomDB.Comment
import com.example.khyku.viewmodel.CommentViewModel

@Composable
fun CommentInputDialog(
    onDismiss: () -> Unit,
    viewModel: CommentViewModel
) {
    var commentText by remember { mutableStateOf(" ") }


    val comment = Comment(commentText, getCurrentTime(), 0) //+++이거 마지막 0 늘어나게 수정

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("User Name") }, //사용자 이름으로
        text = {
            TextField(
                value = commentText,
                onValueChange = { commentText = it },
                placeholder = { Text("댓글을 입력하세요.") }
            )
        },
        confirmButton = {
            Button(onClick = {
                onDismiss()
                viewModel.InsertComment(comment)
            }) {
                Text("작성")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}