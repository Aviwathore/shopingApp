package com.example.userinformation.customViewForRecycleView.api

import com.example.userinformation.customViewForRecycleView.modal.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface CustomRVDataAvatarApi {

    @GET("api/users")
    fun getData() :Call<UserResponse>
}