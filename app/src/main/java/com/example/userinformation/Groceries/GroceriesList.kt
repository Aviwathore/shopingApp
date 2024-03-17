package com.example.userinformation.Groceries

data class GroceriesList(var name:String, var price :Double){
    override fun toString(): String {
        return "$name                                             $price"
    }
}
