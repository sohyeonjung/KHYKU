package com.example.khyku.DB

data class Comment(

    //var userName:String,
    var commentContents:String,
    var commentTime:String,
    var commentId:Int,
    var recomments: MutableList<Comment> = mutableListOf()

){
    constructor():this(" ", " ", 0)
}