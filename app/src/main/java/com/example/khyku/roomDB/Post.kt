package com.example.khyku.roomDB

data class Post(
    var postTitle:String,
    var postContents:String,
    var postType:String,
    var postDone:Boolean,
    var postTime: String
    //val postId:Int=0
){
constructor():this(" "," "," ", false," ")
}