package com.example.userinformation.customViewForRecycleView

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.customViewForRecycleView.adapter.CustomRVAdapter
import com.example.userinformation.customViewForRecycleView.api.DataAvatarApi
import com.example.userinformation.customViewForRecycleView.modal.Data
import com.example.userinformation.customViewForRecycleView.modal.UserResponse
import com.example.userinformation.databinding.ActivityCarvBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://reqres.in/"
class CARV : AppCompatActivity() {
    private lateinit var binding:ActivityCarvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCarvBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadData()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadData() {
        val load = binding.progrssbarId

        val retrofitBuilder=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(DataAvatarApi::class.java)

        val carvData = retrofitBuilder.getData()

        Log.d("Data","Fetch Data")

        carvData.enqueue(object :Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val res= response.body()

                val formatList = ArrayList<Data>()
                if (res!=null){
//                    Log.d("Yesss"," finalyy done"+res.data

                    for (users in res.data){
                        formatList.add(users)
                    }

                    val recycleView = binding.viewCarv
                    recycleView.setPadding(0,0,0,0,)
                    val layoutParams =recycleView.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(0,0,0,0)
                    recycleView.layoutParams=layoutParams

//                    recycleView.layoutManager=LinearLayoutManager(this@CARV)

                    val adapter =CustomRVAdapter(this@CARV,formatList)
                    recycleView.adapter=adapter

                    load.visibility=View.GONE
//                    load.visibility=View.VISIBLE

                }else{
                    Log.d("Nooo", "No")
                }


            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Fail", "Not Fetch a Response")
            }


        })
    }
}