package com.example.userinformation.cloth.clothproducts.model

import com.google.gson.annotations.SerializedName

data class ClothItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("rating")
    val rating: Rating,

    var is_fav: Int
)

data class Rating(
    @SerializedName("rate")
    val rate: Double,

    @SerializedName("count")
    val count: Int
)