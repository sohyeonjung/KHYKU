package com.example.khyku.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khyku.R
import com.example.khyku.roomDB.Post

@Composable
fun PostList(list: List<Post>, onClick: (post:Post) -> Unit) {
    LazyColumn {
        items(list) { post ->
            PostUI(post, onClick)
            Divider(color = Color.Black, thickness = 1.dp,
                modifier = Modifier.padding(top=10.dp, bottom = 10.dp))
        }
    }
}

@Composable
fun PostUI(post: Post, onClick: (post:Post) -> Unit) {
    val KonkukGreen = Color(0xFF036B3F)

    Column(modifier = Modifier
        .padding(start = 10.dp)
        .fillMaxWidth()
        .clickable { onClick(post) }){
        Row {
            Box(modifier =Modifier.padding(bottom = 6.dp) )
            var donetext:String = " 모집중 "
            if(post.postDone==false) donetext="  모집 완료  "
//            Button(onClick = {
//                println("clicked")
//            },
//                colors = ButtonDefaults.buttonColors(containerColor = KonkukGreen)
//            ) { Text(text = donetext, color = Color.White)}
//            Spacer(modifier = Modifier.padding(start = 20.dp))
//            OutlinedButton(onClick = {}
//            ) { Text(text = post.postType, color = Color.Black)}
            Box(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.konkukgreen)))
            {
                Text(text = donetext, color = Color.White, modifier = Modifier.padding(3.dp))
            }
            Spacer(modifier = Modifier.padding(start = 13.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                //.background(colorResource(id = R.color.konkukgreen))
                .background(Color.White)
                .border(
                    1.dp,
                    colorResource(id = R.color.konkukgreen),
                    shape = RoundedCornerShape(16.dp)
                ))
            {
                Text(text = " "+ post.postType+" ",
                    //color = Color.White,
                    color = colorResource(id = R.color.konkukgreen),
                    modifier = Modifier.padding(3.dp))
            }
        }
        Text(text = post.postTitle, fontSize = 25.sp, modifier = Modifier.padding(top = 7.dp, start = 3.dp))
        Row {
            Text(post.userName)
            Spacer(modifier = Modifier.padding(start=10.dp))
            Text(text = post.postTime)
        }

    }
}