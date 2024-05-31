package com.example.khyku.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    var commentContents:String,
    var commentTime:String,
    @PrimaryKey(autoGenerate = true)
    var commentId:Int=0
)