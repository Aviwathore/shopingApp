package com.example.userinformation.pharmacy.api
import com.example.userinformation.pharmacy.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface PharmacyJSONInterface {
    @GET("api/users")

     fun getUserNested() : Call<Users>
}