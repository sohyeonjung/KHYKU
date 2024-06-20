package com.example.khyku.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    var userName:String,
    var postTitle:String,
    var postContents:String,
    var postType:String,
    var postDone:Boolean,
    var postTime: String,
    @PrimaryKey(autoGenerate = true)
    val postId:Int=0
)