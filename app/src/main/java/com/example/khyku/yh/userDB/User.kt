// User
package com.example.khyku.yh.userDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalTime

// simple Date Format
@Entity(tableName = "user_profiles")
data class UserProfile (
    @PrimaryKey(autoGenerate = false) val id: String = "user0",
    var userName: String
) {
    var passWd: String? = null
    var todayStudyTime: Duration = Duration.ZERO
    var maxFocusTime: Duration = Duration.ZERO
    var studyStartTime: LocalTime? = null
    var lastStudyTime: LocalTime? = null
    var studyEndTime: LocalTime? = null
    var statusMessage: String? = null
    // home에서 subject추가 -> update
    var subjects:List<Subject> = mutableListOf() // => isUserMain
    // lateinit var mainSubjects: List<Subject>
}

