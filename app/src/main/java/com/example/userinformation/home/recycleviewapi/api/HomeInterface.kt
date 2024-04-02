package com.example.userinformation.home.recycleviewapi.api

import com.example.userinformation.home.recycleviewapi.model.HomeToDo
import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {
    @GET("todos")
    fun getData(): Call<List<HomeToDo>>
}