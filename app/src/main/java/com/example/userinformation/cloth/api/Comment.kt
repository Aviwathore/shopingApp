package com.example.userinformation.cloth.api

data class Comment(
    val postId: Int,
    val id :Int,
    val body: String,
    val email: String,
    val name: String
)
