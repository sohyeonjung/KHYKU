package com.example.khyku.nav


sealed class Routes (val route: String) {
    object Community : Routes("Community")
    object InputPost : Routes("InputPost")
    object Post : Routes("Post/{postId}")

}