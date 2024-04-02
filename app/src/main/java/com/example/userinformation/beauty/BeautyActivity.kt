package com.example.userinformation.beauty

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
import com.example.userinformation.beauty.recycleview.adapter.BeautyAdaptor
import com.example.userinformation.beauty.recycleview.api.BeautyInterface
import com.example.userinformation.home.recycleviewapi.model.HomeToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://jsonplaceholder.typicode.com/"
class Beauty : AppCompatActivity() {
    var modelListView :ArrayList<HomeToDo> = ArrayList<HomeToDo>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beauty)
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Welcome To Beauty Shop")
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
        val retrofitBuilder =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(
                BeautyInterface::class.java)

        val retrofitData = retrofitBuilder.getData(4)
       Log.d("TAG", "loadToDoListData: "+ retrofitData.request())

        retrofitData.enqueue(object : Callback<HomeToDo>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<HomeToDo>, response: Response<HomeToDo>) {
//                Log.d("TAG", "onResponse: "+ response.body().toString())

                val beautyList = ArrayList<String>()

                if(response.isSuccessful){
                    val item =response.body()

                    if (item!==null){
                        val userIdd= item.userId
                        val idd =item.id
                        val titlee = item.title
                        val completedd = item.completed

                        val formatData= "User Id: $idd\n" +
                                        "Id : $userIdd\n" +
                                        "Title : $titlee\n" +
                                        "Completed : $completedd"
                        beautyList.add(formatData)
                    }
                }
                val recyclerview = findViewById<RecyclerView>(R.id.beauty_recycle)
                recyclerview.layoutManager = LinearLayoutManager(this@Beauty)

                val beautyAdaptor = BeautyAdaptor(beautyList)

                recyclerview.adapter=beautyAdaptor

                beautyAdaptor.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<HomeToDo>, t: Throwable) {
                Log.d("MainActivity","onFailure "+t.message)
            }

        })

    }
}