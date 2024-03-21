package com.example.userinformation.shoes.model

data class ProductOfShoes(
    val productList :List<Product>,

    val total : Int,
    val skip : Int,
    val limit : Int

)

data class Product(
    val id :Int,
    val description : String ,
    val price: Double ,
    val discountPrice : Double,
    val rating : Int,
    val stock : Int,
    val brand : String,
    val category : String,
    val thumbnail : String,
    val images : List<Images>
)

data class Images(
    val img :String
)

