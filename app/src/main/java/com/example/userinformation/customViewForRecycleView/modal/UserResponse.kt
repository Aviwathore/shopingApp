package com.example.userinformation.customViewForRecycleView.modal

import com.google.gson.annotations.SerializedName

data class UserResponse(
   val data :List<Data>
)

data class Data(
    val id :Int,
    @SerializedName("first_name")
    val firstName :String,

    @SerializedName("last_name")
    val lastName :String,

    @SerializedName("email")
    val email :String,

    @SerializedName("avatar")
    val avatar :String
)

