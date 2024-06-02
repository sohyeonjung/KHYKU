package com.example.khyku.viewmodel

import com.example.khyku.roomDB.Comment
import com.example.khyku.roomDB.CommentDatabase

class CommentRepository(private val db: CommentDatabase) {

    val dao = db.getDao()

    suspend fun InsertComment(comment: Comment){
        dao.InsertComment(comment)
    }

//    suspend fun UpdateComment(comment: Comment){
//        dao.UpdateComment(comment)
//    }
//
//    suspend fun DeleteComment(comment: Comment){
//        dao.DeleteComment(comment)
//    }

    fun getAllItems() = dao.getAllItems()
    fun getItems(postId:String) = dao.getItems(postId)

}
