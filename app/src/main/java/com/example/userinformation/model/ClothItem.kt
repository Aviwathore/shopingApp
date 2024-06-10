package com.example.userinformation.model
import com.google.gson.annotations.SerializedName


data class ClothItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("price")
    var price: Double,

    @SerializedName("description")
    val description: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("rating")
    val rating: Rating,

    var is_fav: Int,

    val productSize: String,
    var productCount: Int,
    val subTotal: Long,
    val deliveryCharge: Int,
    val discount: Int,
    val totalCost: Long,
    val addToCart: Int,
    val userAddress: String,
    val orderConfirm: String,
    val deliveryDate: String,
    val orderId: Long,
    val deliveryBy: String,
    val cartNumber: String,
    val cardExpiryDate: String,
    val cardHolderName: String,
    val paymentType:String
)

data class Rating(
    @SerializedName("rate")
    val rate: Double,

    @SerializedName("count")
    val count: Int
)