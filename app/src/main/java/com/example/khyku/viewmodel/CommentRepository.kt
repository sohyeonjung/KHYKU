package com.example.khyku.viewmodel

import com.example.khyku.db.Comment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CommentRepository(private val table: DatabaseReference) {

    suspend fun InsertComment(comment: Comment){
        table.child(comment.commentId.toString()).setValue(comment)
        //table.child(item.itemId.toString()).setValue(item)

    }
//
//    suspend fun UpdatePost(post: Post){
//        table.child(post.postTitle).setValue(post)
//        //table.child(item.itemId.toString())
//        //            .child("itemQuantity").setValue(item.itemQuantity)
//    }
//
//    suspend fun DeletePost(post: Post){
//        table.child(post.postTitle).removeValue()
//        //table.child(item.itemId.toString())
//        //            .child("itemQuantity").setValue(item.itemQuantity)
//    }

    fun getAllItems(): Flow<List<Comment>> = callbackFlow{
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val commentList = mutableListOf<Comment>()
                for(commentSnapshot in snapshot.children){
                    val comment = commentSnapshot.getValue(Comment::class.java)
                    comment?.let { commentList.add(it) }
                }
                trySend(commentList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        table.addValueEventListener(listener)
        awaitClose{
            table.removeEventListener(listener)
        }

    }


}
