package com.example.userinformation.informationform.emergency_contact_form

data class BeautyList(var name:String, var price: Double)
{
    override fun toString(): String {
        return "$name                                             $price"
    }
}

