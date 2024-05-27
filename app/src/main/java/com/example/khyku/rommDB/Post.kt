package com.example.khyku.rommDB

import androidx.room.Entity

@Entity(tableName = "Posts") //게시물 table 생성
data class Post(
    var postTitle:String,
    var postContents:String,
//    @PrimaryKey(autoGenerate = true)//autogenerate 자동으로 키값 생성
//    var postId:Int=0
)
