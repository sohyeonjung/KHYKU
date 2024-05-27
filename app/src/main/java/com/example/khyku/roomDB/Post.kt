package com.example.khyku.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")//table생성
data class Post (
    var postTitle:String,
    var postContents:String,
    @PrimaryKey(autoGenerate = true)//autogenerate 자동으로 키값 생성
    val postId:Int=0
)