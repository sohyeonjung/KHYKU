package com.example.khyku.screen

import java.text.SimpleDateFormat
import java.util.TimeZone

fun getCurrentTime(): String {
    //+++수정필요 - 한국시간으로 나오게 수정해야함
    //val currentTime : Long = System.currentTimeMillis() // ms로 반환
    val currentTime = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
    print(dateFormat.format(currentTime))
    return dateFormat.format(currentTime)
}