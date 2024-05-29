package com.example.khyku.viewmodel

import com.example.khyku.DB.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class PostRepository(private val table: DatabaseReference) {
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
        //table.child(item.itemId.toString())
        //            .child("itemQuantity").setValue(item.itemQuantity)
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

    fun getItems(postName: String): Flow<List<Post>> = callbackFlow {
        val query = table.orderByChild("postTitle")
            .startAt(postName)
            .endAt(postName + "\uf8ff")

        //val query = table.orderByChild("postTitle").equalTo(postName)
        //val query = table.orderByChild("postTitle").st
        //g.equalTo(itemName)

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
        query.addListenerForSingleValueEvent(listener)
        awaitClose{
            query.removeEventListener(listener)
        }
    }


}