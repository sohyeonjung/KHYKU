package com.example.khyku.loginoutscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.khyku.userDB.UserProfileDatabase
import com.example.khyku.userViewmodel.UserProfileViewModel
import com.example.khyku.userViewmodel.UserRepository
import com.example.khyku.userViewmodel.UserViewModelFactory

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current
    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
    val userviewModel:UserProfileViewModel =
        viewModel(factory = UserViewModelFactory(UserRepository(userdb)))

    var studentId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkpassword by remember { mutableStateOf("") }

    var showalert by remember { mutableStateOf(false) }

    Column {
        Text(text = "회원가입")
        TextField(value = studentId, onValueChange = {studentId=it} )
        TextField(value = name, onValueChange = {name=it})
        TextField(value = major, onValueChange = {major=it} )
        TextField(value = password, onValueChange = {password=it} )
        TextField(value = checkpassword , onValueChange = {checkpassword=it} )
        Button(onClick = {
            if(true){ //학번 중복

                showalert = true
            }
            else if(true){ //비밀번호 일치 안 함

                showalert = true
            }
            else{ //모두 일치함
                //TODO(navigate to main screen)
            }

            

        }) {
            Text(text = "회원가입")
        }
        if(showalert){
            AlertDialog(
                onDismissRequest = { showalert=false },
                title = { Text(text = "로그인 실패")},
                confirmButton = { OutlinedButton(onClick = { showalert=false }) {
                    Text("취소", color = Color.Black)
                } }
            )
        }

    }
}