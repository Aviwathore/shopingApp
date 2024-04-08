package com.example.userinformation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DialogTitle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userinformation.R
import com.example.userinformation.databinding.ActivityHomeBinding
import com.example.userinformation.home.recycleviewapi.adapter.HomeAdaptor
import com.example.userinformation.home.recycleviewapi.api.HomeInterface
import com.example.userinformation.home.recycleviewapi.api.OnDeleteItemClickListener
import com.example.userinformation.home.recycleviewapi.model.HomeToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://jsonplaceholder.typicode.com/"
class Home : AppCompatActivity(), OnDeleteItemClickListener {
    private lateinit var recyclerView: RecyclerView
    var modelListView :ArrayList<HomeToDo> = ArrayList<HomeToDo>()
    private lateinit var homeAdapte :HomeAdaptor

    private lateinit var binding: ActivityHomeBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadToDoListData()
        recyclerView= findViewById<RecyclerView>(R.id.home_recycle)
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


                // Initialize RecyclerView
                val recyclerview = binding.homeRecycle
                recyclerview.layoutManager = LinearLayoutManager(this@Home)
                homeAdapte= HomeAdaptor(modelListView, this@Home)

                // Set adapter to RecyclerView
                recyclerview.adapter = homeAdapte
//                recyclerview.adapter?.notifyDataSetChanged()
            }

                override fun onFailure(call: Call<List<HomeToDo>?>, t: Throwable) {
               Log.d("MainActivity","onFailure "+t.message)
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged", "RestrictedApi")
    override fun onItemClick(position: Int, homeList: ArrayList<HomeToDo>) {
        showAlertDialog(position, recyclerView, homeList)

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showAlertDialog(position: Int, recyclerView: RecyclerView, homeList: ArrayList<HomeToDo>) {
        val builder = AlertDialog.Builder(this)
        val details = homeList[position]

       builder.setTitle(" Are you sure you want to delete this?")
            .setMessage("Id: ${details.id}\nTitle: ${details.title}" )
            .setPositiveButton("YES") { dialog, _ ->
                Log.d("before", "before === "+ homeList.size.toString())
                Log.d("position", "position === $position")
                homeList.removeAt(position)
                Log.d("after", "after"+homeList.size.toString())
                homeAdapte.updateList(homeList)
                homeAdapte.notifyDataSetChanged()

                dialog.dismiss()
            }.setNegativeButton("NO"){ dialog, _ ->
                dialog.dismiss()

            }
            .show()
    }
}