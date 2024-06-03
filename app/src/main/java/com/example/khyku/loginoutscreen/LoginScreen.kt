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
import androidx.navigation.NavController
import com.example.khyku.nav.Routes

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavController) {

    var id by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    Column {
        Text(text = "건하예KU")
        TextField(value = id, onValueChange = {id=it} )
        TextField(value = password, onValueChange = {password=it} )
        Button(onClick = {
            if(true){ //아이디 없
                showAlert=true
            }
            else if(true){ //비번 없
                showAlert=true
            }
            else if(true){ //아이디 비번 일치 x
                showAlert=true
            }
            else{ //모두 일치함
                //TODO(navigate to main screen)
            }

        }) {
            Text(text = "로그인")
        }
        Button(onClick = { navController.navigate(Routes.Register.route)}) {
            Text(text = "회원 가입")
        }
        if(showAlert){
            AlertDialog(
                onDismissRequest = { showAlert=false },
                title = { Text(text = "가입 실패")},
                confirmButton = { OutlinedButton(onClick = { showAlert=false }) {
                    Text("취소", color = Color.Black)
                } }
            )
        }
    }

}

