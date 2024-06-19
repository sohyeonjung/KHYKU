//package com.example.khyku.facilityScreen
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Build
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.outlined.AccountCircle
//import androidx.compose.material.icons.outlined.Build
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.List
//import androidx.compose.material.icons.outlined.Star
//import com.example.khyku.BarItem
//
//data class Facility(
//    val location:String,
//    val charge:Boolean,
//    val cafe:Boolean,
//    val cafeloction:String,
//    val room:Boolean,
//    val seatImg:Int
//)
//
//object FacilityItems{
//    val Facilities = listOf(
//        Facility("공학관 1층 K-Cube", true, true, "공학관 1층 레스티오", true),
//        Facility("경영관 1층 K-Hub", true, true, "경영관 1층 레스티오", true),
//        Facility("학생회관 2층 취창업 전략처", true, true, "학생회관 1층 1847, 메가커피", true),
//        Facility("학생회관 1층 Career Lounge", true, true, "학생회관 1층 1847, 메가커피", true),
//        Facility("학생회관 2층 고전 음악 감상실", true, false, "음료반입불가", true),
//        Facility("생명과학관 K-Cube", true, true, "동물생명과학관 1층 레스티오", true),
//        Facility("상허연구관 1층", true, true, "상허연구관 1층 블루포트", true),
//        Facility("상허연구관 3층 K-Cube", true, true, "상허연구관 1층 블루포트", true),
//        Facility("상허기념도서관 6층 K-Cube", true, true, "도서관 레스티오", true),
//        Facility("동물생명과학관 1층 K-Cube", true, true, "동물생명과학관 1층 레스티오", true),
//        Facility("건축관 1층 K-Hub", true, true, "부동산학관 1층 아이엔지", true),
//        Facility("과학관 1층 K-Hub", true, true, "공학관 1층 레스티오", true),
//        Facility("레이크홀 1층 이마트24", true, true, "공학관 1층 레스티오", true),
//    )
//}
//
//
package com.example.khyku.facilityScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import com.example.khyku.BarItem
import com.example.khyku.R

data class Facility(
    val location:String,
    val charge:Boolean,
    val cafe:Boolean,
    val cafeloction:String,
    val room:Boolean,
    val seatImg:Int
)

object FacilityItems{
    val Facilities = listOf(
        Facility("공학관 1층 K-Cube", true, true, "공학관 1층 레스티오", true, R.drawable.img),
        Facility("경영관 1층 K-Hub", true, true, "경영관 1층 레스티오", true, R.drawable.img),
        Facility("학생회관 2층 취창업 전략처", true, true, "학생회관 1층 1847, 메가커피", true, R.drawable.img),
        Facility("학생회관 1층 Career Lounge", true, true, "학생회관 1층 1847, 메가커피", true, R.drawable.img),
        Facility("학생회관 2층 고전 음악 감상실", true, false, "음료반입불가", true, R.drawable.lazyupdate),
        Facility("생명과학관 K-Cube", true, true, "동물생명과학관 1층 레스티오", true, R.drawable.lazyupdate),
        Facility("상허연구관 1층", true, true, "상허연구관 1층 블루포트", true, R.drawable.lazyupdate),
        Facility("상허연구관 3층 K-Cube", true, true, "상허연구관 1층 블루포트", true, R.drawable.lazyupdate),
        Facility("상허기념도서관 6층 K-Cube", true, true, "도서관 레스티오", true, R.drawable.lazyupdate),
        Facility("동물생명과학관 1층 K-Cube", true, true, "동물생명과학관 1층 레스티오", true, R.drawable.lazyupdate),
        Facility("건축관 1층 K-Hub", true, true, "부동산학관 1층 아이엔지", true, R.drawable.lazyupdate),
        Facility("과학관 1층 K-Hub", true, true, "공학관 1층 레스티오", true, R.drawable.lazyupdate),
        Facility("레이크홀 1층 이마트24", true, true, "공학관 1층 레스티오", true, R.drawable.lazyupdate),
    )
}


