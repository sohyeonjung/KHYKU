package com.example.khyku.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    var userName:String,
    var commentContents:String,
    var commentTime:String,
    var commentPost:String,
    @PrimaryKey(autoGenerate = true)
    var commentId:Int=0
)