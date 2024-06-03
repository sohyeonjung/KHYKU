// Profile UI
package com.example.khyku.yh.ProfileScreen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import com.example.khyku.yh.userDB.UserProfile

@Composable
fun UserList(list: List<UserProfile>, onClick: (UserProfile) -> Unit) {
    LazyColumn {
        items(list) { user ->
            ProfileUI(user, onClick)
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
fun ProfileUI(user: UserProfile, onClick: (user:UserProfile) -> Unit) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .clickable { onClick(user) }){ //onclick 함수를 실행함
        Text(user.userStudentId.toString(), fontSize = 15.sp)
        Text(user.userName, fontSize = 15.sp)
        Text(user.maxFocusTime.toString(), fontSize = 15.sp)
        Text(user.studyStartTime.toString(), fontSize = 15.sp)
        Text(user.studyEndTime.toString(), fontSize = 15.sp)
        if (user.subjects.isNotEmpty()) {
            Text("Subjects:", fontSize = 15.sp)
            user.subjects.forEach { subject ->
                Text(subject.name, fontSize = 15.sp)
            }
        } else {
            Text("No subjects", fontSize = 15.sp)
        }
    }
}