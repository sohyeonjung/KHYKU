package com.example.khyku.roomDB

data class Post (
    var postTitle:String,
    var postContents:String
    //val postId:Int=0
){
constructor():this("notitle","nocontents")
}