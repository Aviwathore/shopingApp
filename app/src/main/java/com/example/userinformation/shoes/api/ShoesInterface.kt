//package com.example.userinformation.shoes.api
//
//import com.example.userinformation.shoes.Shoes
//import com.example.userinformation.shoes.model.ProductOfShoes
//import okhttp3.Call
//import retrofit2.http.Field
//import retrofit2.http.FormUrlEncoded
//import retrofit2.http.POST
//
//interface ShoesInterface {
//
//    @FormUrlEncoded
//    @POST("products/add")
//    fun createProduct(
//        @Field("id") id :Int
//    ) :Call<ProductOfShoes>
//}