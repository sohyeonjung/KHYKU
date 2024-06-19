// UserDao
package com.example.khyku.yh.userDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Insert
    suspend fun insertUserProfile(userProfile: UserProfile)

    @Update
    suspend fun updateUserProfile(userProfile: UserProfile)

    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profiles")
    fun getAllUserProfiles(): Flow<List<UserProfile>>

    @Query("SELECT * FROM user_profiles WHERE userStudentId = :userStudentId LIMIT 1")
    fun getUserProfileById(userStudentId: Long): Flow<UserProfile?>
}