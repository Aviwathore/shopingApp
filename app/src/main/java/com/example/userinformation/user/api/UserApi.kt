package com.example.userinformation.user.api

import com.example.userinformation.user.modal.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("api/users")
    fun postData(@Body userModal :User?) :Call<User ?>?
}
