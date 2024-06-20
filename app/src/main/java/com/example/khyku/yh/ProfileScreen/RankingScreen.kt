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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khyku.R
import com.example.khyku.yh.userViewmodel.UserProfileViewModel

@Composable
fun RankingScreen(modifier: Modifier = Modifier, viewModel: UserProfileViewModel) {
    val sortedUserProfiles by viewModel.getSortedUserProfilesByStudyTime().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.konkukgreen))){
            Text(text = "Ranking", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 23.sp, modifier = Modifier.padding(15.dp))
        }
        sortedUserProfiles.forEach { user ->
            Text(text = "${user.userName}: ${user.todayStudyTime}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}