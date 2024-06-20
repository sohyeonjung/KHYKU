package com.example.khyku.RankingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.khyku.R

@Composable
fun RankingScreen(modifier: Modifier = Modifier) {
    Column {
        Box(modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.konkukgreen))){
            Text(text = "Ranking", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 23.sp, modifier = Modifier.padding(15.dp))
        }
    }
}