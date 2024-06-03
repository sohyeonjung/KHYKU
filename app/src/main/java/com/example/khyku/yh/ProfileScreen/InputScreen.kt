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

@Composable
fun InputScreen(viewModel: UserProfileViewModel, selectedUser: UserProfile? = null) {
    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var subjectName by remember { mutableStateOf("") }

    LaunchedEffect(selectedUser) {
        selectedUser?.let {
            userId = it.userStudentId.toString()
            userName = it.userName
        }
    }

    fun clearText() {
        userId = ""
        userName = ""
        subjectName = ""
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
            label = { Text("SubjectName") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Button(onClick = {
                viewModel.InsertUserProfile(UserProfile(userId.toLong(), userName,"",""))
                clearText()
            }) {
                Text("Insert")
            }
            Button(onClick = {
                viewModel.DeleteUserProfile(UserProfile(userId.toLong(), userName,"",""))
                clearText()
            }) {
                Text("Delete")
            }
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
                    subjectName = "" // clear subject name after updating study time
                }
            }) {
                Text("End")
            }
        }
    }
}
