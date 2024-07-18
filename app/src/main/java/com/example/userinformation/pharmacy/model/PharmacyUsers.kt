package com.example.userinformation.pharmacy.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class Users(
    val page: Int,
    val per_page : Int,
    val total : Int,
    val total_pages : Int,

    val data :List<Data>,
    val support : Support
)

data class Data(
    @SerializedName("id")
    val user_id : Int,
    val email : String,

    @SerializedName("first_name")
    val firstName :String,

    @SerializedName("last_name")
    val last_name : String,
    val avatar : URL
)

data class Support(
    val url :String ,
    val text : String
)

