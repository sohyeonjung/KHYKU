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
    lateinit var subjects:List<Subject> // home에서 subject추가 -> update
    lateinit var mainSubjects: List<Subject>
}

//    fun updateSubjectStudyTime(now: LocalTime, subject:String) {
//        subjects[subject]?.let {
//            subjects[subject] = it.plus(Duration.between(studyStartTime!!, now))
//        } ?: run {
//            subjects[subject] = Duration.between(studyStartTime!!, now)
//        }
//    }
//    fun updateStatusMessage(newMessage: String) {
//        statusMessage = newMessage
//    }
//    var mainSubject: String = ""
//        get() = field
//        set(value) {
//            if (subjects.size < 3 || subjects.containsKey(value)) {
//                field = value
//                // new main subject 초기화
//                subjects.putIfAbsent(value, Duration.ZERO)
//            } else {
//                throw IllegalStateException("3 main subjects")
//            }
//        }
//    val mainSubjectStudyTotal: Duration
//        get() = subjects[mainSubject] ?: Duration.ZERO
