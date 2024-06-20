// UserProfileViewModel
package com.example.khyku.yh.userViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.yh.userDB.Subject
import com.example.khyku.yh.userDB.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserProfileViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class UserProfileViewModel(private val repository: UserRepository) : ViewModel() {
    private var _userList = MutableStateFlow<List<UserProfile>>(emptyList())
    val userList = _userList.asStateFlow()
//    fun getUserByName(userName: String?): Flow<UserProfile?>? {
//        return if (userName != null) {
//            repository.getUserByName(userName)
//        } else {
//            null
//        }
//    }

    suspend fun getUserByName(userName: String?): UserProfile? {
        return if (userName != null) {
            repository.getUserByName(userName).firstOrNull()
        } else {
            null
        }
    }
    suspend fun getSortedUserProfilesByStudyTime(): List<UserProfile> {
        getAllUserProfile()
        return userList.first().sortedByDescending { it.todayStudyTime }
    }
    suspend fun getUserById(userId: Long): UserProfile? {
        return if (userId != 0L) {
            repository.getUserById(userId).firstOrNull()
        } else {
            null
        }
    }
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

    fun startStudySession(user: UserProfile?, startTime: Long) {
        if (user != null ) {
            viewModelScope.launch {
                try {
                    if(user.studyStartTime == 0L) {
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
    // todayStudyTime  maxFocusTime  studyStartTime  lastStudyTime  studyEndTime
    fun endStudySession(user: UserProfile?, endTime: Long) {
        if (user != null && user.studyStartTime != 0L) {
            viewModelScope.launch {
                try {
                    user.studyEndTime = endTime
                    val session = endTime - user.lastStudyTime
                    if (session > user.maxFocusTime) {
                        user.maxFocusTime = session
                    }
                    user.todayStudyTime += session
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
            val addSubjectTime = user.studyEndTime - user.lastStudyTime
            if (existingSubject != null) {
                existingSubject.time += addSubjectTime
            } else {
                val newSubject = Subject(subjectName,"a",addSubjectTime,true)
                it.subjects += newSubject
            }
        }
    }

    // 로그인 - ID 일치
    fun checkUserId(userStudentId: Long, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserById(userStudentId).firstOrNull()
            callback(user != null)
        }
    }

    // 로그인 - 비밀번호 일치
    fun checkUserPassword(userStudentId: Long, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserById(userStudentId).firstOrNull()
            callback(user?.userPassword == password)
        }
    }

    // 회원가입 - ID 중복
    fun checkDuplicateUserId(userStudentId: Long, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserById(userStudentId).firstOrNull()
            callback(user != null)
        }
    }

    // 회원가입 - 사용자 프로필 삽입
    fun registerUserProfile(user: UserProfile, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.InsertUser(user)
                getAllUserProfile()
                callback(true)
            } catch (e: Exception) {
                e.printStackTrace()
                callback(false)
            }
        }
    }
}
