// UserProfileViewModel
package com.example.khyku.yh.userViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.yh.userDB.Subject
import com.example.khyku.yh.userDB.UserProfile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime

// 과목, 과목별 총 공부량, main subject로 선정된 여부
// update를 쳐하면 모든게 null로 쳐바뀜 => update UI 완성 후에 쪼개서 추가
class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class UserProfileViewModel(private val repository: Repository) : ViewModel() {
    private var _userList = MutableStateFlow<List<UserProfile>>(emptyList())
    val userList = _userList.asStateFlow()

    fun InsertUserProfile(user: UserProfile?) {
        if (user != null) {
            viewModelScope.launch {
                try {
                    repository.InsertUser(user)
                    getAllUserProfile()
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                }
            }
        }
    }

    fun UpdateUserProfile(user: UserProfile?) {
        if (user != null) {
            viewModelScope.launch {
                try {
                    repository.UpdateUser(user)
                    getAllUserProfile()
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                }
            }
        }
    }

    fun DeleteUserProfile(user: UserProfile?) {
        if (user != null) {
            viewModelScope.launch {
                try {
                    repository.DeleteUser(user)
                    getAllUserProfile()
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                }
            }
        }
    }

    fun getAllUserProfile() {
        viewModelScope.launch {
            try {
                repository.getAllUsers().collect {
                    _userList.value = it
                }
            } catch (e: Exception) {
                // 예외 처리
                e.printStackTrace()
            }
        }
    }

    fun startStudySession(user: UserProfile?, startTime: LocalTime) {
        if (user != null ) {
            viewModelScope.launch {
                try {
                    if(user.studyStartTime == null) {
                        user.studyStartTime = startTime
                    }
                    user.lastStudyTime = startTime
                    repository.UpdateUser(user)
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                }
            }
        }
    }

    fun endStudySession(user: UserProfile?, endTime: LocalTime) {
        if (user?.studyStartTime != null) {
            viewModelScope.launch {
                try {
                    user.studyEndTime = endTime
                    val sessionDuration = Duration.between(user.lastStudyTime, endTime)
                    if (sessionDuration > user.maxFocusTime) {
                        user.maxFocusTime = sessionDuration
                    }
                    repository.UpdateUser(user)
                } catch (e: Exception) {
                    // 예외 처리
                    e.printStackTrace()
                }
            }
        }
    }
    // subjectName => Subject 객체로 전환 필요
    fun updateSubjectStudyTime(user: UserProfile?, subjectName: String) {
        user?.let {
            val existingSubject = it.subjects.find { it.name == subjectName }
            val addSubjectDuration = Duration.between(user.lastStudyTime, user.studyEndTime)
            if (existingSubject != null) {
                existingSubject.time = existingSubject.time.plus(addSubjectDuration)
            } else {
                val newSubject = Subject(subjectName,"a",addSubjectDuration,true)
                it.subjects += newSubject
            }
        }
    }

}

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
