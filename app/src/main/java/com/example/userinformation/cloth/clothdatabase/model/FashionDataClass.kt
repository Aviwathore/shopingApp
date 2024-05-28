package com.example.userinformation.cloth.clothdatabase.model

data class FashionDataClass(val id: Int, val title:String, val price:Double, val description:String,
                            val category:String, val image: String, val rate:Double,
                            val count:Int, val isFav:Boolean=false)
