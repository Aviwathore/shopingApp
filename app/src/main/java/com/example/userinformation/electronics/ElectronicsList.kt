package com.example.userinformation.electronics

data class ElectronicsList(var name:String, var price: Double){
    override fun toString(): String {
        return "$name                                             $price"
    }
}

