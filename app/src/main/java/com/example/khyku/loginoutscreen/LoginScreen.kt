package com.example.khyku.loginoutscreen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import com.example.khyku.nav.Routes
import com.example.khyku.yh.userViewmodel.UserProfileViewModel

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavController, viewModel: UserProfileViewModel) {
    // return UserProfile?
    val KonkukGreen = Color(0xFF036B3F)

    var id by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }
    var alertmsg by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .background(KonkukGreen)
            .fillMaxSize(),
    ){
        Text(text = "건하예KU", color = Color.White,
            modifier = Modifier.padding(start=95.dp, top=200.dp, bottom=30.dp),
            fontSize = 50.sp, fontWeight = FontWeight.Bold, )
        TextField(
            value = id,
            onValueChange = {id = it},
            placeholder = { Text("아이디", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, top=50.dp, bottom=30.dp)
        )
        TextField(
            value = password,
            onValueChange = {password=it},
            placeholder = { Text("비밀번호", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        Button(
            onClick = {
                viewModel.checkUserId(id.toLong()) { idExists ->
                    if (!idExists) { //아이디 없
                        alertmsg = "존재하지 않는 아이디입니다."
                        showAlert = true
                    } else { //아이디 있 비번 없
                        viewModel.checkUserPassword(id.toLong(), password) { passwordMatches ->
                            if (!passwordMatches) {
                                alertmsg = "비밀번호가 일치하지 않습니다."
                                showAlert = true
                            } else { //모두 일치함
                                Handler(Looper.getMainLooper()).postDelayed({
                                    navController.navigate(Routes.Home.route)
                                }, 2000)
                                //return viewModel.getUserById
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            modifier = Modifier.padding(start=170.dp, bottom=30.dp)
        ) {
            Text(text = "로그인", color = Color.Black)
        }
        Button(onClick = { navController.navigate(Routes.Register.route)},
            colors = ButtonDefaults.buttonColors(containerColor = KonkukGreen)) {
            Text(text = "회원 가입", color = Color.White,
                modifier = Modifier.padding(start=160.dp, bottom=30.dp))
        }
        if(showAlert){
            AlertDialog(
                onDismissRequest = { showAlert=false },
                title = { Text(text = alertmsg, color = Color.Black, fontWeight = FontWeight.Bold)},
                confirmButton = { Button(onClick = { showAlert=false },
                    colors = ButtonDefaults.buttonColors(containerColor = KonkukGreen)) {
                    Text("취소", color = Color.White)
                } },
                containerColor = Color.White
            )
        }
    }
}

