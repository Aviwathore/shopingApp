package com.example.userinformation.cloth.api

import retrofit2.Call
import retrofit2.http.GET

interface CommentInterface {
    @GET("comments")
    fun getData(): Call<List<ClothComment>>
}