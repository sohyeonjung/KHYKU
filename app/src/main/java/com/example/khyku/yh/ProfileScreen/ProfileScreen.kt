package com.example.khyku.yh.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.yh.userDB.Subject
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userDB.UserProfileDatabase
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import com.example.khyku.yh.userViewmodel.UserProfileViewModelFactory
import com.example.khyku.yh.userViewmodel.UserRepository

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}
//@Composable
//fun UserProfileScreen(userName: String) {
//    val context = LocalContext.current
//    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
//    val viewModel: UserProfileViewModel =
//        viewModel(factory = UserProfileViewModelFactory(UserRepository(userdb)))
//
//    val userlist by viewModel.userList.collectAsState(initial = emptyList()) //~해서 자동으로 화면 recomposition
//    var selectedUser: UserProfile? by remember {
//        mutableStateOf<UserProfile?>(null)
//    }
//    val selectedEvent = { user: UserProfile -> selectedUser = user }
//    val subjectExample = selectedUser?.let { Subject("DB","pink", it.maxFocusTime,true) }
//    Column(modifier = Modifier.fillMaxSize()) {
//        InputScreen(viewModel = viewModel, selectedUser)
//        if (subjectExample != null) {
//            ProfileScreen(viewModel = viewModel, userName, subjectExample)
//        }
//        UserList(list = userlist, onClick = selectedEvent)
//    }
//}
//@Composable
//fun ProfileScreen(viewModel: UserProfileViewModel, userName:String , subject: Subject) {
//    val KonkukGreen = Color(0xFF036B3F)
//    val selectedUser = remember { mutableStateOf<UserProfile?>(null) }
//
//    LaunchedEffect(userName) {
//        selectedUser.value = viewModel.getUserByName(userName)
//    }
//    Column(
//        modifier = Modifier
//            .background(color = KonkukGreen)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        selectedUser.value?.let {
//            Text(
//                text = it.userName,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//                    .padding(top = 15.dp, start = 10.dp),
//                color = Color.White,
//                fontSize = 27.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center
//            )
//        }
//        Column(
//            modifier = Modifier
//                .background(color = Color.White)
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//            ) {
//                selectedUser.value?.todayStudyTime?.let {
//                    Text(
//                        text = it.formatTime(),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(70.dp)
//                            .padding(top = 15.dp, start = 10.dp),
//                        color = Color.Black,
//                        fontSize = 27.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//            Column {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Column(modifier = Modifier.weight(1f)) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            //contentAlignment = Alignment.BottomEnd
//                        ) {
//                            Text(
//                                text = "최대 집중",
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(70.dp)
//                                    .padding(top = 15.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                            selectedUser.value?.maxFocusTime?.let {
//                                Text(
//                                    text = it.formatTime(),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(70.dp)
//                                        .padding(top = 15.dp, start = 10.dp),
//                                    color = Color.Black,
//                                    fontSize = 18.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//                    }
//                    Column(modifier = Modifier.weight(1f)) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            //contentAlignment = Alignment.BottomEnd
//                        ) {
//                            //Text(text = subject.name)
//                            Text(
//                                text = subject.name,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(70.dp)
//                                    .padding(top = 15.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                // subject의 공부시간으로 변경
//                                text = subject.time.formatTime(),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(70.dp)
//                                    .padding(top = 15.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text(
//                            text = "시작",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(70.dp)
//                                .padding(top = 15.dp, start = 10.dp),
//                            color = Color.Black,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
//                        )
//                        selectedUser.value?.studyStartTime?.let {
//                            Text(
//                                text = it.formatTime(),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(70.dp)
//                                    .padding(top = 15.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text(
//                            text = "종료",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(70.dp)
//                                .padding(top = 15.dp, start = 10.dp),
//                            color = Color.Black,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
//                        )
//                        selectedUser.value?.studyEndTime?.let {
//                            Text(
//                                text = it.formatTime(),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(70.dp)
//                                    .padding(top = 15.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                }
//            }
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    "Main Subjects",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(70.dp)
//                        .padding(top = 15.dp, start = 10.dp),
//                    color = Color.Black,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//            selectedUser.value?.subjects?.let { subjects ->
//                Column(modifier = Modifier.padding(16.dp)) {
//                    subjects.forEach { subject ->
//                        Row {
//                            Text(
//                                text = subject.name,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(60.dp)
//                                    .padding(top = 10.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                            Text(
//                                text = subject.time.formatTime(),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(60.dp)
//                                    .padding(top = 10.dp, start = 10.dp),
//                                color = Color.Black,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun UserProfileScreen(userName: String) {
    val context = LocalContext.current
    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
    val viewModel: UserProfileViewModel =
        viewModel(factory = UserProfileViewModelFactory(UserRepository(userdb)))

    val userlist by viewModel.userList.collectAsState(initial = emptyList())
    var selectedUser by remember { mutableStateOf<UserProfile?>(null) }

    LaunchedEffect(userName) {
        selectedUser = viewModel.getUserByName(userName)
    }

    val subjectExample = selectedUser?.let { Subject("DB", "pink", it.maxFocusTime, true) }

    Column(modifier = Modifier.fillMaxSize()) {
        InputScreen(viewModel = viewModel, selectedUser)
        if (subjectExample != null) {
            ProfileScreen(viewModel = viewModel, selectedUser, subjectExample)
        }
        UserList(list = userlist, onClick = { user -> selectedUser = user })
    }
}

@Composable
fun ProfileScreen(viewModel: UserProfileViewModel, user: UserProfile?, subject: Subject) {
    val KonkukGreen = Color(0xFF036B3F)

    Column(
        modifier = Modifier
            .background(color = KonkukGreen)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let {
            Text(
                text = it.userName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(top = 15.dp, start = 10.dp),
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = it.todayStudyTime.formatTime(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(top = 15.dp, start = 10.dp),
                        color = Color.Black,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "최대 집중",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.maxFocusTime.formatTime(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = subject.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = subject.time.formatTime(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
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
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.studyStartTime.formatTime(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
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
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.studyEndTime.formatTime(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp, start = 10.dp),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
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
                it.subjects.let { subjects ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        subjects.forEach { subject ->
                            Row {
                                Text(
                                    text = subject.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp)
                                        .padding(top = 10.dp, start = 10.dp),
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = subject.time.formatTime(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp)
                                        .padding(top = 10.dp, start = 10.dp),
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}




