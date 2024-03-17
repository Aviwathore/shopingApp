package com.example.userinformation.home

data class HomeList(var name :String, var price :Double){
    override fun toString(): String {
        return "$name                                             $price"
    }
}
