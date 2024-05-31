package com.example.khyku.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

//package com.example.khyku.screens
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowDropDown
//import androidx.compose.material3.Button
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.khyku.User.Subject
//import com.example.khyku.User.User
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//
////class UserViewModel: ViewModel(){
////    var user: MutableState<User>
////    var selectedSub:Subject
////    var timerActive: Boolean = false
////    init{
////        user = mutableStateOf<User>(User("dy", mutableListOf<Subject>(Subject("과목", "기본", 0)), 0))
////        selectedSub = user.value.subjects[0]
////    }
////
////
////    fun addSubject(sub:Subject){
////        var updateSubject = user.value.subjects + sub
////        //not use add
////        user.value = user.value.copy(subjects = updateSubject)
////    }
////}
//
@Composable
fun HomeScreen() {
    Text(text = "homeScreen")
}
//
//@Composable
//fun HomeScreenHaveTime() {
//    TODO("Not yet implemented")
//}
//
//@Composable
//fun HomeScreenActive() {
//    TODO("Not yet implemented")
//}
//
//@Composable
//fun HomeScreenInit(userViewModel: UserViewModel = viewModel()) {
//    Column {
//        DateField()
//        TimerField(userViewModel)
//        DropDownBoxField(userViewModel)
//        TimerButtonField(userViewModel)
//    }
//}
//
//@Composable
//fun DateField() {
//    //요일 출력
//    //https://thinking-face.tistory.com/59
//    val current = LocalDateTime.now()
//    val formatter = DateTimeFormatter.ofPattern("MM.dd(EE)")
//    val formatted = current.format(formatter)
//    Text(formatted)
//}
//
//@Composable
//fun TimerField(userViewModel: UserViewModel) {
//    var time = userViewModel.selectedSub.time
//    var hour:Int = time/(60*60*100)
//    var minute:Int = time/(60*100)
//    var second:Int = time/100
//
//    Text("00:00:00")
//}
//
//@Composable
//fun DropDownBoxField(userViewModel: UserViewModel) {
//    //https://mahendranv.github.io/posts/compose-dropdown/
//    //https://alexzh.com/jetpack-compose-dropdownmenu/
//    var expanded by remember { mutableStateOf(false) }
//    Box{
//        Row(modifier = Modifier.clickable {
//            expanded = !expanded
//        }) {
//            //https://velog.io/@baekhk1006/kotlin-null%EC%9D%84-%EB%8B%A4%EB%A3%A8%EB%8A%94-%EB%B0%A9%EB%B2%95-yzwb6i7u
//            var subName = userViewModel.selectedSub.name
//
//            Text(text = subName)
//            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }) {
//                userViewModel.user.value.subjects.forEach{
//                    subject ->  DropdownMenuItem(
//                    text = {
//                        val isSelected = subject.name == subName
//                        val style = if (isSelected) {
//                            Text(subject.name, fontWeight = FontWeight.Bold, color = Color.Blue)
//                        } else {
//                            Text(subject.name, fontWeight = FontWeight.Normal)
//                        }
//                    },
//                    onClick = {
//                        userViewModel.selectedSub = subject
//                        expanded = false
//                        subName = subject.name
//                    }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TimerButtonField(userViewModel: UserViewModel) {
//    Button(onClick = { userViewModel.timerActive = !userViewModel.timerActive }) {
//        if(!userViewModel.timerActive){
//            Text(text = "공부 시작")
//        }
//        else{
//            Text(text = "중지")
//        }
//    }
//}
//
//@Composable
//fun AddSubjectField() {
//    
//}