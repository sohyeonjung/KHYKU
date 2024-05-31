package com.example.khyku.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert
    suspend fun InsertComment(comment: Comment)

//    @Update
//    suspend fun UpdateComment(comment: Comment)
//    @Delete
//    suspend fun DeleteComment(comment: Comment)

    @Query("SELECT * FROM comments")
    fun getAllItems(): Flow<List<Comment>>



}