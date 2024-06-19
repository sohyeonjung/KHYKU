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
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import com.example.khyku.yh.ProfileScreen.ProfileScreen

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(navController: NavController, viewModel: UserProfileViewModel) {

    val KonkukGreen = Color(0xFF036B3F)

//    val context = LocalContext.current
//    val userdb = UserProfileDatabase.getUserProfileDatabase(context)
//    val userviewModel: UserProfileViewModel =
//        viewModel(factory = UserViewModelFactory(UserRepository(userdb)))

    var studentId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkpassword by remember { mutableStateOf("") }

    var showalert by remember { mutableStateOf(false) }
    var alertmsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(KonkukGreen)
            .fillMaxSize(),
    ) {
        Text(text = "회원가입", modifier = Modifier.padding(start=115.dp, top=100.dp, bottom=30.dp),
            fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color.White)
        TextField(
            value = studentId, onValueChange = { studentId = it },
            placeholder = { Text("학번", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        TextField(
            value = name, onValueChange = { name = it },
            placeholder = { Text("이름", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        TextField(
            value = major, onValueChange = { major = it },
            placeholder = { Text("전공", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        TextField(
            value = password, onValueChange = { password = it },
            placeholder = { Text("비밀번호", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        TextField(
            value = checkpassword, onValueChange = { checkpassword = it },
            placeholder = { Text("비밀번호 확인", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier.padding(start=70.dp, bottom=30.dp)
        )
        Button(
            onClick = {
                viewModel.checkDuplicateUserId(studentId.toLong()) { idExists ->
                    if (idExists) { //학번 중복
                        showalert = true
                        alertmsg = "이미 존재하는 학번입니다."
                    } else if (password != checkpassword) { //비밀번호 일치 안 함
                        showalert = true
                        alertmsg = "비밀번호가 일치하지 않습니다."
                    } else { //모두 일치함
                        val newUser = UserProfile(studentId.toLong(), name, password, major)
                        // register => viewmodel insert
                        viewModel.registerUserProfile(newUser) { success ->
                            if (success) {
                                showalert = true
                                alertmsg = "회원가입에 성공했습니다."
                                //move to login screen
                                Handler(Looper.getMainLooper()).postDelayed({
                                    navController.navigate(Routes.Login.route)
                                }, 2000)
                            } else {
                                showalert = true
                                alertmsg = "회원가입에 실패했습니다."
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            modifier = Modifier.padding(start=165.dp, bottom=30.dp)
        ) {
            Text(text = "회원가입", color = Color.Black)
        }

        if(showalert){
            AlertDialog(
                onDismissRequest = { showalert=false },
                title = { Text(text = alertmsg, color = Color.Black, fontWeight = FontWeight.Bold)},
                confirmButton = { Button(onClick = { showalert=false },
                    colors = ButtonDefaults.buttonColors(containerColor = KonkukGreen)) {
                    Text("취소", color = Color.White)
                } },
                containerColor = Color.White
            )
        }


    }
}