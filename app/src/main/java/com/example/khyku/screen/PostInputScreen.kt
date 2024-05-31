package com.example.khyku.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.khyku.nav.Routes
import com.example.khyku.roomDB.Post
import com.example.khyku.viewmodel.PostViewModel


@Composable
fun PostInputScreen(viewModel: PostViewModel, navController: NavHostController) {

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

    val KonkukGreen = Color(0xFF036B3F)

    val post = Post(postTitle, postContent, postType, postDone, getCurrentTime())

    fun clearText(){
        postTitle = ""
        postContent = ""
        postType = ""
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = KonkukGreen)
    )
    {
        Text("스터디 글쓰기", color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier.padding(start=85.dp, top = 20.dp, bottom = 20.dp)
            )
        Row {
            TextField(
                value = postType,
                onValueChange = {postType = it},
                placeholder = { Text(text="분야", fontSize = 20.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                modifier = Modifier.height(72.dp)
            )
            Spacer(modifier = Modifier.padding(start=20.dp))
            Column(modifier = Modifier
                .background(color = Color.White)
            ) {
                Text(text = "모집중/모집완료",
                    color = Color.Black,
                    modifier = Modifier.padding(start=10.dp),
                    fontSize = 14.sp
                )
                Switch(checked = postDone,
                    onCheckedChange = {postDone=it},
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color.DarkGray,
                        uncheckedThumbColor = Color.DarkGray,
                        uncheckedTrackColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                )
                
            }
            
        }
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value = postTitle,
            onValueChange = {postTitle = it},
            placeholder = { Text("제목", fontSize = 20.sp)},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .height(72.dp)
                .fillMaxSize()
        )
        Spacer(modifier=Modifier.height(16.dp))
        TextField(
            value = postContent,
            onValueChange = {postContent = it},
            placeholder = { Text("내용을 입력하세요 (시간, 장소, 진행 방식 등)")},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .height(530.dp)
                .fillMaxSize()

        )
        Button(onClick = { viewModel.InsertPost(post)
                         navController.navigate(Routes.Community.route)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
            ) {
            Text("글쓰기", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

    }

}

