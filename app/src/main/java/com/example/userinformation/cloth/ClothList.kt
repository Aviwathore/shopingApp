package com.example.userinformation.cloth

data class ClothList(var clothName : String, var price :Double)
{
    override fun toString(): String {

        return "$clothName                                             $price"
    }
}