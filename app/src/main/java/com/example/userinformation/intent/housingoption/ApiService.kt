package com.example.userinformation.intent.housingoption

import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
@GET("your-endpoint")
fun getHousingOptions(): Call<List<HousingOption>>
}
