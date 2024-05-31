package com.example.khyku.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.roomDB.CommentDatabase
import com.example.khyku.roomDB.Post
import com.example.khyku.viewmodel.CommentRepository
import com.example.khyku.viewmodel.CommentViewModel
import com.example.khyku.viewmodel.CommentViewModelFactory


@ExperimentalMaterial3Api
@Composable
fun PostDetailScreen(post: Post) {

    val KonkukGreen = Color(0xFF036B3F)

    val state = rememberScrollState()


    val context = LocalContext.current
    val commentdb = CommentDatabase.getCommentDatabase(context)
    val viewModel: CommentViewModel =
        viewModel(factory = CommentViewModelFactory(CommentRepository(commentdb)))

    val commentlist by viewModel.commentList.collectAsState(initial = emptyList())

    var showCommentDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.getAllItems()
    }

    Column (
        modifier = Modifier
            .background(color = KonkukGreen)
            .fillMaxSize()
    ) {
        Text(
            text="스터디원 구하기",
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 15.dp, start = 10.dp),
            color = Color.White,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold
        )
        Column (modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            //.height(200.dp)
        ){
            Text(
                text="사람이름",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(text = post.postTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text=post.postType,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(text = post.postContents,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, bottom = 10.dp),
                color = Color.Black,
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.background(color = KonkukGreen).padding(top=20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {

            Column (
                modifier = Modifier
                    .background(color = Color.LightGray)
            ){
                if(commentlist.isEmpty()) Text(text = "댓글 없음")
                else CommentList(list = commentlist)

                if (showCommentDialog) {
                    CommentInputDialog(
                        onDismiss = { showCommentDialog = false }, viewModel
                    )
                }
            }
             FloatingActionButton(onClick = { showCommentDialog = true },
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
                    .align(Alignment.BottomEnd),
                containerColor = Color.LightGray)
            {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }


        }
    }


}