package com.example.khyku.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.khyku.R
import com.example.khyku.User.Subject
import com.example.khyku.User.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserViewModel: ViewModel(){
    var user: MutableState<User>
    var selectedSub:Subject
    var timerActive = mutableStateOf(false)
    init{
        user = mutableStateOf<User>(User("dy", mutableListOf<Subject>(Subject("과목", "#589288", 0, true)), 0))
        selectedSub = user.value.subjects[0]
    }
    fun addSubject(sub:Subject) {
        var updateSubject = user.value.subjects + sub
        //not use add
        user.value = user.value.copy(subjects = updateSubject)
    }

    fun changeTimerActive(){
        timerActive.value = !timerActive.value
    }
    /*************************************************************/
    private val _timer = MutableStateFlow(0L)
    /*************************************************************/
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value++
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun stopTimer() {
        selectedSub.time += _timer.value
        user.value.totalTime += _timer.value
        _timer.value = 0
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}


//class TimerViewModel : ViewModel() {
//    private val _timer = MutableStateFlow(0L)
//    val timer = _timer.asStateFlow()
//
//    private var timerJob: Job? = null
//
//    fun startTimer() {
//        timerJob?.cancel()
//        timerJob = viewModelScope.launch {
//            while (true) {
//                delay(1000)
//                _timer.value++
//            }
//        }
//    }
//
//    fun pauseTimer() {
//        timerJob?.cancel()
//    }
//
//    fun stopTimer() {
//        _timer.value = 0
//        timerJob?.cancel()
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        timerJob?.cancel()
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(userViewModel: UserViewModel = viewModel()) {
//    val sheetState = rememberModalBottomSheetState()
//    var showBottomSheet by remember { mutableStateOf(false) }
//    Scaffold (
//        floatingActionButton = {
//            FloatingActionButton(onClick = { showBottomSheet = false }) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
//            }
//        }
//    ){
//        Box(Modifier.padding(it)){
//            if(userViewModel.timerActive==false && userViewModel.user.value.totalTime == 0){
//                HomeScreenInit()
//            }
//            else if(userViewModel.timerActive==true){
//                HomeScreenActive()
//            }
//            else if(userViewModel.timerActive==false && userViewModel.user.value.totalTime>0){
//                HomeScreenHaveTime()
//            }
//            ModalBottomSheet(
//                onDismissRequest = { showBottomSheet=false },
//                sheetState = sheetState
//            ) {
//                Column {
//                    Row {
//                        Text(text ="취소", modifier = Modifier.clickable { showBottomSheet = false })
//                        Text(text = "과목 추가하기", fontWeight = FontWeight.Bold)
//                        Text(text = "완료", modifier = Modifier.clickable { showBottomSheet = false})
//                    }
//                    TextField(value = "", onValueChange = {})
//
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userViewModel: UserViewModel = viewModel()) {
    var showBottomSheet by remember { mutableStateOf(false) }

    if(!userViewModel.timerActive.value){
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = colorResource(id = R.color.konkukgreen),
                    onClick = { showBottomSheet = true }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            },
        ) {
            Box(Modifier.padding(it)) {
                when {
                    userViewModel.user.value.totalTime == 0L -> {
                        HomeScreenInit(userViewModel)
                    }
                    userViewModel.user.value.totalTime > 0 -> {
                        HomeScreenHaveTime(userViewModel)
                    }
                }
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                    ) {
                        SubjectAdder(userViewModel) {
                            showBottomSheet = false
                        }
                    }
                }
            }
        }
    }
    else{
        HomeScreenActive(userViewModel)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectAdder(userViewModel: UserViewModel, onClose: () -> Unit) {
    var subjectName by remember { mutableStateOf("") }
    var colorStrings = remember { mutableStateListOf("#589288", "#77aa64", "#9fbf69", "#d1da6c", "#f8ec77") }
    var subjectCate by remember { mutableStateOf(colorStrings[0]) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "취소", modifier = Modifier
                .weight(1f)
                .clickable { onClose() })
            Text(
                text = "과목 추가하기",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(text = "완료", modifier = Modifier.clickable {
                if(subjectName == ""){
                    //subjectNoInputAlert()
                }
                else{
                    userViewModel.addSubject(Subject(name = subjectName, cate = subjectCate, time = 0, false))
                    onClose()
                }
            })
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = subjectName,
            onValueChange = { subjectName = it },
            label = { Text(text = "과목 이름") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        var colorChartexpanded by remember { mutableStateOf(false) }
        Box{
            Row(modifier = Modifier.clickable {
                colorChartexpanded = !colorChartexpanded
            }) {
                var selectedColor = subjectCate
                Text(text = "색상 선택")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_circle_24),
                    tint = Color(android.graphics.Color.parseColor(subjectCate)),
                    contentDescription = ""
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(
                    expanded = colorChartexpanded,
                    onDismissRequest = { colorChartexpanded = false }) {
                    colorStrings.forEach{
                            color ->  DropdownMenuItem(
                        text = {
//                            val isSelected = color == subName
//                            val style = if (isSelected) {
//                                Text(subject.name, fontWeight = FontWeight.Bold, color = Color.Blue)
//                            } else {
//                                Text(subject.name, fontWeight = FontWeight.Normal)
//                            }
                        },
                        onClick = {
                            selectedColor = color
                            colorChartexpanded = false
                            subjectCate = color
                        },
                        leadingIcon =  {
                            val isSelected = color == selectedColor
                            if (isSelected){
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_check_circle_24),
                                    tint = Color(android.graphics.Color.parseColor(color)),
                                    contentDescription = ""
                                )
                            }
                            else{
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_circle_24),
                                    tint = Color(android.graphics.Color.parseColor(color)),
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(200.dp))
    }
}



@Composable
fun HomeScreenHaveTime(userViewModel: UserViewModel) {
    Column {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.konkukgreen)),
            Arrangement.Top,
            Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(24.dp))
            DateField()
            Spacer(modifier = Modifier.height(40.dp))
            //TimerField(userViewModel)
            //Spacer(modifier = Modifier.height(30.dp))
            DropDownBoxField(userViewModel)
            Spacer(modifier = Modifier.height(28.dp))
            //TimerButtonField(userViewModel)
            TimerScreenContent(userViewModel)
            Spacer(modifier = Modifier.height(40.dp))
        }
        LazyColumn (modifier = Modifier.background(Color.White)){
            items(userViewModel.user.value.subjects){ item ->
                if(item.time>0L) {
                    ItemUI(item)
                    Divider(color = colorResource(id = R.color.konkukgreen))
                }
            }
        }
    }

}

@Composable
fun ItemUI(item: Subject) {
    Row(modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.width(15.dp))
        Icon(
            painter = painterResource(id = R.drawable.baseline_circle_24),
            tint = Color(android.graphics.Color.parseColor(item.cate)),
            contentDescription = "",

        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(30.dp))
        Text(text = item.time.formatTime())
    }
}

@Composable
fun HomeScreenActive(userViewModel: UserViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.konkukgreen)), Arrangement.Top, Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        DateField()
        Spacer(modifier = Modifier.height(140.dp))
        //TimerField(userViewModel)
        Text(userViewModel.selectedSub.name, color = Color.White)
        Spacer(modifier = Modifier.height(28.dp))
        TimerScreenContent(userViewModel)
    }
}

@Composable
fun HomeScreenInit(userViewModel: UserViewModel) {
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Top, Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(24.dp))
        DateField()
        Spacer(modifier = Modifier.height(140.dp))
        //TimerField(userViewModel)
        //Spacer(modifier = Modifier.height(30.dp))
        DropDownBoxField(userViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        //TimerButtonField(userViewModel)
        TimerScreenContent(userViewModel)
    }
}

@Composable
fun DateField() {
    //요일 출력
    //https://thinking-face.tistory.com/59
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MM.dd(EE)")
    val formatted = current.format(formatter)
    Text(formatted, fontSize = 17.sp)
}

//@Composable
//fun TimerField(userViewModel: UserViewModel) {
//    var time = userViewModel.selectedSub.time
//    var hour:Int = time/(60*60*100)
//    var minute:Int = time/(60*100)
//    var second:Int = time/100
//
//    Text("00:00:00", fontSize = 50.sp, fontWeight = FontWeight.Bold)
//}

@Composable
fun DropDownBoxField(userViewModel: UserViewModel) {
    //https://mahendranv.github.io/posts/compose-dropdown/
    //https://alexzh.com/jetpack-compose-dropdownmenu/
    var expanded by remember { mutableStateOf(false) }
    Box{
        Row(modifier = Modifier.clickable {
            expanded = !expanded
        }) {
            //https://velog.io/@baekhk1006/kotlin-null%EC%9D%84-%EB%8B%A4%EB%A3%A8%EB%8A%94-%EB%B0%A9%EB%B2%95-yzwb6i7u
            var subName = userViewModel.selectedSub.name
            Box{
                Text(text = subName)
            }
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                userViewModel.user.value.subjects.forEach{
                    subject ->  DropdownMenuItem(
                    text = {
                        val isSelected = subject.name == subName
                        val style = if (isSelected) {
                            Text(subject.name, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor(subject.cate)))
                        } else {
                            Text(subject.name, fontWeight = FontWeight.Normal)
                        }
                    },
                    onClick = {
                        userViewModel.selectedSub = subject
                        expanded = false
                        subName = subject.name
                    },
                    modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

@Composable
fun TimerButtonField(userViewModel: UserViewModel) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.konkukgreen),
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White,
        ),
        onClick = { userViewModel.timerActive.value = !userViewModel.timerActive.value }
    ) {
        if(!userViewModel.timerActive.value){
            Text(text = "공부 시작")
        }
        else{
            Text(text = "중지")
        }
    }
}

//@Composable
//fun FloatingButton(onClick:()->Unit) {
//    FloatingActionButton(onClick = onClick) {
//        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
//    }
//}

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

@Composable
fun TimerScreenContent(userViewModel: UserViewModel) {
    val timerValue by userViewModel.timer.collectAsState()

    TimerScreen(
        timerValue = timerValue,
//        onStartClick = { timerViewModel.startTimer() },
//        onPauseClick = { timerViewModel.pauseTimer() },
//        onStopClick = { timerViewModel.stopTimer() }
        userViewModel = userViewModel
    )
}

@Composable
fun TimerScreen(
    timerValue: Long,
    userViewModel: UserViewModel
) {
    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(!userViewModel.timerActive.value && userViewModel.user.value.totalTime == 0L){
            Text(text = timerValue.formatTime(), fontSize = 24.sp)
        }
        else if(userViewModel.timerActive.value){
            Text(text = timerValue.formatTime(), fontSize = 24.sp, color = Color.White)
        }
        else if(userViewModel.user.value.totalTime >0){
            Text(userViewModel.user.value.totalTime.formatTime(), fontSize = 24.sp, color = Color.White)
        }
        if(userViewModel.timerActive.value){
            Text(text = userViewModel.selectedSub.name, color = Color.White)
            Text(text = (userViewModel.selectedSub.time + timerValue).formatTime(), fontSize = 24.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
//            Button(onClick = onStartClick) {
//                Text("Start")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Button(onClick = onPauseClick) {
//                Text("Pause")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Button(onClick = onStopClick) {
//                Text("Stop")
//            }

            var containerColor:Color
            var contentColor:Color
            var onClick: ()->Unit
            var buttonText: String

            if(!userViewModel.timerActive.value){
                onClick = {userViewModel.startTimer()}
                buttonText = "공부 시작"

                if(userViewModel.user.value.totalTime == 0L){
                    containerColor = colorResource(id = R.color.konkukgreen)
                    contentColor = Color.White
                }
                else{
                    containerColor = Color.White
                    contentColor = colorResource(id = R.color.konkukgreen)
                }
            }
            else{
                containerColor = Color.White
                contentColor = colorResource(id = R.color.konkukgreen)
                onClick = {userViewModel.stopTimer()}
                buttonText = "중지"
            }

//            if(!userViewModel.timerActive.value){
//                Button(
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = colorResource(id = R.color.KUGrenn),
//                        contentColor = Color.White,
//                        disabledContainerColor = Color.LightGray,
//                        disabledContentColor = Color.White,
//                    ),
//                    onClick = {
//                        userViewModel.timerActive.value = !userViewModel.timerActive.value
//                        userViewModel.startTimer()
//                    }
//                ) {
//                    Text(text = "공부 시작")
//                }
//            }
//            else{
//                Button(
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color.White,
//                        contentColor = colorResource(id = R.color.KUGrenn),
//                        disabledContainerColor = Color.LightGray,
//                        disabledContentColor = Color.White,
//                    ),
//                    onClick = {
//                        userViewModel.timerActive.value = !userViewModel.timerActive.value
//                        userViewModel.stopTimer()
//                    }
//                ) {
//                    Text(text = "중지")
//                }
//            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    contentColor = contentColor,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = containerColor,
                ),
                onClick = {
                    userViewModel.timerActive.value = !userViewModel.timerActive.value
                    onClick()
                }
            ) {
                Text(text = buttonText)
            }
        }
    }
}