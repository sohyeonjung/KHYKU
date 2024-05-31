package com.example.khyku.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.roomDB.CommentDatabase
import com.example.khyku.roomDB.Post
import com.example.khyku.viewmodel.CommentRepository
import com.example.khyku.viewmodel.CommentViewModel
import com.example.khyku.viewmodel.CommentViewModelFactory


@Composable
fun PostDetailScreen(post: Post) {

    val context = LocalContext.current
    val commentdb = CommentDatabase.getCommentDatabase(context)
    val viewModel: CommentViewModel =
        viewModel(factory = CommentViewModelFactory(CommentRepository(commentdb)))

    val commentlist by viewModel.commentList.collectAsState(initial = emptyList())

    var showCommentDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.getAllItems()
    }

    Column {
        Text(text="스터디원 구하기")
        //사람 정보
        Text(text = post.postTitle)
        Text(text=post.postType)
        Text(text = post.postContents)

        if(commentlist.isEmpty()) Text(text = "댓글 없음")
        else CommentList(list = commentlist)

        if (showCommentDialog) {
            CommentInputDialog(
                onDismiss = { showCommentDialog = false }, viewModel
            )
        }

        FloatingActionButton(onClick = { showCommentDialog = true }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }

}