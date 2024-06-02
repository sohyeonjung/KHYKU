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
    suspend fun InsertPost(post: Post)

    @Update
    suspend fun UpdatePost(post: Post)

    @Delete
    suspend fun DeletePost(post: Post)

    @Query("SELECT * FROM posts")
    fun getAllItems(): Flow<List<Post>>

    @Query("SELECT * FROM posts WHERE postTitle like :postName") //포함된다면
    fun getItems(postName: String): Flow<List<Post>>



}