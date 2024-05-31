package com.example.khyku.viewmodel

import com.example.khyku.roomDB.Post
import com.example.khyku.roomDB.PostDatabase

class PostRepository(private val db: PostDatabase) {

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
    fun getItems(itemName:String) = dao.getItems("$itemName%") //like하기 위해서
    fun getItem(postId:Int) = dao.getItem(postId)

}