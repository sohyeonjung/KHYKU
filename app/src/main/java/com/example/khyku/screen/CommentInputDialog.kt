package com.example.khyku.screen

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.khyku.roomDB.Comment
import com.example.khyku.viewmodel.CommentViewModel

@Composable
fun CommentInputDialog(
    onDismiss: () -> Unit,
    viewModel: CommentViewModel
) {
    var commentText by remember { mutableStateOf(" ") }

    val KonkukGreen = Color(0xFF036B3F)


    val comment = Comment(commentText, getCurrentTime())

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("User Name", color = Color.Black) }, //사용자 이름으로
        modifier = Modifier.background(color = Color.White),
        text = {
            TextField(
                value = commentText,
                onValueChange = { commentText = it },
                placeholder = { Text("댓글을 입력하세요.", color = Color.Black) }
            )
        },
        confirmButton = {
            Button(onClick = {
                    onDismiss()
                    viewModel.InsertComment(comment)},
                colors = ButtonDefaults.buttonColors(containerColor = KonkukGreen)
                ) {
                Text("작성", color = Color.White)
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("취소", color = Color.Black)
            }
        }
    )
}