package com.example.userinformation.informationform.emergency_contact_form.recycleview.api

import com.example.userinformation.home.recycleviewapi.model.HomeToDo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BeautyInterface {
    @GET("todos/{id}")
    fun getData(@Path("id") id: Int?): Call<HomeToDo>
}