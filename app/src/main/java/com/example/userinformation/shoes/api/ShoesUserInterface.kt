package com.example.userinformation.shoes.api
import com.example.userinformation.shoes.model.UserInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ShoesUserInterface {
    @FormUrlEncoded
    @POST("")
    fun createPost(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("age") age: String,
        @Field("city") city: String,
        @Field("ContactNumber") contactNUmber: String,
        @Field("gender") gender: String,
        @Field("course") course: String
    ): Call<UserInfo>
}