package com.example.userinformation.Groceries.api

import com.example.userinformation.Groceries.model.GroceriesData
import com.example.userinformation.pharmacy.model.PharmacyProduct
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GroceriesInterface {
    @FormUrlEncoded
    @POST("api/users")
    fun createPost(
        @Field("name") name: String,
        @Field("job") job: String
    ): Call<GroceriesData>
}