// User
package com.example.khyku.userDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalTime

// simple Date Format
@Entity(tableName = "user_profiles")
data class UserProfile (
    @PrimaryKey(autoGenerate = false)
    var userStudentId: Long,
    var userName: String,
    var userPassword: String,
    var userMajor:String

) {
    var passWd: String? = null
    var todayStudyTime: Duration = Duration.ZERO
    var maxFocusTime: Duration = Duration.ZERO
    var studyStartTime: LocalTime? = null
    var studyEndTime: LocalTime? = null
    var statusMessage: String? = null
    var subjects: MutableMap<String, Duration> = mutableMapOf()
}

