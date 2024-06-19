package com.example.khyku.yh.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khyku.yh.userDB.Subject
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userViewmodel.UserProfileViewModel

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}
@Composable
fun ProfileScreen(viewModel: UserProfileViewModel, selectedUser: UserProfile? = null, subject: Subject) {
    val KonkukGreen = Color(0xFF036B3F)
    Column(
        modifier = Modifier
            .background(color = KonkukGreen).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedUser != null) {
            Text(
                text = selectedUser.userName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(top = 15.dp, start = 10.dp),
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .background(color = Color.White).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (selectedUser != null) {
                    Text(
                        text = selectedUser.todayStudyTime.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(top = 15.dp, start = 10.dp),
                        color = Color.Black,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //contentAlignment = Alignment.BottomEnd
                        ) {
                            Text(
                                text = "최대 집중",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (selectedUser != null) {
                                Text(
                                    text = selectedUser.maxFocusTime.toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .padding(top = 15.dp, start = 10.dp),
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //contentAlignment = Alignment.BottomEnd
                        ) {
                            //Text(text = subject.name)
                            Text(
                                text = "현재 과목",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (selectedUser != null) {
                                Text(
                                    // subject의 공부시간으로 변경
                                    text = selectedUser.maxFocusTime.toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .padding(top = 15.dp, start = 10.dp),
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "시작",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(top = 15.dp, start = 10.dp),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (selectedUser != null) {
                            Text(
                                text = selectedUser.studyStartTime.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "종료",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(top = 15.dp, start = 10.dp),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (selectedUser != null) {
                            Text(
                                text = selectedUser.studyEndTime.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (selectedUser != null) {
                    Text(
                        text = selectedUser.statusMessage.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(top = 15.dp, start = 10.dp),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Main Subjects",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(top = 15.dp, start = 10.dp),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            selectedUser?.subjects?.let { subjects ->
                Column(modifier = Modifier.padding(16.dp)) {
                    subjects.forEach { subject ->
                        Text(
                            text = subject.name,
                            modifier = Modifier.fillMaxWidth()
                                .height(70.dp)
                                .padding(top = 15.dp, start = 10.dp),
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = subject.time.toString(),
                            modifier = Modifier.fillMaxWidth()
                                .height(70.dp)
                                .padding(top = 15.dp, start = 10.dp),
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        }
    }
}



