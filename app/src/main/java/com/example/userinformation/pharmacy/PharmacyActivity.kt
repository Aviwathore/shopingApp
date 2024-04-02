package com.example.userinformation.pharmacy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.pharmacy.adapter.PharmacyJSONAdapter
import com.example.userinformation.pharmacy.api.PharmacyJSONInterface
import com.example.userinformation.pharmacy.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://reqres.in/"
class Pharmacy : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pharmacy)

        jasonPars()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun jasonPars() {
        val retrofitBuilder =Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(PharmacyJSONInterface::class.java)
        val retrofitData = retrofitBuilder.getUserNested()

        retrofitData.enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val pharmacyList = ArrayList<String>()
                if (response.isSuccessful){
                    val pharmacyResponse = response.body()

                    if (pharmacyResponse !==null){

                            val page = pharmacyResponse.page
                            val perPage = pharmacyResponse.per_page
                            val total = pharmacyResponse.total
                            val totalPages = pharmacyResponse.total_pages
                            val url = pharmacyResponse.support.url
                            val text = pharmacyResponse.support.text


                            val formatData = "page :$page\n" +
                                    "perPage :$perPage\n" +
                                    "total : $total\n" +
                                    "totalPages : $totalPages\n" +
                                    "url :$url\n" +
                                    "text : $text\n"

                            pharmacyList.add(formatData)


                        pharmacyResponse.data.forEach{user->
                            val id = user.user_id
                            val first = user.firstName
                            val last = user.last_name
                            val email = user.email
                            val avtar = user.avatar

                            val formatData = "Id: $id\n" +
                                    "firstName :$first\n" +
                                    "lastName :$last\n" +
                                    "Email :$email\n" +
                                    "avtar :$avtar"

                            pharmacyList.add(formatData)

                        }

                    }

                    val recycleView = findViewById<RecyclerView>(R.id.recycle_Pharmacy_view)

                    recycleView.layoutManager = LinearLayoutManager(this@Pharmacy)
                    val pharAdaptor = PharmacyJSONAdapter(pharmacyList)

                    recycleView.adapter=pharAdaptor

                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("PHARMACYACTIVITY","ONFAILURE"+t.message)
            }

        })
    }

}

