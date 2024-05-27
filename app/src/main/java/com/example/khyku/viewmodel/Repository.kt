package com.example.khyku.viewmodel

import com.example.khyku.roomDB.Post
import com.example.khyku.roomDB.PostDatabase

class Repository(private val db: PostDatabase) {
    val dao = db.getDao()

    suspend fun InsertPost(post: Post){
        dao.InsertPost(post)
    }

    suspend fun UpdatePost(post: Post){
        dao.UpdatePost(post)
    }

    suspend fun DeletePost(post: Post){
        dao.DeletePost(post)
    }

    fun getAllItems() = dao.getAllItems()
}