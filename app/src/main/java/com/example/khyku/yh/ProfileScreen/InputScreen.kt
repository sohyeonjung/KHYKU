// ProfileScreen
package com.example.khyku.yh.ProfileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import java.time.LocalTime
fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}
@Composable
fun InputScreen(viewModel: UserProfileViewModel, selectedUser: UserProfile? = null) {
    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var subjectName by remember { mutableStateOf("") }

    LaunchedEffect(selectedUser) {
        selectedUser?.let {
            userId = it.id
            userName = it.userName
        }
    }

    fun clearText() {
        userId = ""
        userName = ""
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("UserID") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("UserName") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = subjectName,
            onValueChange = { subjectName = it },
            label = { Text("subjectName") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Button(onClick = {
                viewModel.InsertUserProfile(UserProfile(userId, userName))
                clearText()
            }) {
                Text("Insert")
            }
//            Button(onClick = {
//                viewModel.UpdateUserProfile(UserProfile(userId, userName))
//                clearText()
//            }) {
//                Text("Update")
//            }
            Button(onClick = {
                viewModel.DeleteUserProfile(UserProfile(userId, userName))
                clearText()
            }) {
                Text("Delete")
            }
//            Button(onClick = {
//                viewModel.getAllUserProfile()
//                clearText()
//            }) {
//                Text("Find")
//            }
        }
        Row {
            Button(onClick = {
                if (selectedUser != null) {
                    val localtime = LocalTime.now()
                    viewModel.startStudySession(selectedUser, localtime)
                }
            }) {
                Text("Start")
            }
            Button(onClick = {
                if (selectedUser != null) {
                    val localtime = LocalTime.now()
                    viewModel.endStudySession(selectedUser, localtime)
                    viewModel.updateSubjectStudyTime(selectedUser, subjectName)
                }
            }) {
                Text("End")
            }
        }
    }
}
