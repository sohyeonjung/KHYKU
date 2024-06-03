// Repository
package com.example.khyku.yh.userViewmodel

import com.example.khyku.yh.userDB.UserProfile
import com.example.khyku.yh.userDB.UserProfileDatabase
import kotlinx.coroutines.flow.Flow


//UI와 repo를 나눠서 사용, viewmodel을 사용하여 viewmodel과 repository사이에 데이터를 구성받도록\
//내장 db말고 외장(클라우드) db 사용하려면 repository만 수정하면 됨
class UserRepository(private val db: UserProfileDatabase){
    val dao = db.getDao()

    suspend fun InsertUser(user: UserProfile){
        dao.insertUserProfile(user)
    }

    suspend fun UpdateUser(user: UserProfile){
        dao.updateUserProfile(user)
    }

    suspend fun DeleteUser(user: UserProfile){
        dao.deleteUserProfile(user)
    }
    fun getAllUsers() = dao.getAllUserProfiles()
    fun getUserById(userStudentId: Long): Flow<UserProfile?> = dao.getUserProfileById(userStudentId)
}
