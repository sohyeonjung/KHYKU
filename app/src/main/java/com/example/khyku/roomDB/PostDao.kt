package com.example.khyku.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert
    suspend fun InsertPost(post:Post)
    @Update
    suspend fun UpdatePost(post:Post)
    @Delete
    suspend fun DeletePost(post:Post)

    @Query("SELECT * FROM Posts")
    fun getAllItems(): Flow<List<Post>>


}