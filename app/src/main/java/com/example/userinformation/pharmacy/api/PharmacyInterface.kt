package com.example.userinformation.pharmacy.api

import com.example.userinformation.pharmacy.model.PharmacyProduct
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface PharmacyInterface {

//    Call<DataModal> createPost(@Body DataModal dataModal);   this is java syntax

//    fun createPost(@Body product: String, id: String, title: String, completed: String): Call<PharmacyProduct>
@POST("todos")
    fun createPost(
    @Field("userId") userId: String,
    @Field("id") id: String,
    @Field("title") title: String,
    @Field("completed") completed: String
    ): Call<PharmacyProduct>
}