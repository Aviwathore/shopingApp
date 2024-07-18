package com.example.userinformation.pharmacy

data class PharmacyList(var name:String, var price :Double)
{
    override fun toString(): String {
        return "$name                                             $price"
    }
}

