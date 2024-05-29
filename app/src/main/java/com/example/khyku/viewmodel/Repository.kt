package com.example.khyku.viewmodel

import com.example.khyku.roomDB.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Repository(private val table: DatabaseReference) {
    suspend fun InsertPost(post: Post){
        table.child(post.postTitle).setValue(post)
        //table.child(item.itemId.toString()).setValue(item)
    }

    suspend fun UpdatePost(post: Post){
        table.child(post.postTitle).setValue(post)
        //table.child(item.itemId.toString())
        //            .child("itemQuantity").setValue(item.itemQuantity)
    }

    suspend fun DeletePost(post: Post){
        table.child(post.postTitle).removeValue()
    }

    fun getAllItems(): Flow<List<Post>> = callbackFlow{
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = mutableListOf<Post>()
                for(postSnapshot in snapshot.children){
                    val post = postSnapshot.getValue(Post::class.java)
                    post?.let { postList.add(it) }
                }
                trySend(postList)
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