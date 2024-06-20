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
//import com.google.android.gms.maps.model.LatLng

data class Facility(
    val location:String,
    val charge:Boolean,
    val cafe:Boolean,
    val cafeloction:String,
    val room:Boolean,
    val seatImg:Int,
    val buildingImg: Int,
    //val buildingLatLng:LatLng,
    //val cafeLatlng : LatLng
)

object FacilityItems{
    val Facilities = listOf(
        Facility(
            "건축관 1층 K-Hub",
            true,
            true,
            "부동산학관 1층 아이엔지",
            true,
            R.drawable.lazyupdate,
            R.drawable.barchi,
            //LatLng(37.543483,127.078543),
            //LatLng(37.543300,127.078282)
        ),
        Facility(
            "경영관 1층 K-Hub",
            true,
            true,
            "경영관 1층 레스티오",
            true,
            R.drawable.img,
            R.drawable.becon,
            //LatLng(37.544261,127.076071),
            //LatLng(37.544261,127.076071)
        ),
        Facility(
            "공학관 1층 K-Cube",
            true,
            true,
            "공학관 1층 레스티오",
            true,
            R.drawable.img,
            R.drawable.beng,
            //LatLng(37.541635,127.078790),
            //LatLng(37.541635,127.078790)
        ),
        Facility(
            "과학관 1층 K-Hub",
            true,
            true,
            "공학관 1층 레스티오",
            true,
            R.drawable.lazyupdate,
            R.drawable.bsci,
            //LatLng(37.541484,127.080432),
            //LatLng(37.541635,127.078790)
        ),
        Facility(
            "동물생명과학관 1층 K-Cube",
            true,
            true,
            "동물생명과학관 1층 레스티오",
            true,
            R.drawable.lazyupdate,
            R.drawable.banibiosci,
            //LatLng(37.540366,127.074361),
            //LatLng(37.540366,127.074361)
        ),

        Facility(
            "레이크홀 1층 이마트24",
            true,
            true,
            "레이크홀 1층 이마트24 내 폴바셋",
            true,
            R.drawable.lazyupdate,
            R.drawable.lazyupdate,
            //LatLng(37.539317,127.077329),
            //LatLng(37.539317,127.077329)
        ),
        Facility(
            "상허기념도서관 6층 K-Cube",
            true,
            true,
            "도서관 레스티오",
            true,
            R.drawable.lazyupdate,
            R.drawable.blib,
            //LatLng(37.541922,127.073740),
            //LatLng(37.541922,127.073740)
        ),
        Facility(
            "상허연구관 1층",
            true,
            true,
            "상허연구관 1층 블루포트",
            true,
            R.drawable.lazyupdate,
            R.drawable.bsh,
            //LatLng(37.544168,127.075353),
            //LatLng(37.544168,127.075353)
        ),
        Facility(
            "상허연구관 3층 K-Cube",
            true,
            true,
            "상허연구관 1층 블루포트",
            true,
            R.drawable.lazyupdate,
            R.drawable.bsh,
            //LatLng(37.544168,127.075353),
            //LatLng(37.544168,127.075353)
        ),
        Facility(
            "생명과학관 K-Cube",
            true,
            true,
            "동물생명과학관 1층 레스티오",
            true,
            R.drawable.lazyupdate,
            R.drawable.lazyupdate,
            //LatLng(37.540366,127.074361),
            //LatLng(37.540366,127.074361)
        ),
        Facility(
            "학생회관 1층 Career Lounge",
            true,
            true,
            "학생회관 1층 1847, 메가커피",
            true,
            R.drawable.img,
            R.drawable.bstuhall,
            //LatLng(37.541877,127.078208),
            //LatLng(37.541877,127.078208)
        ),
        Facility(
            "학생회관 2층 고전 음악 감상실",
            true,
            false,
            "음료반입불가",
            true,
            R.drawable.lazyupdate,
            R.drawable.bstuhall,
            //LatLng(37.541877,127.078208),
            //LatLng(37.541877,127.078208)
        ),
        Facility(
            "학생회관 2층 취창업 전략처",
            true,
            true,
            "학생회관 1층 1847, 메가커피",
            true,
            R.drawable.img,
            R.drawable.bstuhall,
            //LatLng(37.541877,127.078208),
            //LatLng(37.541877,127.078208)
        ),
    )
}


