// User
//package com.example.khyku.yh.userDB
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import java.time.Duration
//import java.time.LocalTime
//
//// simple Date Format
//@Entity(tableName = "user_profiles")
//data class UserProfile (
//    // id -> Long
//    @PrimaryKey(autoGenerate = false)
//    val userStudentId: Long,
//    var userName: String,
//    var userPassword: String,
//    var userMajor:String
//) {
//    var passWd: String? = null
//    var todayStudyTime: Duration = Duration.ZERO
//    var maxFocusTime: Duration = Duration.ZERO
//    var studyStartTime: LocalTime? = null
//    var lastStudyTime: LocalTime? = null
//    var studyEndTime: LocalTime? = null
//    var statusMessage: String? = null
//    // home에서 subject추가 -> update
//    var subjects:List<Subject> = mutableListOf() // => isUserMain
//}

package com.example.khyku.yh.userDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalTime

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey(autoGenerate = false)
    val userStudentId: Long,
    var userName: String,
    var userPassword: String,
    var userMajor: String
) {
    //todayStudyTime  maxFocusTime  studyStartTime  lastStudyTime  studyEndTime
    var passWd: String? = null
    var todayStudyTime:Long = 0L
    var maxFocusTime: Long = 0L
    var studyStartTime: Long = 0L
    var lastStudyTime: Long = 0L
    var studyEndTime: Long = 0L
    var statusMessage: Long = 0L
    var subjects: List<Subject> = mutableListOf()

    fun Duration.toHoursMinutesSeconds(): Long {
        return this.seconds
    }

    fun LocalTime.toSecondsOfDay(): Long {
        return this.toSecondOfDay().toLong()
    }
}


