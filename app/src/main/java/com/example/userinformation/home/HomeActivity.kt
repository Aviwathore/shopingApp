package com.example.userinformation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.home.recycleviewapi.adapter.HomeAdaptor
import com.example.userinformation.home.recycleviewapi.api.HomeInterface
import com.example.userinformation.home.recycleviewapi.model.HomeToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://jsonplaceholder.typicode.com/"
class Home : AppCompatActivity() {

    var modelListView :ArrayList<HomeToDo> = ArrayList<HomeToDo>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Welcome To Home Shop")
            .setMessage("Have a good day !!")
        val alertDialog : AlertDialog =builder.create()
        alertDialog.show()

        loadToDoListData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadToDoListData() {

        // object of retrofit
        val retrofitBuilder =Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(HomeInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<HomeToDo>?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<HomeToDo>?>, response: Response<List<HomeToDo>?>) {
                modelListView = (response.body() as ArrayList<HomeToDo>?)!!

                val homeToDoList =  ArrayList<String>()

                for (list in modelListView){
                    val formatData="User_Id: ${list.userId}\n"+
                            "Id: ${list.id}\n"+
                            "Title: ${list.title}\n"+
                            "Completed: ${list.completed}\n"

                    homeToDoList.add(formatData)
                }
/*
                val listAdapter = ArrayAdapter<String>(
                    this@Home, // activity name
                    R.layout.home_recycle_layout, // row layout
                    R.id.cardView, // ID of the TextView in row layout
                    homeToDoList
                )
                val listView = findViewById<ListView>(R.id.home_recycle)
                listView.adapter = listAdapter

 */

                val recyclerview = findViewById<RecyclerView>(R.id.home_recycle)
                recyclerview.layoutManager = LinearLayoutManager(this@Home)

                val homeAdaptor = HomeAdaptor(homeToDoList)

                recyclerview.adapter=homeAdaptor

                homeAdaptor.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<HomeToDo>?>, t: Throwable) {
               Log.d("MainActivity","onFailure "+t.message)
            }

        })
    }
}