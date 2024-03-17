package com.example.userinformation.beauty

data class BeautyList(var name:String, var price: Double)
{
    override fun toString(): String {
        return "$name                                             $price"
    }
}
