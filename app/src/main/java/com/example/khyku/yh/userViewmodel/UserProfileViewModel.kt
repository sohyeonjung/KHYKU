// UserProfileViewModel
package com.example.khyku.yh.userViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.khyku.yh.userDB.UserProfile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime

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
}
