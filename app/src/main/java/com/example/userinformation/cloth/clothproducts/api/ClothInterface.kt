package com.example.userinformation.cloth.clothproducts.api

import com.example.userinformation.model.ClothItem
import retrofit2.Call
import retrofit2.http.GET

interface ClothInterface {

    @GET("/products")
    fun getClothItems() :Call<List<ClothItem>>
}
