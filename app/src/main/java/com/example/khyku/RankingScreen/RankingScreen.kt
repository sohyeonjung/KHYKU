package com.example.khyku.yh.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.khyku.R
import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userViewmodel.UserProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun RankingScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: UserProfileViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var sortedUserProfiles by remember { mutableStateOf<List<UserProfile>>(emptyList()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            sortedUserProfiles = viewModel.getSortedUserProfilesByStudyTime()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.konkukgreen))) {
            Text(
                text = "Ranking",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
        sortedUserProfiles.forEach { user ->
            Box(modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.white))) {
                Text(
                    text = user.userName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    text = user.todayStudyTime.formatTime(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(start = 100.dp, top = 17.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

