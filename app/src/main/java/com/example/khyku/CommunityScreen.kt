@file:Suppress("DEPRECATION")

package com.example.khyku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {

    var presses by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }
    val KonkukGreen = Color(0xFF036B3F)


    Scaffold(
        topBar = {
            LargeTopAppBar(
                colors = topAppBarColors(
                    containerColor = KonkukGreen,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = "스터디원 찾기",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    // 검색 TextField
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            //textColor = Color.Black,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        ),
                        placeholder = { Text("제목 검색") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            // 검색 동작 구현 (예: API 호출)
                        }),
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        },
                        modifier = Modifier.width(403.dp)

                    )
                },
            )
        },
        ///++수정
        bottomBar = {
            BottomAppBar(
                containerColor = KonkukGreen,
                contentColor = Color.Black
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "수정해야할부분",
                )
            }
        },
        //+++수정
        floatingActionButton = {
            FloatingActionButton(
                onClick = { presses++ },
                containerColor = KonkukGreen) {
                Icon(Icons.Default.Edit, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

//            Text(
//                modifier = Modifier.padding(8.dp),
//                text =
//                """
//                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
//
//                    It also contains some basic inner content, such as this text.
//
//                    You have pressed the floating action button $presses times.
//                """.trimIndent(),
//            )
        }
    }

}